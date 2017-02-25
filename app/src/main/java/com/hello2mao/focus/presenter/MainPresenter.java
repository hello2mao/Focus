package com.hello2mao.focus.presenter;


import com.hello2mao.focus.base.RxPresenter;
import com.hello2mao.focus.model.http.RetrofitHelper;
import com.hello2mao.focus.presenter.contract.MainContract;

import javax.inject.Inject;

public class MainPresenter extends RxPresenter<MainContract.View> implements MainContract.Presenter {

    private RetrofitHelper retrofitHelper;

    @Inject
    public MainPresenter(RetrofitHelper retrofitHelper) {
        this.retrofitHelper = retrofitHelper;
    }

}
