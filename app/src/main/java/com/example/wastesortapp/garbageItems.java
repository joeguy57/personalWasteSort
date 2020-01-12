package com.example.wastesortapp;

import android.content.ClipData;
import android.content.Context;
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

public class garbageItems implements ImageView.OnTouchListener{
  private String color;
  private String information;
  private Context context;
  private final String TAG = "myActivity";
  private game game;


  public garbageItems(String color, String information, Context context, ConstraintLayout constraintLayout){
    this.color = color;
    this.information = information;
    this.context = context;
    ImageView item = new ImageView(context);
    item.setBackgroundResource(R.drawable.appletest);
    constraintLayout.addView(item);
    item.setOnTouchListener(this);

  }//garbageItems
  public String getColor() {
    return color;
  }
//HAVE TO DO POINT SCORE IN THIS CLASS
  public boolean onTouch(View v, MotionEvent event) {
    DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
    v.startDrag(null, shadowBuilder, v, 0);
    v.setVisibility(v.INVISIBLE);
    return true;
  }

}
