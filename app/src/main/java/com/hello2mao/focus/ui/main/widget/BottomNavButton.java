package com.hello2mao.focus.ui.main.widget;

import android.content.Context;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.annotation.RequiresApi;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.hello2mao.focus.R;

/**
 * 自定义主界面底部的导航栏中的按钮
 * 三个部分：（1）按钮图标（2）底部文字（3）新消息提示小红点
 */
public class BottomNavButton extends FrameLayout {

    private Fragment fragment = null;
    private Class<?> clx;
    // 按钮图标
    private ImageView iconView;
    // 按钮说明文字
    private TextView titleView;
    // 新消息提示小红点
    private TextView dot;
    private String tag;

    public BottomNavButton(Context context) {
        super(context);
        init();
    }

    public BottomNavButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BottomNavButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public BottomNavButton(Context context, AttributeSet attrs, int defStyleAttr, int
            defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        inflater.inflate(R.layout.layout_nav_item, this, true);
        iconView = (ImageView) findViewById(R.id.nav_iv_icon);
        titleView = (TextView) findViewById(R.id.nav_tv_title);
        dot = (TextView) findViewById(R.id.nav_tv_dot);
    }

    /**
     * 初始化
     * @param resId 图标
     * @param strId 图标下的文字
     * @param clx 对应的fragment
     */
    public void initView(@DrawableRes int resId, @StringRes int strId, Class<?> clx) {
        this.iconView.setImageResource(resId);
        this.titleView.setText(strId);
        this.clx = clx;
        this.tag = clx.getName();
    }

    /**
     * 设置图标选中状态
     * @param selected 是否选中
     */
    @Override
    public void setSelected(boolean selected) {
        super.setSelected(selected);
        iconView.setSelected(selected);
        titleView.setSelected(selected);
    }

    /**
     * 展示新消息的小红点
     * @param count 新消息的个数
     */
    public void showRedDot(int count) {
        dot.setVisibility(count > 0 ? VISIBLE : GONE);
        dot.setText(String.valueOf(count));
    }

    public Fragment getFragment() {
        return fragment;
    }

    public Class<?> getClx() {
        return clx;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }

    public String getTag() {
        return tag;
    }

}
