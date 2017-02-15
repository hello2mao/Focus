package com.hello2mao.focus.presenter;


import com.hello2mao.focus.base.RxPresenter;
import com.hello2mao.focus.model.bean.SplashBean;
import com.hello2mao.focus.model.http.RetrofitHelper;
import com.hello2mao.focus.presenter.contract.SplashContract;
import com.hello2mao.focus.util.RxUtil;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;

public class SplashPresenter extends RxPresenter<SplashContract.View>
        implements SplashContract.Presenter {

    private static final String RES = "1080*1776";
    private static final int COUNT_DOWN_TIME = 2200;
    private RetrofitHelper retrofitHelper;

    @Inject
    public SplashPresenter(RetrofitHelper retrofitHelper) {
        this.retrofitHelper = retrofitHelper;
    }

    @Override
    public void getSplashData() {
        Subscription rxSubscription = retrofitHelper.fetchSplashInfo(RES)
                .compose(RxUtil.<SplashBean>rxSchedulerHelper())
                .subscribe(new Action1<SplashBean>() {
                    @Override
                    public void call(SplashBean splashBean) {
                        view.showContent(splashBean);
                        startCountDown();
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        view.jumpToMain();
                    }
                });
        addSubscribe(rxSubscription);
    }

    private void startCountDown() {
        Subscription rxSubscription = Observable.timer(COUNT_DOWN_TIME, TimeUnit.MILLISECONDS)
                .compose(RxUtil.<Long>rxSchedulerHelper())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        view.jumpToMain();
                    }
                });
        addSubscribe(rxSubscription);
    }
}
