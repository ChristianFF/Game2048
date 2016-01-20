package com.ff.ui;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.ff.BuildConfig;
import com.ff.R;
import com.ff.ui.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AboutActivity extends BaseActivity {

    @Bind(R.id.txt_version)
    TextView txtVersion;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @Bind(R.id.fab)
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_about;
    }

    @Override
    protected void findViews() {
        ButterKnife.bind(this);
    }

    @Override
    protected void setViews() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        collapsingToolbar.setTitle(getResources().getString(R.string.string_2048));

        txtVersion.setText("版本信息:" + BuildConfig.VERSION_NAME);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "欢迎提出意见~喜欢的话可以关注我的GitHub哦~", Snackbar.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return true;
    }
}
