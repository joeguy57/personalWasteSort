/**
 * GameActivity.java
 * <p>This activity is going to show the gamePlay aspect of the application. There is a
 * fixed amount of bins for the objects to be sorted into.
 * It will create bin objects from the Bin.java class, as well as take information from
 * objects in the WasteItems.java class</p>
 *
 * @author Jared Matson unless specified otherwise
 */
package com.example.wastesortapp;

import android.content.ClipData;
import android.content.ClipData.Item;
import android.content.ClipDescription;
import android.media.Image;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class GameActivity extends AppCompatActivity {
  public static Bin[] theBins = new Bin[5];

  private ImageView apple;
  private ViewGroup mainLayout;
  private int xDelta;
  private int yDelta;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_game);
    theBins[0] = new Bin("Green", "Organics");
    theBins[1] = new Bin("Blue", "Recyclables");
    theBins[2] = new Bin("Yellow", "Yellow");
    theBins[3] = new Bin("Black", "Landfill");
    theBins[4] = new Bin("Gray", "None of the above");


     apple = findViewById(R.id.testApple);
  }//onCreate

    
}//gameActivity



