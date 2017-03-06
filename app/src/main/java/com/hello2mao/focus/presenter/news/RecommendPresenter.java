package com.hello2mao.focus.presenter.news;


import com.hello2mao.focus.base.RxPresenter;
import com.hello2mao.focus.model.bean.DailyListBean;
import com.hello2mao.focus.model.http.RetrofitHelper;
import com.hello2mao.focus.presenter.news.contract.RecommendContract;
import com.hello2mao.focus.util.RxUtil;

import java.util.List;

import javax.inject.Inject;

import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;

public class RecommendPresenter extends RxPresenter<RecommendContract.View>
        implements RecommendContract.Presenter {

    private RetrofitHelper retrofitHelper;

    @Inject
    public RecommendPresenter(RetrofitHelper retrofitHelper) {
        this.retrofitHelper = retrofitHelper;
    }

    @Override
    public void getContent() {
        Subscription rxSubscription = retrofitHelper.fetchDailyListInfo()
                .compose(RxUtil.<DailyListBean>rxSchedulerHelper())
                .map(new Func1<DailyListBean, DailyListBean>() {
                    @Override
                    public DailyListBean call(DailyListBean dailyListBean) {
                        List<DailyListBean.StoriesBean> list = dailyListBean.getStories();
                        for(DailyListBean.StoriesBean item : list) {
                            item.setReadState(mRealmHelper.queryNewsId(item.getId()));
                        }
                        return dailyListBean;
                    }
                })
                .subscribe(new Action1<DailyListBean>() {
                    @Override
                    public void call(DailyListBean dailyListBean) {
                        topCount = dailyListBean.getTop_stories().size();
                        view.showContent(dailyListBean);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        view.showError("数据加载失败ヽ(≧Д≦)ノ");
                    }
                });
        addSubscribe(rxSubscription);
    }
}
