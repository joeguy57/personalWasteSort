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
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintLayout;

public class game extends AppCompatActivity implements ImageView.OnTouchListener, ImageView.OnDragListener{

    public static Bin[] theBins = new Bin[5];

    private ImageView garbageItem;
    private ImageView blackBin;
    private int blueBin = 2131296314 ;
    private int greenBin = 2131296315;
    private int yellowBin = 2131296316;
    private ConstraintLayout constraintLayout;
    private ConstraintLayout dropLayoutGreen;
    private ConstraintLayout dropLayoutBlue;
    private ConstraintLayout dropLayoutYellow;

  private static final String TAG = "MyActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        theBins[0] = new Bin("Green", "Organics");
        theBins[1] = new Bin("Blue", "Recyclables");
        theBins[2] = new Bin("Yellow", "Yellow");
        theBins[3] = new Bin("Black", "Landfill");
        theBins[4] = new Bin("Gray", "None of the above");

        garbageItem = findViewById(R.id.appleboy);
         blackBin = findViewById(R.id.trashCan);
//        blueBin = findViewById(R.id.recycleBin);
//        greenBin = findViewById(R.id.organicBin);
//        yellowBin = findViewById(R.id.beverageBin);
        blackBin.setOnTouchListener(this);
        blackBin.setOnDragListener(this);
        constraintLayout = findViewById(R.id.ConstraintLayoutDrop);
        constraintLayout.setOnDragListener(this);
        dropLayoutGreen = findViewById(R.id.dropLayoutGreen);
        dropLayoutBlue = findViewById(R.id.dropLayoutBlue);
        dropLayoutYellow = findViewById(R.id.dropLayoutYellow);

        dropLayoutGreen.setOnDragListener(this);
        dropLayoutBlue.setOnDragListener(this);
        dropLayoutYellow.setOnDragListener(this);



    }//onCreate

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        ClipData.Item item = new ClipData.Item((CharSequence)v.getTag());
//        String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};
//        ClipData data = new ClipData(v.getTag().toString(), mimeTypes, item);
        DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
        v.startDrag(null, shadowBuilder, v, 0);
        v.setVisibility(v.INVISIBLE);
        return true;
    }
    public boolean onDrag(View v, DragEvent event) {
        switch (event.getAction()) {
          case DragEvent.ACTION_DRAG_STARTED:
            Log.d(TAG,"onDrag: ACTION_DRAG_STARTED ");
            break;
          case DragEvent.ACTION_DRAG_ENTERED:
            Log.d(TAG, "onDrag: ACTION_DRAG_ENTERED ");
            break;
          case DragEvent.ACTION_DRAG_EXITED:
            Log.d(TAG, "onDrag: ACTION_DRAG_EXITED ");
            break;
          case DragEvent.ACTION_DROP:
            View view = (View) event.getLocalState();
            ViewGroup owner = (ViewGroup) view.getParent();
            owner.removeView(view);
            ConstraintLayout container = (ConstraintLayout) v;
            container.addView(view);
            view.setVisibility(View.VISIBLE);
            System.out.println("CONTAINER " + container.getId());
            if (container.getId() == 0) {
              System.out.println("CORRECT CHOICE");
            } else {
              System.out.println("WRONG CHOICE");
            }
            break;
          case DragEvent.ACTION_DRAG_ENDED:
            Log.d(TAG, "onDrag: ACTION_DRAG_ENDED ");
          default:
            break;
        }//switch
      return true;

  }
  public void what(View v){

  }
}//gameActivity