package com.hello2mao.focus.presenter.me;

import com.hello2mao.focus.base.RxPresenter;
import com.hello2mao.focus.model.http.RetrofitHelper;
import com.hello2mao.focus.presenter.me.contract.MeMainContract;

import javax.inject.Inject;


public class MeMainPresenter extends RxPresenter<MeMainContract.View>
        implements MeMainContract.Presenter {

    private RetrofitHelper retrofitHelper;

    @Inject
    public MeMainPresenter(RetrofitHelper retrofitHelper) {
        this.retrofitHelper = retrofitHelper;
    }
}
