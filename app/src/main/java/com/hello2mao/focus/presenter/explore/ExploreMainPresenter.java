package com.hello2mao.focus.presenter.explore;


import com.hello2mao.focus.base.RxPresenter;
import com.hello2mao.focus.model.http.RetrofitHelper;
import com.hello2mao.focus.presenter.explore.contract.ExploreMainContract;

import javax.inject.Inject;

public class ExploreMainPresenter extends RxPresenter<ExploreMainContract.View>
        implements ExploreMainContract.Presenter {

    private RetrofitHelper retrofitHelper;

    @Inject
    public ExploreMainPresenter(RetrofitHelper retrofitHelper) {
        this.retrofitHelper = retrofitHelper;
    }
}
