package com.hello2mao.focus.di.component;

import android.app.Activity;

import com.hello2mao.focus.di.FragmentScope;
import com.hello2mao.focus.di.module.FragmentModule;
import com.hello2mao.focus.ui.explore.fragment.ExploreMainFragment;
import com.hello2mao.focus.ui.me.fragment.MeMainFragment;
import com.hello2mao.focus.ui.news.fragment.DailyFragment;
import com.hello2mao.focus.ui.news.fragment.NewsMainFragment;
import com.hello2mao.focus.ui.news.fragment.ThemeFragment;
import com.hello2mao.focus.ui.video.fragment.VideoMainFragment;

import dagger.Component;

@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {

    Activity getActivity();

    void inject(NewsMainFragment newsMainFragment);

    void inject(VideoMainFragment videoMainFragment);

    void inject(ExploreMainFragment exploreMainFragment);

    void inject(MeMainFragment meMainFragment);

    void inject(DailyFragment dailyFragment);

    void inject(ThemeFragment themeFragment);

//    void inject(ZhihuDailyFragment dailyFragment);
//
//    void inject(ZhihuThemeFragment themeFragment);
//
//    void inject(SectionFragment sectionFragment);
//
//    void inject(HotFragment hotFragment);
//
//    void inject(CommentFragment longCommentFragment);
//
//    void inject(TechFragment techFragment);
//
//    void inject(GirlFragment girlFragment);
//
//    void inject(LikeFragment likeFragment);
//
//    void inject(WechatMainFragment wechatMainFragment);
//
//    void inject(SettingFragment settingFragment);
//
//    void inject(GoldMainFragment goldMainFragment);
//
//    void inject(GoldPagerFragment goldPagerFragment);
//
//    void inject(VtexPagerFragment vtexPagerFragment);
}
