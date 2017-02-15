package com.hello2mao.focus.di.component;


import com.hello2mao.focus.app.App;
import com.hello2mao.focus.di.module.AppModule;
import com.hello2mao.focus.model.http.RetrofitHelper;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    App getContext();  // 提供App的Context

    RetrofitHelper retrofitHelper();  //提供http的帮助类
//
//    RealmHelper realmHelper();    //提供数据库帮助类

}
