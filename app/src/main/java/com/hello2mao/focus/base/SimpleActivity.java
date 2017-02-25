package com.hello2mao.focus.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.hello2mao.focus.app.App;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 无MVP的activity基类
 */
public abstract class SimpleActivity extends AppCompatActivity {

    protected Activity context;
    private Unbinder unBinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        unBinder = ButterKnife.bind(this);
        context = this;
        App.getInstance().addActivity(this);
        initEventAndData();
    }

//    protected void setToolBar(Toolbar toolbar, String title) {
//        toolbar.setTitle(title);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                onBackPressedSupport();
//            }
//        });
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        App.getInstance().removeActivity(this);
        unBinder.unbind();
    }

    protected abstract int getLayout();
    protected abstract void initEventAndData();
}
