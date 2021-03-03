package com.example.smartparking.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.example.smartparking.R;

public class Splash extends AppCompatActivity {
Button sub;
ImageView imageView2;
Animation frombottom,fromtop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        sub=findViewById(R.id.button);
        imageView2=findViewById(R.id.imageView2);
        frombottom= AnimationUtils.loadAnimation(this,R.anim.frobottom);
        fromtop= AnimationUtils.loadAnimation(this,R.anim.fromtop);
     sub.setAnimation(frombottom);
     imageView2.setAnimation(fromtop);
     sub.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             startActivity(new Intent(Splash.this, LoginActivity.class));
         }
     });
    }
}