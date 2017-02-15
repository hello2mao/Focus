package com.hello2mao.focus.component;


import android.app.Activity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class ImageLoader {

    // 使用Glide加载圆形ImageView(如头像)时，不要使用占位图
    public static void load(Activity activity, String url, ImageView iv) {
        if(!activity.isDestroyed()) {
            Glide.with(activity).load(url).crossFade().diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(iv);
        }
    }
}
