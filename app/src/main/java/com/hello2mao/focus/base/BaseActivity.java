package com.hello2mao.focus.base;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.hello2mao.focus.app.App;
import com.hello2mao.focus.di.component.ActivityComponent;
import com.hello2mao.focus.di.component.DaggerActivityComponent;
import com.hello2mao.focus.di.module.ActivityModule;
import com.hello2mao.focus.log.BasicLog;
import com.hello2mao.focus.log.LogManager;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * MVP activity基类
 */
public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity
        implements BaseView {

    @Inject
    protected T presenter;
    protected Activity context;
    protected static final BasicLog LOG = LogManager.getInstance();
    private Unbinder unBinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        unBinder = ButterKnife.bind(this);
        context = this;
        initInject();
        if (null != presenter) {
            presenter.attachView(this);
        }
        App.getInstance().addActivity(this);
        initEventAndData();
    }

    protected ActivityComponent getActivityComponent(){
        return  DaggerActivityComponent.builder()
                .appComponent(App.getAppComponent())
                .activityModule(getActivityModule())
                .build();
    }

    protected ActivityModule getActivityModule(){
        return new ActivityModule(this);
    }


    @Override
    protected void onDestroy() {
        if (presenter != null) {
            presenter.detachView();
        }
        unBinder.unbind();
        App.getInstance().removeActivity(this);
        super.onDestroy();
    }

    protected abstract int getLayout();
    protected abstract void initInject();
    protected abstract void initEventAndData();
}
