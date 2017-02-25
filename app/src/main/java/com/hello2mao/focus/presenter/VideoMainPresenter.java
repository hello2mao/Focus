package com.hello2mao.focus.presenter;


import com.hello2mao.focus.base.RxPresenter;
import com.hello2mao.focus.model.http.RetrofitHelper;
import com.hello2mao.focus.presenter.contract.VideoMainContract;

import javax.inject.Inject;

public class VideoMainPresenter extends RxPresenter<VideoMainContract.View>
        implements VideoMainContract.Presenter {

    private RetrofitHelper retrofitHelper;

    @Inject
    public VideoMainPresenter(RetrofitHelper retrofitHelper) {
        this.retrofitHelper = retrofitHelper;
    }

}
