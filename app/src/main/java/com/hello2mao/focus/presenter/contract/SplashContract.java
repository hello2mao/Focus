package com.hello2mao.focus.presenter.contract;


import com.hello2mao.focus.base.BasePresenter;
import com.hello2mao.focus.base.BaseView;
import com.hello2mao.focus.model.bean.SplashBean;

public interface SplashContract {

    interface View extends BaseView {

        void showContent(SplashBean splashBean);

        void jumpToMain();
    }

    interface Presenter extends BasePresenter<View> {

        void getSplashData();
    }
}
