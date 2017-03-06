package com.hello2mao.focus.presenter.main;


import com.hello2mao.focus.base.RxPresenter;
import com.hello2mao.focus.presenter.main.contract.SplashContract;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;

public class SplashPresenter extends RxPresenter<SplashContract.View>
        implements SplashContract.Presenter {

    private static final long SPLASH_DELAY_MILLIS = 800;

    @Inject
    public SplashPresenter() {
    }

    @Override
    public void getSplashData() {
        Subscription rxSubscription = Observable.timer(SPLASH_DELAY_MILLIS, TimeUnit.MILLISECONDS)
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        view.jumpToMain();
                    }
                });
        addSubscribe(rxSubscription);
    }

}
