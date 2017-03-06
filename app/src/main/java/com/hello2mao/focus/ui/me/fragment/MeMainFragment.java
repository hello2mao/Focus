package com.hello2mao.focus.ui.me.fragment;

import android.widget.Toast;

import com.hello2mao.focus.R;
import com.hello2mao.focus.base.BaseFragment;
import com.hello2mao.focus.presenter.me.MeMainPresenter;
import com.hello2mao.focus.presenter.me.contract.MeMainContract;
import com.hello2mao.focus.ui.main.fragment.OnTabReselectListener;


public class MeMainFragment extends BaseFragment<MeMainPresenter> implements MeMainContract.View,
        OnTabReselectListener {

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_me_main;
    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    public void onTabReselect() {
        Toast.makeText(context, "Double Me", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showError(String msg) {

    }
}
