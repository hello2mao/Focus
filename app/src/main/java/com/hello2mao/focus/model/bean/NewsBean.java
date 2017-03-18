package com.hello2mao.focus.model.bean;


import java.util.List;

public class NewsBean {

    private boolean readState;

    /* common */
    private String group_id;
    // article/video/gallery/ad
    private String article_genre;
    private String title;
    private String source;
    private int comments_count;
    private long behot_time;

    private String image_url;


    /* article */

    /* video */

    /* gallery */
    private boolean has_gallery;
    private int gallary_image_count;
    private List<ImageUrlBean> image_list;

    /* ad */



    public boolean isReadState() {
        return readState;
    }

    public void setReadState(boolean readState) {
        this.readState = readState;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getArticle_genre() {
        return article_genre;
    }

    public void setArticle_genre(String article_genre) {
        this.article_genre = article_genre;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public int getComments_count() {
        return comments_count;
    }

    public void setComments_count(int comments_count) {
        this.comments_count = comments_count;
    }

    public long getBehot_time() {
        return behot_time;
    }

    public void setBehot_time(long behot_time) {
        this.behot_time = behot_time;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public boolean isHas_gallery() {
        return has_gallery;
    }

    public void setHas_gallery(boolean has_gallery) {
        this.has_gallery = has_gallery;
    }

    public int getGallary_image_count() {
        return gallary_image_count;
    }

    public void setGallary_image_count(int gallary_image_count) {
        this.gallary_image_count = gallary_image_count;
    }

    public List<ImageUrlBean> getImage_list() {
        return image_list;
    }

    public void setImage_list(List<ImageUrlBean> image_list) {
        this.image_list = image_list;
    }
}
