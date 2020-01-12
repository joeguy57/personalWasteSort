package com.example.wastesortapp;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.view.animation.DecelerateInterpolator;


public class MainActivity extends AppCompatActivity {
    private ObjectAnimator mAnimation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button gameBtn = findViewById(R.id.gameBtn);
        Button disposableHelpBtn = findViewById(R.id.disposableHelpBtn);
        Button moreInfo = findViewById(R.id.moreInfoBtn);
        final ProgressBar progressBar = (ProgressBar)findViewById(R.id.progressBar);


        gameBtn.setOnClickListener(new CompoundButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameBtn.setEnabled(true);
                mAnimation.start();
            }
        });
        mAnimation = ObjectAnimator.ofInt(progressBar, "progress" , 100, 0);
        mAnimation.setDuration(7000);
        mAnimation.setInterpolator(new DecelerateInterpolator());
        mAnimation.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                //do something when the countdown is complete

            }

            @Override
            public void onAnimationCancel(Animator animator) {
            }

            @Override
            public void onAnimationRepeat(Animator animator) {
            }
        });

       gameBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent goToActivity = new Intent(getApplicationContext(), game.class);
               startActivity(goToActivity);
           }
       });

       disposableHelpBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent goToActivity = new Intent(getApplicationContext(), DisposableHelp.class);
               startActivity(goToActivity);
           }
       });

        moreInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToActivity = new Intent(getApplicationContext(), ContactUs.class);
                startActivity(goToActivity);
            }
        });

    }
}
