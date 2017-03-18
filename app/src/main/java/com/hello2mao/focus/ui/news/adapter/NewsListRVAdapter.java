package com.hello2mao.focus.ui.news.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.hello2mao.focus.R;
import com.hello2mao.focus.component.ImageLoader;
import com.hello2mao.focus.log.BasicLog;
import com.hello2mao.focus.log.LogManager;
import com.hello2mao.focus.model.bean.NewsBean;
import com.hello2mao.focus.util.DateUtil;

import cn.bingoogolapple.androidcommon.adapter.BGARecyclerViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

public class NewsListRVAdapter extends BGARecyclerViewAdapter<NewsBean> {

    private Context context;
    private static final BasicLog LOG = LogManager.getInstance();
    // article/video/gallery/ad
    private static final String ARTICLE_GENRE_ARTICLE = "article";
    private static final String ARTICLE_GENRE_VIDEO = "video";
    private static final String ARTICLE_GENRE_GALLERY = "gallery";
    private static final String ARTICLE_GENRE_AD = "ad";

    public NewsListRVAdapter(RecyclerView recyclerView, Context context) {
        super(recyclerView, R.layout.item_news);
        this.context = context;
    }

    @Override
    protected void fillData(BGAViewHolderHelper helper, int position, NewsBean model) {
        helper.setText(R.id.tvTitle, model.getTitle())
                .setText(R.id.tvSource, model.getSource())
                .setText(R.id.tvCommentsCount, model.getComments_count() + "评论");
        if (model.getBehot_time() != 0) {
            helper.setText(R.id.tvBehotTime, DateUtil.getShortTime(model.getBehot_time() * 1000));
        }
        switch (model.getArticle_genre()) {
            case ARTICLE_GENRE_ARTICLE:
                // 文章
                if (model.getImage_list() == null || model.getImage_list().size() == 0) {
                  // 单图片
                    if (!TextUtils.isEmpty(model.getImage_url())) {
                        ImageLoader.load(context, model.getImage_url(), helper.getImageView(R.id.ivRightImg1));
                        helper.setVisibility(R.id.rlRightImg, View.VISIBLE)
                                .setVisibility(R.id.viewFill, View.VISIBLE);
                    }
                } else {
                    // 3图片
                    ImageLoader.load(context, model.getImage_list().get(0).getUrl(), helper.getImageView(R.id.ivCenterImg1));
                    ImageLoader.load(context, model.getImage_list().get(1).getUrl(), helper.getImageView(R.id.ivCenterImg2));
                    ImageLoader.load(context, model.getImage_list().get(2).getUrl(), helper.getImageView(R.id.ivCenterImg3));
                    helper.setVisibility(R.id.llCenterImg, View.VISIBLE);
                }
                break;
            case ARTICLE_GENRE_VIDEO:
                // 视频
                if (!TextUtils.isEmpty(model.getImage_url())) {
                    ImageLoader.load(context, model.getImage_url(), helper.getImageView(R.id.ivBigVideo));
                    helper.setVisibility(R.id.rlBigVideo, View.VISIBLE);
                }
                break;
            case ARTICLE_GENRE_GALLERY:
                // 图集
                if (model.getImage_list() == null) {
                    ImageLoader.load(context, model.getImage_url(), helper.getImageView(R.id.ivBigImg));
                } else {
                    ImageLoader.load(context, model.getImage_list().get(0).getUrl(), helper.getImageView(R.id.ivBigImg));
                }
                helper.setText(R.id.tvImgCount, model.getGallary_image_count() + "图")
                        .setVisibility(R.id.rlBigImg, View.VISIBLE);
                break;
            case ARTICLE_GENRE_AD:
                break;
            default:
                LOG.error("no such article_genre: " + model.getArticle_genre());
        }
    }

    @Override
    protected void setItemChildListener(BGAViewHolderHelper helper, int viewType) {

    }


}
