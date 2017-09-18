package com.example.whoareyou.helloworld;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
    /*
        实现动画效果
     */
public class MainActivity extends ListActivity {
    private class Sample{
        private CharSequence title;
        private Class<? extends Activity> activityClass;
        public Sample(int titleResId,Class<? extends Activity> activityClass){
            this.activityClass = activityClass;
            this.title = getResources().getString(titleResId);
        }
        @Override
        public String toString(){
            return title.toString();
        }
    }
    private static Sample[] mSamples;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSamples = new Sample[]{
                new Sample(
                        R.string.title_crossfade,
                        CrossfadeActivity.class),
                new Sample(
                        R.string.title_card_flip,
                        CradFlipActivity.class),
                new Sample(
                        R.string.title_screen_slide,
                        ScreenSlideActivity.class),
                new Sample(
                        R.string.title_zoom,
                        ZoomActivity.class),
                new Sample(
                        R.string.title_layout_changes,
                        LayoutChangesActivity.class),
        };
        setListAdapter(new ArrayAdapter<Sample>(this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                mSamples));

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        startActivity(new Intent(MainActivity.this
        ,mSamples[position].activityClass));
    }
}
