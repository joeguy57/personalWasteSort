package com.example.wastesortapp;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.Random;

public class garbageItems implements ImageView.OnTouchListener{
  private String color;
  private Context context;
  private int ImageList[] = new int[]{R.drawable.appletest,R.drawable.popcanboi};
  private String [] colorList = new String[]{"Green","Yellow"};
  private Random randomGen = new Random();
  private int chosenNum;

  /**
   * Constructor for garbageItems
   * @param context the context that the item is being created in
   * @param constraintLayout which layout the item will be created in
   */
  public garbageItems(Context context, ConstraintLayout constraintLayout){
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
  public int getRandomNumber(){
    return randomGen.nextInt(2);
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
