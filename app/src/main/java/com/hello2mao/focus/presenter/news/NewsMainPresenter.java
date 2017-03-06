package com.hello2mao.focus.presenter.news;


import com.hello2mao.focus.base.RxPresenter;
import com.hello2mao.focus.model.http.RetrofitHelper;
import com.hello2mao.focus.presenter.news.contract.NewsMainContract;

import javax.inject.Inject;

public class NewsMainPresenter extends RxPresenter<NewsMainContract.View>
        implements NewsMainContract.Presenter {

    private RetrofitHelper retrofitHelper;

    @Inject
    public NewsMainPresenter(RetrofitHelper retrofitHelper) {
        this.retrofitHelper = retrofitHelper;
    }
}
