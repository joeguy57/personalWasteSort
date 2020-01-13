package com.example.wastesortapp;

import android.os.Build.VERSION_CODES;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import com.example.wastesortapp.GarbageItems.DataCountCallback;
import com.example.wastesortapp.GarbageItems.FirebaseCallback;


public class game extends AppCompatActivity {

  TextView colorTextView;
  TextView itemTextView;
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

    final GarbageItems garbageItems = new GarbageItems();
    garbageItems.readRandomKey(new DataCountCallback() {
      @Override
      public void onCallback(int result) {
        setRandomKey(result);
        getRandomKey();
        garbageItems.readColorData(new FirebaseCallback() {
          @Override
          public void onCallback(String result) {
            setColor(result);
            garbageItems.readItemNameData(new FirebaseCallback() {
              @Override
              public void onCallback(String result) {
                setItemName(result);
                itemTextView.setText(getItemName());
              }
            }, getRandomKey());
            colorTextView.setText(getColor());
          }
        },getRandomKey());
      }
    });


  }

  public int getRandomKey() {
    return randomKey;
  }

  public void setRandomKey(int randomKey) {
    this.randomKey = randomKey;
  }

  public String getColor() {
    return Color;
  }

  public void setColor(String color) {
    this.Color = color;
  }

  public String getItemName() {
    return itemName;
  }

  public void setItemName(String itemName) {
    this.itemName = itemName;

  }


}
