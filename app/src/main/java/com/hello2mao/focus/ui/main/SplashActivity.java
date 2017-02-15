package com.hello2mao.focus.ui.main;


import com.hello2mao.focus.R;
import com.hello2mao.focus.base.BaseActivity;
import com.hello2mao.focus.presenter.SplashPresenter;
import com.hello2mao.focus.presenter.contract.SplashContract;

public class SplashActivity extends BaseActivity<SplashPresenter> implements SplashContract.View {

    @Override
    protected int getLayout() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    public void showContent() {

    }

    @Override
    public void junmpToMain() {

    }

    @Override
    public void showError(String msg) {

    }
}
