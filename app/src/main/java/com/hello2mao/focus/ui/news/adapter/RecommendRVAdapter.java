package com.hello2mao.focus.ui.news.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hello2mao.focus.R;
import com.hello2mao.focus.model.bean.DailyListBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecommendRVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<DailyListBean.StoriesBean> list;
    private LayoutInflater inflater;

    public RecommendRVAdapter(Context context, List<DailyListBean.StoriesBean> list) {
        this.context = context;
        this.list = list;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ContentViewHolder(inflater.inflate(R.layout.item_news_recommend, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ContentViewHolder) holder).title.setText("xxxxxxxx");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ContentViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_recommend_item_title)
        TextView title;
        @BindView(R.id.iv_recommend_item_image)
        ImageView image;

        public ContentViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public void  addData(DailyListBean info) {
        list = info.getStories();
    }
}
