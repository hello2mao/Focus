package com.hello2mao.focus.base;


import com.hello2mao.focus.log.BasicLog;
import com.hello2mao.focus.log.LogManager;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * 基于Rx的Presenter封装,控制订阅的生命周期
 */
public class RxPresenter<T extends BaseView> implements BasePresenter<T> {

    protected T view;
    protected CompositeSubscription compositeSubscription;
    protected static final BasicLog LOG = LogManager.getInstance();

    protected void addSubscribe(Subscription subscription) {
         if (null == compositeSubscription) {
             compositeSubscription = new CompositeSubscription();
         }
        compositeSubscription.add(subscription);
    }

    protected void unSubscribe() {
        if (null != compositeSubscription) {
            compositeSubscription.unsubscribe();
        }
    }

    @Override
    public void attachView(T view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
        unSubscribe();
    }
}
