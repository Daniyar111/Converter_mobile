package com.example.saint.convertproject.ui;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public abstract class BaseActivity extends AppCompatActivity{

    protected abstract int getViewLayout();
    protected abstract int getToolbarId();

    private Toolbar mToolbar;

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        setContentView(getViewLayout());
    }

    protected void getToolbar(String title, boolean back){

        mToolbar = findViewById(getToolbarId());
        setSupportActionBar(mToolbar);
        if(getSupportActionBar() != null){
            getSupportActionBar().setTitle(title);
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(back);
    }
}
