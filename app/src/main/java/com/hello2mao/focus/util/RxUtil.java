package com.hello2mao.focus.util;


import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RxUtil {

    /**
     * 统一线程处理：后台线程取数据、主线程显示
     * 事件产生在Schedulers.io()上，即读写文件、读写数据库、网络信息交互
     * 事件消费在AndroidSchedulers.mainThread()上，即android主线程
     * @param <T> T
     * @return Observable.Transformer
     */
    public static <T> Observable.Transformer<T, T> rxSchedulerHelper() {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> observable) {
                return observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
}