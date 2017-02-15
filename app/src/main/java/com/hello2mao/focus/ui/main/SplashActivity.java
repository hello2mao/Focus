package com.hello2mao.focus.ui.main;


import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hello2mao.focus.R;
import com.hello2mao.focus.base.BaseActivity;
import com.hello2mao.focus.component.ImageLoader;
import com.hello2mao.focus.model.bean.SplashBean;
import com.hello2mao.focus.presenter.SplashPresenter;
import com.hello2mao.focus.presenter.contract.SplashContract;

import butterknife.BindView;

public class SplashActivity extends BaseActivity<SplashPresenter> implements SplashContract.View {

    @BindView(R.id.iv_splash_bg)
    ImageView ivSplashBg;
    @BindView(R.id.tv_splash_author)
    TextView tvSplashAuthor;

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
    public void showContent(SplashBean splashBean) {
        ImageLoader.load(this, splashBean.getImg(), ivSplashBg);
        ivSplashBg.animate().scaleX(1.12f).scaleY(1.12f).setDuration(2000).setStartDelay(100).start();
        tvSplashAuthor.setText(splashBean.getText());
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
    public void showError(String msg) {

    }

    @Override
    protected void onDestroy() {
        Glide.clear(ivSplashBg);
        super.onDestroy();
    }
}
