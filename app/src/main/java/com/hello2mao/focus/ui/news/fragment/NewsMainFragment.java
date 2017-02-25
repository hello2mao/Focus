package com.hello2mao.focus.ui.news.fragment;


import android.widget.Toast;

import com.hello2mao.focus.R;
import com.hello2mao.focus.base.BaseFragment;
import com.hello2mao.focus.presenter.NewsMainPresenter;
import com.hello2mao.focus.presenter.contract.NewsMainContract;
import com.hello2mao.focus.ui.main.fragment.OnTabReselectListener;

public class NewsMainFragment extends BaseFragment<NewsMainPresenter> implements
        NewsMainContract.View, OnTabReselectListener {

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_news_main;
    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    public void onTabReselect() {
        Toast.makeText(context, "Double News", Toast.LENGTH_SHORT).show();

    }
}
