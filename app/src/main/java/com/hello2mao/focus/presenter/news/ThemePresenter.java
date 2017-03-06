package com.hello2mao.focus.presenter.news;


import com.hello2mao.focus.base.RxPresenter;
import com.hello2mao.focus.model.http.RetrofitHelper;
import com.hello2mao.focus.presenter.news.contract.ThemeContract;

import javax.inject.Inject;

public class ThemePresenter extends RxPresenter<ThemeContract.View>
        implements ThemeContract.Presenter {

    private RetrofitHelper retrofitHelper;

    @Inject
    public ThemePresenter(RetrofitHelper retrofitHelper) {
        this.retrofitHelper = retrofitHelper;
    }
}
