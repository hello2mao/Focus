package com.hello2mao.focus.ui.explore.fragment;


import android.widget.Toast;

import com.hello2mao.focus.R;
import com.hello2mao.focus.base.BaseFragment;
import com.hello2mao.focus.presenter.explore.ExploreMainPresenter;
import com.hello2mao.focus.presenter.explore.contract.ExploreMainContract;
import com.hello2mao.focus.ui.main.fragment.OnTabReselectListener;

public class ExploreMainFragment extends BaseFragment<ExploreMainPresenter>
        implements ExploreMainContract.View, OnTabReselectListener {

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_explore_main;
    }

    @Override
    protected void initEventAndData(){

    }

    @Override
    public void onTabReselect() {
        Toast.makeText(context, "Double Explore", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError(String msg) {

    }
}
