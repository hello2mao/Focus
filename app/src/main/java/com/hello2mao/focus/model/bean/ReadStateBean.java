package com.hello2mao.focus.model.bean;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class ReadStateBean extends RealmObject {

    @PrimaryKey
    private String id;

    public ReadStateBean() { }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
