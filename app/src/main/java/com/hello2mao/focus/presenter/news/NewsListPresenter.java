package com.hello2mao.focus.presenter.news;

import com.hello2mao.focus.base.RxPresenter;
import com.hello2mao.focus.model.bean.NewsBean;
import com.hello2mao.focus.model.bean.ResultResponse;
import com.hello2mao.focus.model.db.RealmHelper;
import com.hello2mao.focus.model.http.RetrofitHelper;
import com.hello2mao.focus.presenter.news.contract.NewsListContract;
import com.hello2mao.focus.util.RxUtil;

import java.util.List;

import javax.inject.Inject;

import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Action1;

public class NewsListPresenter extends RxPresenter<NewsListContract.View>
        implements NewsListContract.Presenter {

    private RetrofitHelper retrofitHelper;
    private RealmHelper realmHelper;

    @Inject
    public NewsListPresenter(RetrofitHelper retrofitHelper, RealmHelper realmHelper) {
        this.retrofitHelper = retrofitHelper;
        this.realmHelper = realmHelper;
    }

    @Override
    public void getContent(String category) {
        Subscription rxSubscription = retrofitHelper.fetchNews(category)
                .compose(RxUtil.<ResultResponse<List<NewsBean>>>rxSchedulerHelper())
                // TODO:数据库记录，暂时启用
//                .map(new Func1<ResultResponse<List<NewsBean>>, ResultResponse<List<NewsBean>>>() {
//                    @Override
//                    public ResultResponse<List<NewsBean>> call(ResultResponse<List<NewsBean>> response) {
//                        if (response.getMessage().equals("success")) {
//                            List<NewsBean> newsList = response.getData();
//                            for (NewsBean news : newsList) {
//                                news.setReadState(realmHelper.queryNewsId(news.getGroup_id()));
//                            }
//                        }
//                        return response;
//                    }
//                })
                .subscribe(new Action1<ResultResponse<List<NewsBean>>>() {
                    // onNext
                    @Override
                    public void call(ResultResponse<List<NewsBean>> response) {
                        if (response.getMessage().equals("success")) {
                            List<NewsBean> newsList = response.getData();
                            if (newsList.size() > 0) {
                                // 由于最后一条重复，所以删除
                                newsList.remove(newsList.size() - 1);
                                view.showContent(newsList);
                            }
                        } else {
                            view.showError("数据获取失败");
                        }
                    }
                }, new Action1<Throwable>() {
                    // onError
                    @Override
                    public void call(Throwable throwable) {
                        view.showError("数据获取异常");
                    }
                }, new Action0() {
                    // onComplete
                    @Override
                    public void call() {
                        LOG.debug("数据获取成功");
                    }
                });
        addSubscribe(rxSubscription);
    }

}
