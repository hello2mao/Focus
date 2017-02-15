package com.hello2mao.focus.model.http.api;


import com.hello2mao.focus.model.bean.SplashBean;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface ZhihuApis {

    String HOST = "http://news-at.zhihu.com/api/4/";

    /**
     * 启动界面图片
     */
    @GET("start-image/{res}")
    Observable<SplashBean> getSplashInfo(@Path("res") String res);

}
