package com.hello2mao.focus.presenter.news.contract;

import com.hello2mao.focus.base.BasePresenter;
import com.hello2mao.focus.base.BaseView;
import com.hello2mao.focus.model.bean.NewsBean;

import java.util.List;

public interface NewsListContract {

    interface View extends BaseView {

        void showContent(List<NewsBean> newsList);

    }

    interface Presenter extends BasePresenter<View> {

        void getContent(String category);

    }
}
