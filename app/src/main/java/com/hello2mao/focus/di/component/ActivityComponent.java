package com.hello2mao.focus.di.component;

import android.app.Activity;

import com.hello2mao.focus.di.ActivityScope;
import com.hello2mao.focus.di.module.ActivityModule;
import com.hello2mao.focus.ui.main.activity.MainActivity;
import com.hello2mao.focus.ui.main.activity.SplashActivity;

import dagger.Component;


@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    Activity getActivity();

    void inject(SplashActivity splashActivity);

    void inject(MainActivity mainActivity);

//    void inject(ZhihuDetailActivity zhihuDetailActivity);
//
//    void inject(ThemeActivity themeActivity);
//
//    void inject(SectionActivity sectionActivity);
//
//    void inject(RepliesActivity repliesActivity);
//
//    void inject(NodeListActivity nodeListActivity);
}
