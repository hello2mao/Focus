package com.hello2mao.focus.util;


import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

public class StatusBarUtil {

    /**
     * 设置状态栏全透明
     *
     * @param activity 需要设置的activity
     */
    public static void setTransparent(Activity activity) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            return;
        }
        transparentStatusBar(activity);
//        setRootView(activity);
    }

    /**
     * 使状态栏透明
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    private static void transparentStatusBar(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
        } else {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    /**
     * 设置根布局参数
     */
    private static void setRootView(Activity activity) {
        ViewGroup parent = (ViewGroup) activity.findViewById(android.R.id.content);
        for (int i = 0, count = parent.getChildCount(); i < count; i++) {
            View childView = parent.getChildAt(i);
            if (childView instanceof ViewGroup) {
                childView.setFitsSystemWindows(true);
                ((ViewGroup) childView).setClipToPadding(true);
            }
        }
    }

    /**
     * 为DrawerLayout 布局设置状态栏变色
     * 参考：http://www.jianshu.com/p/86ca7c873cb8
     * @param activity       需要设置的activity
     * @param drawerLayout   DrawerLayout
     * @param color          状态栏颜色值
     * @param statusBarAlpha 状态栏透明度
     */
    public static void setColorForDrawerLayout(Activity activity, DrawerLayout drawerLayout, @ColorInt int color,
                                               int statusBarAlpha) {
//        //系统小于4.4
//        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
//            return;
//        }
//        //5.0以上 直接设置状态栏为透明色
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
//        } else {
//            //4.4-5.0之间先设置透明状态栏
//            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        }
//        // 生成一个状态栏大小的矩形
//        // 添加 statusBarView 到布局中
//        ViewGroup contentLayout = (ViewGroup) drawerLayout.getChildAt(0);
//        View fakeStatusBarView = contentLayout.findViewById(FAKE_STATUS_BAR_VIEW_ID);
//        if (fakeStatusBarView != null) {
//            if (fakeStatusBarView.getVisibility() == View.GONE) {
//                fakeStatusBarView.setVisibility(View.VISIBLE);
//            }
//            fakeStatusBarView.setBackgroundColor(color);
//        } else {
//            contentLayout.addView(createStatusBarView(activity, color), 0);
//        }
//        // 内容布局不是 LinearLayout 时,设置padding top
//        if (!(contentLayout instanceof LinearLayout) && contentLayout.getChildAt(1) != null) {
//            contentLayout.getChildAt(1)
//                    .setPadding(contentLayout.getPaddingLeft(), getStatusBarHeight(activity) + contentLayout.getPaddingTop(),
//                            contentLayout.getPaddingRight(), contentLayout.getPaddingBottom());
//        }
//        // 设置属性
//        setDrawerLayoutProperty(drawerLayout, contentLayout);
//        //将状态栏设置一定的透明度
//        addTranslucentView(activity, statusBarAlpha);
    }

}
