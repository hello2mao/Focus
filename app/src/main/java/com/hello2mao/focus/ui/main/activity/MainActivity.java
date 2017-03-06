package com.hello2mao.focus.ui.main.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.hello2mao.focus.R;
import com.hello2mao.focus.app.App;
import com.hello2mao.focus.base.BaseActivity;
import com.hello2mao.focus.presenter.main.MainPresenter;
import com.hello2mao.focus.presenter.main.contract.MainContract;
import com.hello2mao.focus.ui.main.fragment.BottomNavFragment;
import com.hello2mao.focus.ui.main.fragment.OnTabReselectListener;
import com.hello2mao.focus.ui.main.widget.BottomNavButton;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View,
        BottomNavFragment.OnNavigationReselectListener {

    private BottomNavFragment bottomNav;


    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void initEventAndData() {
        FragmentManager fm = getSupportFragmentManager();
        bottomNav = (BottomNavFragment) fm.findFragmentById(R.id.bottom_nav);
        bottomNav.setup(context, fm, R.id.main_container, this);
    }

    @Override
    public void onBackPressed() {
        showExitDialog();
    }

    private void showExitDialog() {
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(this);
        builder.setTitle("提示");
        builder.setMessage("确定退出Focus吗");
        builder.setNegativeButton("取消", null);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                App.getInstance().exitApp();
            }
        });
        builder.show();
    }

    @Override
    public void onReselect(BottomNavButton bottomNavButton) {
        Fragment fragment = bottomNavButton.getFragment();
        if (fragment != null
                && fragment instanceof OnTabReselectListener) {
            OnTabReselectListener listener = (OnTabReselectListener) fragment;
            // 图标被在此点击时调用相应的fragment中的onTabReselect
            listener.onTabReselect();
        }
    }

    /**
     * 底部导航栏的显示与隐藏
     * @param isShowOrHide 显示或隐藏
     */
    public void toggleNavTabView(boolean isShowOrHide) {
        final View view = bottomNav.getView();
        if (view == null) return;
        // hide
        view.setVisibility(View.VISIBLE);
        if (!isShowOrHide) {
            view.animate()
                    .translationY(view.getHeight())
                    .setDuration(180)
                    .setInterpolator(new LinearInterpolator())
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            view.setTranslationY(view.getHeight());
                            view.setVisibility(View.GONE);
                        }
                    });
        } else { // show
            view.animate()
                    .translationY(0)
                    .setDuration(180)
                    .setInterpolator(new LinearInterpolator())
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            // fix:bug > 点击隐藏的同时，快速点击显示
                            view.setVisibility(View.VISIBLE);
                            view.setTranslationY(0);
                        }
                    });
        }
    }

    @Override
    public void showError(String msg) {

    }
}
