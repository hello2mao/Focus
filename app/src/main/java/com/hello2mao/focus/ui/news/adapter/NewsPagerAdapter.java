package com.hello2mao.focus.ui.news.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * FIXME:考虑使用FragmentStatePagerAdapter
 * FragmentStatePagerAdapter 内存占用较小，所以适合大量动态页面，比如我们常见的新闻列表类应用。
 * http://www.jianshu.com/p/2b5a177ea67d
 */
public class NewsPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments = new ArrayList<>();
    private String[] tabTitles;

    public NewsPagerAdapter(FragmentManager fm, List<Fragment> fragments, String[] tabTitles) {
        super(fm);
        this.fragments = fragments;
        this.tabTitles = tabTitles;
    }

    /**
     * Return the Fragment associated with a specified position.
     */
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    /**
     * Return the number of views available.
     */
    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles == null ? "" : tabTitles[position];
    }
}
