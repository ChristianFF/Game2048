package com.ff.util;

import android.app.Activity;
import android.os.Build;

import com.ff.R;
import com.readystatesoftware.systembartint.SystemBarTintManager;

/**
 * UI工具类.
 * Created by FF on 2015/12/22.
 */
public class UIUtils {

    private UIUtils() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static void setUpSystemBar(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 创建状态栏的管理实例
            SystemBarTintManager tintManager = new SystemBarTintManager(activity);
            // 激活状态栏设置
            tintManager.setStatusBarTintEnabled(true);
            // 设置一个颜色给系统栏
            tintManager.setTintColor(R.color.colorPrimaryDark);
        }
    }
}