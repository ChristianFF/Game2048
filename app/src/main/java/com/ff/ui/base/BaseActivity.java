package com.ff.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.ff.util.UIUtils;

/**
 * 快速开发的activity基础框架类，提供了getView来简化findViewById,并且提供了构建框架.
 * Created by feifan on 16/1/9.
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected String TAG = this.getClass().getSimpleName();

    protected Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        beforeSetContentView();
        if (getContentViewId() != 0) {
            setContentView(getContentViewId());
        }
        findViews();
        beforeSetViews();
        setViews();
    }

    /**
     * 在setContentView之前触发的方法
     */
    protected void beforeSetContentView() {
        UIUtils.setUpSystemBar(this);
    }

    /**
     * 如果没有布局，那么就返回0
     *
     * @return activity的布局文件
     */
    protected abstract int getContentViewId();

    /**
     * 找到所有的views
     */
    protected abstract void findViews();

    /**
     * 在这里初始化设置view的各种资源，比如适配器或各种变量
     */
    protected void beforeSetViews(){

    }

    /**
     * 设置所有的view
     */
    protected abstract void setViews();


    /**
     * 通过泛型来简化findViewById
     */
    protected final <E extends View> E getView(int id) {
        try {
            return (E) findViewById(id);
        } catch (ClassCastException ex) {
            Log.e(TAG, "Could not cast View to concrete class.", ex);
            throw ex;
        }
    }
}
