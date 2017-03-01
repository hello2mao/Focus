package com.hello2mao.focus.presenter;


import com.hello2mao.focus.base.RxPresenter;
import com.hello2mao.focus.model.http.RetrofitHelper;
import com.hello2mao.focus.presenter.contract.DailyContract;

import javax.inject.Inject;

public class DailyPresenter extends RxPresenter<DailyContract.View>
        implements DailyContract.Presenter {

    private RetrofitHelper retrofitHelper;

    @Inject
    public DailyPresenter(RetrofitHelper retrofitHelper) {
        this.retrofitHelper = retrofitHelper;
    }
}
