package com.hello2mao.focus.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.hello2mao.focus.app.App;
import com.hello2mao.focus.app.Constants;

public class SharedPreferenceUtil {

    private static final int DEFAULT_CURRENT_ITEM = Constants.TYPE_ZHIHU;

    private static final String SHARED_PREFERENCES_NAME = "my_sp";

    public static SharedPreferences getAppSp() {
        return App.getInstance().getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public static int getCurrentItem() {
        return getAppSp().getInt(Constants.SP_CURRENT_ITEM, DEFAULT_CURRENT_ITEM);
    }

    public static void setCurrentItem(int item) {
        getAppSp().edit().putInt(Constants.SP_CURRENT_ITEM, item).apply();
    }
}
