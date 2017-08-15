package com.hello2mao.focus.model.http;


import android.support.compat.BuildConfig;

import com.hello2mao.focus.app.Constants;
import com.hello2mao.focus.model.bean.BaiduImageBean;
import com.hello2mao.focus.model.bean.DailyListBean;
import com.hello2mao.focus.model.bean.NewsBean;
import com.hello2mao.focus.model.bean.ResultResponse;
import com.hello2mao.focus.model.http.api.BaiduImageApis;
import com.hello2mao.focus.model.http.api.ToutiaoApis;
import com.hello2mao.focus.model.http.api.ZhihuApis;
import com.hello2mao.focus.util.SystemUtil;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import rx.Observable;

public class RetrofitHelper {

    private OkHttpClient okHttpClient = null;
    private static BaiduImageApis baiduImageApis = null;
    private static ZhihuApis zhihuApis = null;
    private static ToutiaoApis toutiaoApis = null;

    public RetrofitHelper() {
        init();
    }

    private void init() {
        initOkHttp();
        baiduImageApis = getApiService(BaiduImageApis.HOST, BaiduImageApis.class);
        zhihuApis = getApiService(ZhihuApis.HOST, ZhihuApis.class);
        toutiaoApis = getApiService(ToutiaoApis.API_SERVER_URL, ToutiaoApis.class);
    }

    private void initOkHttp() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            // https://drakeet.me/retrofit-2-0-okhttp-3-0-config
            // HttpLoggingInterceptor 是一个拦截器，用于输出网络请求和结果的 Log，
            // 可以配置 level 为 BASIC / HEADERS / BODY，
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
            builder.addInterceptor(httpLoggingInterceptor);
        }
        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request.Builder builder = chain.request().newBuilder();
                builder.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.108 Safari/537.36 2345Explorer/8.0.0.13547");
                builder.addHeader("Cache-Control", "max-age=0");
                builder.addHeader("Upgrade-Insecure-Requests", "1");
                builder.addHeader("X-Requested-With", "XMLHttpRequest");
                builder.addHeader("Cookie", "uuid=\"w:f2e0e469165542f8a3960f67cb354026\"; __tasessionId=4p6q77g6q1479458262778; csrftoken=7de2dd812d513441f85cf8272f015ce5; tt_webid=36385357187");
                return chain.proceed(builder.build());
            }
        });
        // http://www.jianshu.com/p/93153b34310e
        File cacheFile = new File(Constants.PATH_CACHE);
        // 缓存大小为50MB
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 50);
        Interceptor cacheInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if (!SystemUtil.isNetworkConnected()) {
                    request = request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                }
                Response response = chain.proceed(request);
                if (SystemUtil.isNetworkConnected()) {
                    int maxAge= 0;
                    // 有网络时，不缓存，最大保存时长为0
                    response.newBuilder()
                            .header("Cache-Control", "public, max-age=" + maxAge)
                            .removeHeader("Pragma")
                            .build();
                } else {
                    // 无网络时，设置超时为4周
                    int maxStale = 60 * 60 * 24 * 28;
                    response.newBuilder()
                            .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                            .removeHeader("Pragma")
                            .build();
                }
                return response;
            }
        };
        // 设置缓存
        builder.addNetworkInterceptor(cacheInterceptor);
        builder.addInterceptor(cacheInterceptor);
        builder.cache(cache);
        // 设置超时
        builder.connectTimeout(10, TimeUnit.SECONDS);
        builder.readTimeout(20, TimeUnit.SECONDS);
        builder.writeTimeout(20, TimeUnit.SECONDS);
        // 错误重连
        builder.retryOnConnectionFailure(true);
        okHttpClient = builder.build();
    }

    private <T> T getApiService(String baseUrl, Class<T> clz) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit.create(clz);
    }

    public Observable<BaiduImageBean> fetchBaiduImageInfo(int pn, int rn, String tag1, String tag2,
                                                          String ftags, String ie) {
        return baiduImageApis.getBaiduImageInfo(pn, rn, tag1, tag2, ftags, ie);
    }

    public Observable<DailyListBean> fetchDailyListInfo() {
        return zhihuApis.getDailyList();
    }

    public Observable<DailyListBean> fetchDailyBeforeListInfo(String date) {
        return zhihuApis.getDailyBeforeList(date);
    }

    public Observable<ResultResponse<List<NewsBean>>> fetchNews(String category) {
        return toutiaoApis.getNews(category);
    }
}
