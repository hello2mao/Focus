package com.hello2mao.focus.ui.news.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.hello2mao.focus.R;
import com.hello2mao.focus.component.ImageLoader;
import com.hello2mao.focus.model.bean.NewsBean;

import cn.bingoogolapple.androidcommon.adapter.BGARecyclerViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

public class NewsListRVAdapter extends BGARecyclerViewAdapter<NewsBean> {

    private Context context;

    public NewsListRVAdapter(RecyclerView recyclerView, Context context) {
        super(recyclerView, R.layout.item_news_recommend);
        this.context = context;
    }

    @Override
    protected void fillData(BGAViewHolderHelper helper, int position, NewsBean model) {
        helper.setText(R.id.tv_recommend_item_title, model.getTitle());
        ImageLoader.load(context, model.getImage_url(), helper.getImageView(R.id.iv_recommend_item_image));
    }

    @Override
    protected void setItemChildListener(BGAViewHolderHelper helper, int viewType) {
        helper.setItemChildClickListener(R.id.iv_recommend_item_image);
        helper.setItemChildClickListener(R.id.tv_recommend_item_title);
    }


}
