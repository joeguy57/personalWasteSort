package com.example.wastesortapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class game extends AppCompatActivity {
    TextView colorTextView;
    TextView itemTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        colorTextView = findViewById(R.id.ColorTextView);
        itemTextView = findViewById(R.id.ItemNameTextView);
        
        GarbageItems items = new GarbageItems(1);
        
        colorTextView.setText(items.Color);
        itemTextView.setText(items.itemName);
        
    }
}
