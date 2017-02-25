package com.hello2mao.focus.presenter.contract;

import com.hello2mao.focus.base.BasePresenter;
import com.hello2mao.focus.base.BaseView;

public interface SplashContract {

    interface View extends BaseView {

        void jumpToGuide();
        void jumpToMain();

    }

    interface Presenter extends BasePresenter<View> {

        void getSplashData();
    }
}
