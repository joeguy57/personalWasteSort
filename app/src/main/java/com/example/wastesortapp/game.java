package com.example.wastesortapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class game extends AppCompatActivity {
    TextView colorTextView;
    TextView itemTextView;
    String Color;
    String Item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        
        colorTextView = findViewById(R.id.ColorTextView);
        itemTextView = findViewById(R.id.ItemNameTextView);
        GarbageItems items = new GarbageItems();
        colorTextView.setText(items.getColor());
        itemTextView.setText(items.getItemName());
    }
}
