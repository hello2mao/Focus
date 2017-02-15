package com.hello2mao.focus.app;


import java.io.File;

public class Constants {

    // Global Setting
    public static final boolean DEBUG = true;
    public static final String GLOBAL_TAG = "Focus";

    //================= PATH ====================

    public static final String PATH_DATA = App.getInstance().getCacheDir().getAbsolutePath()
            + File.separator + "data";

    public static final String PATH_CACHE = PATH_DATA + "/NetCache";

}
