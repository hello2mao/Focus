package com.hello2mao.focus.base;


import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * 基于Rx的Presenter封装,控制订阅的生命周期
 */
public class RxPresenter<T extends BaseView> implements BasePresenter<T> {

    private T view;
    protected CompositeSubscription compositeSubscription;

    private void addSubscribe(Subscription subscription) {
         if (null == compositeSubscription) {
             compositeSubscription = new CompositeSubscription();
         }
        compositeSubscription.add(subscription);
    }

    private void unSubscribe() {

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
