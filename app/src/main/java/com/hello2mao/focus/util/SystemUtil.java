package com.hello2mao.focus.util;


import android.content.Context;
import android.net.ConnectivityManager;

import com.hello2mao.focus.app.App;

public class SystemUtil {

    /**
     * 检查是否有可用网络
     */
    public static boolean isNetworkConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) App.getInstance().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null;
    }
}
