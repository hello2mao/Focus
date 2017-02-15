package com.hello2mao.focus.base;

/**
 * Presenter基类
 */
public interface BasePresenter<T extends BaseView> {

    void attachView(T view);

    void detachView();
}
