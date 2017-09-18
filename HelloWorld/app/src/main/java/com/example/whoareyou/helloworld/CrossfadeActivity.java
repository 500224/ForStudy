package com.example.whoareyou.helloworld;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

/**
 * Created by whoareyou on 17-9-15.
 * 主要实现的是加载界面和内容界面的交替使用，包括界面的隐藏和显示
 * 包括菜单栏的使用和home键实现返回主页
 *
 * question 1：实现菜单栏的用途
 */

public class CrossfadeActivity extends Activity{
    /*
    判断文本显示的状态
     */
    private boolean mContentLoaded;
    /*
    包含文本的视图
     */
    private View mContentView;
    /*
    正在加载的视图
     */
    private View mLoadingView;
    /*
    设置动画的最小时间
     */
    private int mShortAnimationDuration;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crossfade);

        mContentView = findViewById(R.id.content);
        mLoadingView = findViewById(R.id.loading_spinner);
        mContentView.setVisibility(View.GONE);

        mShortAnimationDuration = getResources().
                getInteger(android.R.integer.config_shortAnimTime);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_crossfade,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                NavUtils.navigateUpTo(this,new Intent(this,MainActivity.class));
                return true;
            case R.id.cation_toggle:
                mContentLoaded = !mContentLoaded;
                showContentOrLoadingIndicator(mContentLoaded);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void showContentOrLoadingIndicator(boolean contentLoaded){
        final View showView = contentLoaded ? mContentView:mLoadingView;
        final View hideView = contentLoaded ? mLoadingView:mContentView;
        showView.setAlpha(0f);
        showView.setVisibility(View.VISIBLE);
        showView.animate().alpha(1f).setDuration(
                mShortAnimationDuration
        ).setListener(null);
        hideView.animate().alpha(0f).setDuration(
                mShortAnimationDuration
        ).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                hideView.setVisibility(View.GONE);
            }
        });
    }
}
