package com.hello2mao.focus.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hello2mao.focus.app.App;
import com.hello2mao.focus.di.component.DaggerFragmentComponent;
import com.hello2mao.focus.di.component.FragmentComponent;
import com.hello2mao.focus.di.module.FragmentModule;
import com.hello2mao.focus.log.BasicLog;
import com.hello2mao.focus.log.LogManager;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * MVP Fragment基类
 */
public abstract class BaseFragment<T extends BasePresenter> extends Fragment
        implements BaseView {

    protected static final BasicLog LOG = LogManager.getInstance();
    @Inject
    protected T presenter;
    protected View root;
    protected Activity activity;
    protected Context context;
    protected LayoutInflater inflater;
    private Unbinder unBinder;
    protected boolean isInited = false;

    @Override
    public void onAttach(Context context) {
        activity = (Activity) context;
        this.context = context;
        super.onAttach(context);
    }

    protected FragmentComponent getFragmentComponent(){
        return DaggerFragmentComponent.builder()
                .appComponent(App.getAppComponent())
                .fragmentModule(getFragmentModule())
                .build();
    }

    protected FragmentModule getFragmentModule(){
        return new FragmentModule(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        if (root != null) {
            ViewGroup parent = (ViewGroup) root.getParent();
            if (parent != null) {
                parent.removeView(root);
            }
        } else {
            root = inflater.inflate(getLayoutId(), container, false);
            this.inflater = inflater;
        }
        initInject();
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.attachView(this);
        unBinder = ButterKnife.bind(this, view);
        if (!isHidden()) {
            isInited = true;
            initEventAndData();
        } else {
            LOG.error("create fragment failed!");
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!isInited && !hidden) {
            isInited = true;
            initEventAndData();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unBinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (presenter != null) presenter.detachView();
    }

    protected abstract void initInject();
    protected abstract int getLayoutId();
    protected abstract void initEventAndData();
}