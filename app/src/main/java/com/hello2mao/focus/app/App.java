package com.hello2mao.focus.app;

import android.app.Activity;
import android.app.Application;

import com.hello2mao.focus.di.component.AppComponent;
import com.hello2mao.focus.di.component.DaggerAppComponent;
import com.hello2mao.focus.di.module.AppModule;
import com.hello2mao.focus.log.AndroidLogImpl;
import com.hello2mao.focus.log.BasicLog;
import com.hello2mao.focus.log.LogManager;
import com.hello2mao.focus.log.NullLog;

import java.util.HashSet;
import java.util.Set;


/**
 * 全局应用程序类：用于保存和调用全局应用配置及访问网络数据
 */
public class App extends Application {

    private static App instance;
    private static final BasicLog LOG = LogManager.getInstance();
    private Set<Activity> allActivities;
    public static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        init();
    }

    private void init() {
        // Crash handler
        AppCrashHandler handler = AppCrashHandler.getInstance();
//        handler.init(this);

        // Init for com.rey.material
//        ThemeManager.init(this, 2, 0, null);

        // Log
        BasicLog basicLog;
        if (Constants.DEBUG) {
            basicLog = new AndroidLogImpl();
        } else {
            basicLog = new NullLog();
        }
        LogManager.setLog(basicLog);
        LOG.setLevel(BasicLog.DEBUG);
    }

    public static App getInstance() {
        return instance;
    }

    public void addActivity(Activity act) {
        if (null == allActivities) {
            allActivities = new HashSet<>();
        }
        allActivities.add(act);
    }

    public void removeActivity(Activity act) {
        if (null != allActivities) {
            allActivities.remove(act);
        }
    }

    public void exitApp() {
        if (null != allActivities) {
            synchronized (allActivities) {
                for (Activity act : allActivities) {
                    act.finish();
                }
            }
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }

    public static AppComponent getAppComponent(){
        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(instance))
                    .build();
        }
        return appComponent;
    }

}
