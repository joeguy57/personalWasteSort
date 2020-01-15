package com.example.wastesortapp;

import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.widget.TextView;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;


public class game extends AppCompatActivity {

  private TextView colorTextView;
  private TextView itemTextView;
  private String Color;
  private String itemName;
  private int randomKey;

  @RequiresApi(api = VERSION_CODES.N)
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_game);

    colorTextView = findViewById(R.id.itemColor);
    itemTextView = findViewById(R.id.itemName);



  }



  private int getRandomKey() {
    return randomKey;
  }

  private void setRandomKey(int randomKey) {
    this.randomKey = randomKey;
  }

  private String getColor() {
    return Color;
  }

  private void setColor(String color) {
    this.Color = color;
  }

  private String getItemName() {
    return itemName;
  }

  private void setItemName(String itemName) {
    this.itemName = itemName;

  }


}
