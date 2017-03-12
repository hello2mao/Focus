package com.hello2mao.focus.ui.news.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.hello2mao.focus.R;
import com.hello2mao.focus.app.Constants;
import com.hello2mao.focus.base.BaseFragment;
import com.hello2mao.focus.presenter.news.NewsMainPresenter;
import com.hello2mao.focus.presenter.news.contract.NewsMainContract;
import com.hello2mao.focus.ui.main.fragment.OnTabReselectListener;
import com.hello2mao.focus.ui.news.adapter.NewsPagerAdapter;
import com.hello2mao.focus.ui.news.widget.ColorTrackTabViewIndicator;
import com.hello2mao.focus.ui.news.widget.ColorTrackView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class NewsMainFragment extends BaseFragment<NewsMainPresenter> implements
        NewsMainContract.View, OnTabReselectListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tab)
    ColorTrackTabViewIndicator tab;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    private List<Fragment> fragments;
    private NewsPagerAdapter newsPagerAdapter;
    private String[] tabTitles = new String[]{"推荐", "视频", "热点", "社会", "娱乐", "科技", "汽车",
            "体育", "财经", "军事", "国际", "时尚", "游戏", "旅游", "历史", "探索", "美食", "育儿", "养生",
            "故事", "美文"};
    private String[] titlesCode = new String[]{"__all__", "video", "news_hot", "news_society",
            "news_entertainment", "news_tech", "news_car", "news_sports", "news_finance",
            "news_military", "news_world", "news_fashion", "news_game", "news_travel",
            "news_history", "news_discovery", "news_food", "news_baby", "news_regimen",
            "news_story", "news_essay"};


    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_news_main;
    }

    @Override
    protected void initEventAndData() {
        ((AppCompatActivity) activity).setSupportActionBar(toolbar);
        fragments = new ArrayList<>();
        for (int i = 0; i < tabTitles.length; i++) {
            NewsListFragment newsListFragment = new NewsListFragment();
            Bundle bundle = new Bundle();
            bundle.putString(Constants.DATA, titlesCode[i]);
            newsListFragment.setArguments(bundle);
            fragments.add(newsListFragment);
        }
        newsPagerAdapter = new NewsPagerAdapter(getChildFragmentManager(), fragments, tabTitles);
        viewPager.setAdapter(newsPagerAdapter);
        tab.setTitles(tabTitles, new ColorTrackTabViewIndicator.ColorTrackTabBack() {
            @Override
            public void onClickButton(Integer position, ColorTrackView colorTrackView) {
                viewPager.setCurrentItem(position, false);
            }
        });
        final View tabChild = tab.getChildAt(0);
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        //重新测量
        tabChild.measure(w, h);
        //设置最小宽度，使其可以在滑动一部分距离
        tabChild.setMinimumWidth(tabChild.getMeasuredWidth() + tab.getTabWidth());
        viewPager.setOffscreenPageLimit(tabTitles.length);
        tab.setupViewPager(viewPager);
        setHasOptionsMenu(true);

    }

    @Override
    public void onTabReselect() {
        Toast.makeText(context, "Double News", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showError(String msg) {

    }

}
