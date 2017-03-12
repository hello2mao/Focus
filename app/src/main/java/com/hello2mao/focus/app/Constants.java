package com.hello2mao.focus.app;


import java.io.File;

public class Constants {

    // Global Setting
    public static final boolean DEBUG = true;
    public static final String GLOBAL_TAG = "Focus";
    public static final String DATA = "data";

    //================= TYPE ====================
    public static final int TYPE_ZHIHU = 101;
    public static final int TYPE_SETTING = 110;
    public static final int TYPE_LIKE = 111;
    public static final int TYPE_ABOUT = 112;

    //================= PATH ====================
    public static final String PATH_DATA = App.getInstance().getCacheDir().getAbsolutePath()
            + File.separator + "data";
    public static final String PATH_CACHE = PATH_DATA + "/NetCache";

    //================= PREFERENCE ====================
    public static final String SP_CURRENT_ITEM = "current_item";

}
