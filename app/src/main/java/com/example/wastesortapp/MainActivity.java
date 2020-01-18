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

    protected Intent goToActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);

        Button gameBtn = findViewById(R.id.gameBtn);
        Button disposableHelpBtn = findViewById(R.id.disposableHelpBtn);
        Button moreInfo = findViewById(R.id.moreInfoBtn);

        gameBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               startActivity(goToActivity = new Intent(getApplicationContext(), game.class));
               finish();

           }
       });

       disposableHelpBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               startActivity(goToActivity = new Intent(getApplicationContext(), DisposableHelp.class));

           }
       });

        moreInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(goToActivity = new Intent(getApplicationContext(), ContactUs.class));

            }
        });

    }


}
