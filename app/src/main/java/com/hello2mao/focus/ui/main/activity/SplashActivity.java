package com.hello2mao.focus.ui.main.activity;

import android.content.Intent;

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
        presenter.getSplashData();
    }

    @Override
    public void jumpToGuide() {

    }

    @Override
    public void jumpToMain() {
        Intent intent = new Intent();
        intent.setClass(this,MainActivity.class);
        startActivity(intent);
        finish();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
