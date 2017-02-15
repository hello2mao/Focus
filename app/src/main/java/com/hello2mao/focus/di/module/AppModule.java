package com.hello2mao.focus.di.module;


import com.hello2mao.focus.app.App;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;


@Module
public class AppModule {

    private final App application;

    public AppModule(App application) {
        this.application = application;
    }

    @Provides
    @Singleton
    App provideApplicationContext() {
        return application;
    }

//    @Provides
//    @Singleton
//    RetrofitHelper provideRetrofitHelper() {
//        return new RetrofitHelper();
//    }
//
//    @Provides
//    @Singleton
//    RealmHelper provideRealmHelper() {
//        return new RealmHelper(application);
//    }
}
