package com.hello2mao.focus.util;


import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import com.hello2mao.focus.theme.ColorUi.ColorUiInterface;

public class ViewAttributeUtil {

    public static int getAttributeValue(AttributeSet attr, int paramInt) {
        int value = -1;
        int count = attr.getAttributeCount();
        for (int i = 0; i < count; i++) {
            if (attr.getAttributeNameResource(i) == paramInt) {
                String str = attr.getAttributeValue(i);
//                if (null != str) {
                if (null != str && (str.startsWith("?") || paramInt == android.R.attr.src
                        || paramInt == android.R.attr.background)) {
                    //因为要动态改图片，图片没有用自定义属性
                    value = Integer.valueOf(str.substring(1, str.length()));
                    return value;
                }
            }
        }
        return value;
    }

    public static int getBackgroundAttribute(AttributeSet attr) {
        return getAttributeValue(attr, android.R.attr.background);
    }

    public static void applyBackgroundDrawable(ColorUiInterface ci, Resources.Theme theme, int paramInt) {
        TypedArray ta = theme.obtainStyledAttributes(new int[]{paramInt});
        Drawable drawable = ta.getDrawable(0);
        if (null != ci) {
            (ci.getView()).setBackgroundDrawable(drawable);
        }
        ta.recycle();
    }
}
