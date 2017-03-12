package com.hello2mao.focus.ui.news.fragment;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.hello2mao.focus.R;
import com.hello2mao.focus.app.Constants;
import com.hello2mao.focus.base.BaseFragment;
import com.hello2mao.focus.model.bean.NewsBean;
import com.hello2mao.focus.presenter.news.NewsListPresenter;
import com.hello2mao.focus.presenter.news.contract.NewsListContract;
import com.hello2mao.focus.ui.news.adapter.NewsListRVAdapter;
import com.hello2mao.focus.ui.news.widget.DividerItemDecoration;

import java.util.List;

import butterknife.BindView;
import cn.bingoogolapple.androidcommon.adapter.BGAOnItemChildClickListener;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

public class NewsListFragment extends BaseFragment<NewsListPresenter> implements NewsListContract.View,
        BGARefreshLayout.BGARefreshLayoutDelegate, BGAOnItemChildClickListener {

    @BindView(R.id.rl_recyclerview_refresh)
    BGARefreshLayout refreshLayout;
    @BindView(R.id.rv_recyclerview_data)
    RecyclerView dataRv;

    private NewsListRVAdapter adapter;

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_news_list;
    }

    @Override
    protected void initEventAndData() {
        refreshLayout.setDelegate(this);
        refreshLayout.setRefreshViewHolder(new BGANormalRefreshViewHolder(context, true));
        adapter = new NewsListRVAdapter(dataRv, context);
        adapter.setOnItemChildClickListener(this);
        dataRv.setAdapter(adapter);
        dataRv.addItemDecoration(new DividerItemDecoration(context,
                DividerItemDecoration.VERTICAL_LIST));
        dataRv.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        presenter.getContent(getArguments().getString(Constants.DATA));
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(final BGARefreshLayout refreshLayout) {
        LOG.debug("onBGARefreshLayoutBeginRefreshing");
        presenter.getContent(getArguments().getString(Constants.DATA));
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        LOG.debug("onBGARefreshLayoutBeginLoadingMore");
        this.refreshLayout.endLoadingMore();
        return false;
    }

    @Override
    public void showContent(List<NewsBean> newsList) {
        LOG.debug("showContent, size=" + newsList.size());
        if (refreshLayout.getCurrentRefreshStatus() == BGARefreshLayout.RefreshStatus.REFRESHING) {
            refreshLayout.endRefreshing();
        }
        adapter.addNewData(newsList);
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void onItemChildClick(ViewGroup parent, View childView, int position) {
        switch (childView.getId()) {
            case R.id.tv_recommend_item_title:
                LOG.debug("title click");
                break;
            case R.id.iv_recommend_item_image:
                LOG.debug("image click");
                break;
            default:
                break;
        }
    }
}
