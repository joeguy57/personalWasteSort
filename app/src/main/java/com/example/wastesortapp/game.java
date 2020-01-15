package com.example.wastesortapp;

import android.os.Build.VERSION_CODES;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.example.wastesortapp.GarbageItems.DataCountCallback;
import com.example.wastesortapp.GarbageItems.FirebaseCallback;

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

    onGameStart();

  }

  private void onGameStart() {
    final GarbageItems garbageItems = new GarbageItems();
    runAsync(garbageItems);
  }

  private void runAsync(final GarbageItems garbageItems) {
    garbageItems.readRandomKey(new DataCountCallback() {
      @Override
      public void onCallback(int result) {
        setRandomKey(result);
        garbageItems.readItemNameData(new FirebaseCallback() {
          @Override
          public void onCallback(String result) {
            setItemName(result);
            colorTextView.setText(getItemName()); // put in code to make comparison with constraint tags
          }
        }, getRandomKey());
        garbageItems.readColorData(new FirebaseCallback() {
          @Override
          public void onCallback(String result) {
            setColor(result);
            itemTextView.setText(getColor());
          }
        }, getRandomKey());
        //garbageItem.readImageData(new FirebaseCallback() {) // not a string may need a new access point
      }
    });
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
