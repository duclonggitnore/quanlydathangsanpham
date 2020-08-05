package com.example.project_android.Giaodien;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.project_android.R;

public class Loading extends AppCompatActivity {
    AnimationDrawable wifiAnimation;
    ImageView imgCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.draw_long);

        ImageView imageView = (ImageView) findViewById(R.id.image);
        imageView.setBackgroundResource(R.drawable.loading);
        wifiAnimation = (AnimationDrawable) imageView.getBackground();

        imgCart = findViewById(R.id.imgCart);
        Animation anim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.dichuyen);
        imgCart.startAnimation(anim);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        final Intent intent = new Intent(Loading.this,MainActivity.class);
        wifiAnimation.start();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent1 = new Intent(Loading.this, MainActivity.class);

                startActivity(intent);
            }
        }, 3000);

    }
}
