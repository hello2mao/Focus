package com.hello2mao.focus.presenter;


import com.hello2mao.focus.base.RxPresenter;
import com.hello2mao.focus.presenter.contract.SplashContract;

import javax.inject.Inject;

public class SplashPresenter extends RxPresenter<SplashContract.View>
        implements SplashContract.Presenter {


    @Inject
    public SplashPresenter() {

    }

    @Override
    public void getSplashData() {

    }
}
