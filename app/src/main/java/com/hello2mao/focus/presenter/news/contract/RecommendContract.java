package com.hello2mao.focus.presenter.news.contract;

import com.hello2mao.focus.base.BasePresenter;
import com.hello2mao.focus.base.BaseView;
import com.hello2mao.focus.model.bean.DailyListBean;

public interface RecommendContract {

    interface View extends BaseView {

        void showContent(DailyListBean info);

    }

    interface Presenter extends BasePresenter<View> {

        void getContent();

    }
}
