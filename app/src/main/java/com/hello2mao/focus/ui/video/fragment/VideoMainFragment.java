package com.hello2mao.focus.ui.video.fragment;


import android.widget.Toast;

import com.hello2mao.focus.R;
import com.hello2mao.focus.base.BaseFragment;
import com.hello2mao.focus.presenter.VideoMainPresenter;
import com.hello2mao.focus.presenter.contract.VideoMainContract;
import com.hello2mao.focus.ui.main.fragment.OnTabReselectListener;

public class VideoMainFragment extends BaseFragment<VideoMainPresenter>
        implements VideoMainContract.View, OnTabReselectListener {

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_video_main;
    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    public void onTabReselect() {
        Toast.makeText(context, "Double Video", Toast.LENGTH_SHORT).show();

    }
}
