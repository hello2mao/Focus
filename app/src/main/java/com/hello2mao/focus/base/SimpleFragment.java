package com.hello2mao.focus.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hello2mao.focus.log.BasicLog;
import com.hello2mao.focus.log.LogManager;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 无MVP的Fragment基类
 */
public abstract class SimpleFragment extends Fragment {

    protected static final BasicLog LOG = LogManager.getInstance();
    protected View root;
    protected Activity activity;
    protected Context context;
    protected Bundle bundle;
    protected LayoutInflater inflater;
    private Unbinder unBinder;
    private boolean isInited = false;

    @Override
    public void onAttach(Context context) {
        activity = (Activity) context;
        this.context = context;
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle = getArguments();
        initBundle(bundle);
    }

    @Nullable
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
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unBinder = ButterKnife.bind(this, root);
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

    protected void initBundle(Bundle bundle) {

    }


    protected abstract int getLayoutId();
    protected abstract void initEventAndData();
}
