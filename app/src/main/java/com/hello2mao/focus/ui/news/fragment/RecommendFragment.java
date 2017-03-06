package com.hello2mao.focus.ui.news.fragment;


import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.hello2mao.focus.R;
import com.hello2mao.focus.base.BaseFragment;
import com.hello2mao.focus.model.bean.DailyListBean;
import com.hello2mao.focus.presenter.news.RecommendPresenter;
import com.hello2mao.focus.presenter.news.contract.RecommendContract;
import com.hello2mao.focus.ui.news.adapter.RecommendRVAdapter;
import com.hello2mao.focus.ui.news.widget.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

public class RecommendFragment extends BaseFragment<RecommendPresenter> implements RecommendContract.View,
        BGARefreshLayout.BGARefreshLayoutDelegate {

    @BindView(R.id.rl_recyclerview_refresh)
    BGARefreshLayout refreshLayout;
    @BindView(R.id.rv_recyclerview_data)
    RecyclerView dataRv;

    private RecommendRVAdapter adapter;
    List<DailyListBean.StoriesBean> list = new ArrayList<>();

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_recommend;
    }

    @Override
    protected void initEventAndData() {
        // pullToRefresh
        refreshLayout.setDelegate(this);
        refreshLayout.setRefreshViewHolder(new BGANormalRefreshViewHolder(context, true));
        adapter = new RecommendRVAdapter(context, list);
        dataRv.setAdapter(adapter);
        // 使用addOnScrollListener，而不是setOnScrollListener();
        dataRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                LOG.debug("测试自定义onScrollStateChanged被调用");
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                LOG.debug("测试自定义onScrolled被调用");
            }
        });
        dataRv.addItemDecoration(new DividerItemDecoration(context,
                DividerItemDecoration.VERTICAL_LIST));
        dataRv.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));

    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(final BGARefreshLayout refreshLayout) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                refreshLayout.endRefreshing();
                dataRv.smoothScrollToPosition(0);
            }
        }, 2000);

    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        refreshLayout.endLoadingMore();
        return true;
    }

    @Override
    public void showContent(DailyListBean info) {
        list = info.getStories();
    }

    @Override
    public void showError(String msg) {

    }
}
