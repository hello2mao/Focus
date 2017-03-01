package com.hello2mao.focus.ui.news.fragment;


import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.hello2mao.focus.R;
import com.hello2mao.focus.base.BaseFragment;
import com.hello2mao.focus.presenter.NewsMainPresenter;
import com.hello2mao.focus.presenter.contract.NewsMainContract;
import com.hello2mao.focus.ui.main.fragment.OnTabReselectListener;
import com.hello2mao.focus.ui.news.adapter.NewsPagerAdapter;

import butterknife.BindView;

public class NewsMainFragment extends BaseFragment<NewsMainPresenter> implements
        NewsMainContract.View, OnTabReselectListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
//    @BindView(R.id.view_search)
//    MaterialSearchView searchView;

    private DailyFragment dailyFragment;
    private ThemeFragment themeFragment;
    NewsPagerAdapter newsPagerAdapter;
    String[] tabTitle = new String[]{"日报", "主题", "专栏", "热门", "其他"};

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

        dailyFragment = new DailyFragment();
        themeFragment = new ThemeFragment();
        newsPagerAdapter = new NewsPagerAdapter(getChildFragmentManager());
        newsPagerAdapter.addTab(dailyFragment, tabTitle[0]);
        newsPagerAdapter.addTab(themeFragment, tabTitle[1]);
        viewPager.setAdapter(newsPagerAdapter);

        //TabLayout配合ViewPager有时会出现不显示Tab文字的Bug,需要按如下顺序
        tabLayout.addTab(tabLayout.newTab().setText(tabTitle[0]));
        tabLayout.addTab(tabLayout.newTab().setText(tabTitle[1]));
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setText(tabTitle[0]);
        tabLayout.getTabAt(1).setText(tabTitle[1]);

        setHasOptionsMenu(true);

    }

    @Override
    public void onTabReselect() {
        Toast.makeText(context, "Double News", Toast.LENGTH_SHORT).show();

    }

//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        inflater.inflate(R.menu.main_menu, menu);
//        MenuItem item = menu.findItem(R.id.action_search);
//        item.setVisible(true);
//        searchView.setMenuItem(item);
//    }


//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        menu.clear();
////        inflater.inflate(R.menu.menu_parent_fragment, menu);
//    }
}
