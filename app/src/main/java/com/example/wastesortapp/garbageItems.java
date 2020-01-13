package com.example.wastesortapp;

import android.content.ClipData;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnDragListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.Random;

public class garbageItems implements ImageView.OnTouchListener{
  private String color;
  private String information;
  private Context context;
  private game game;
  private int ImageList[] = new int[]{R.drawable.appletest,R.drawable.popcanboi};
  private String [] colorList = new String[]{"Green","Blue"};
  private Random randomGen;
  private int randomNum;
  private int chosenNum;

  public garbageItems(Context context, ConstraintLayout constraintLayout){
    this.information = information;
    this.context = context;
    chosenNum = getRandomNumber();
    color = colorList[chosenNum];
    ImageView item = new ImageView(context);
    item.setBackgroundResource(ImageList[chosenNum]);
    constraintLayout.addView(item);
    item.setOnTouchListener(this);

  }//garbageItems

  /**
   * Returns color of bin item goes in
   * @return color
   */
  public String getColor() {
    return color;
  }
  public Integer getRandomNumber(){
    randomNum = randomGen.nextInt(2);
    return randomNum;
  }
  /**
   * Allows for items with setOnTouchListener to be dragged around
   * @param v the item that is being dragged
   * @param event how it is being moved
   * @return if the item has been dragged
   */
  public boolean onTouch(View v, MotionEvent event) {
    DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
    v.startDrag(null, shadowBuilder, v, 0);
    v.setVisibility(v.INVISIBLE);
    return true;
  }
}
