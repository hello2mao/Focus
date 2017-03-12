package com.hello2mao.focus.model.db;

import android.content.Context;

import com.hello2mao.focus.model.bean.ReadStateBean;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;


public class RealmHelper {

    private static final String DB_NAME = "myRealm.realm";

    private Realm realm;

    public RealmHelper(Context context) {
        Realm.init(context);
        realm = Realm.getInstance(new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .name(DB_NAME)
                .build());
    }

    /**
     * 增加 阅读记录
     * @param id
     * 使用@PrimaryKey注解后copyToRealm需要替换为copyToRealmOrUpdate
     */
    public void insertNewsId(String id) {
        ReadStateBean bean = new ReadStateBean();
        bean.setId(id);
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(bean);
        realm.commitTransaction();
    }

    /**
     * 查询 阅读记录
     * @param id id
     * @return boolean
     */
    public boolean queryNewsId(String id) {
        RealmResults<ReadStateBean> results = realm.where(ReadStateBean.class).findAll();
        for(ReadStateBean item : results) {
            if(item.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

}
