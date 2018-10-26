package com.example.rapha.transpotsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

import org.litepal.LitePal;

/**
 * Created by raphael on 2017/5/19.
 */

public class StartActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final View v=View.inflate(StartActivity.this,R.layout.activity_fullscreen,null);
        setContentView(v);

        LitePal.getDatabase();

        AlphaAnimation aa=new AlphaAnimation(0.3f,1.0f);
        aa.setDuration(2000);
        v.startAnimation(aa);
        aa.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Intent intent=new Intent(StartActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
