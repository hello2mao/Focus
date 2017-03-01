package com.hello2mao.focus.ui.news.fragment;


import com.hello2mao.focus.R;
import com.hello2mao.focus.base.BaseFragment;
import com.hello2mao.focus.presenter.DailyPresenter;
import com.hello2mao.focus.presenter.contract.DailyContract;

public class DailyFragment extends BaseFragment<DailyPresenter> implements DailyContract.View {

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_daily;
    }

    @Override
    protected void initEventAndData() {

    }
}
