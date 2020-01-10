package com.example.wastesortapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button customButton = findViewById(R.id.button);

        customButton.setOnClickListener(new CompoundButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                customButton.setEnabled(true);
            }
        });

    }
}
