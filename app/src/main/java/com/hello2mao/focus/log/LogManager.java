package com.hello2mao.focus.log;

public class LogManager {

    private volatile static DefaultBasicLog instance = null;

    private LogManager() {}

    public static BasicLog getInstance() {
        if (null == instance) {
            synchronized (LogManager.class) {
                if (null == instance) {
                    instance = new DefaultBasicLog();
                }
            }
        }
        return instance;
    }

    public static void setLog(BasicLog instance2) {
        instance.setImpl(instance2);
    }
}
