package com.hello2mao.focus.model.bean;


public class NewsBean {

    private String title;
    private String image_url;
    private String group_id;

    private boolean readState;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public boolean isReadState() {
        return readState;
    }

    public void setReadState(boolean readState) {
        this.readState = readState;
    }
}
