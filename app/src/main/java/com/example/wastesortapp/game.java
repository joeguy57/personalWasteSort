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

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipData.Item;
import android.content.ClipDescription;
import android.content.DialogInterface.OnClickListener;
import android.media.Image;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class game extends AppCompatActivity implements ImageView.OnTouchListener, ImageView.OnDragListener{

    public static Bin[] theBins = new Bin[5];

    private ImageView apple;
    private ImageView blackBin;
    private ImageView blueBin;
    private ImageView greenBin;
    private ImageView yellowBin;
    private static final String IMAGE_VIEW_TAG = "LAUNCHER LOGO";
    MotionEvent event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        theBins[0] = new Bin("Green", "Organics");
        theBins[1] = new Bin("Blue", "Recyclables");
        theBins[2] = new Bin("Yellow", "Yellow");
        theBins[3] = new Bin("Black", "Landfill");
        theBins[4] = new Bin("Gray", "None of the above");


        blackBin = findViewById(R.id.trashCan);
        blueBin = findViewById(R.id.recycleBin);
        greenBin = findViewById(R.id.organicBin);
        yellowBin = findViewById(R.id.beverageBin);
        blackBin.setOnTouchListener(this);
        blackBin.setOnDragListener(this);
        blackBin.setTag(IMAGE_VIEW_TAG);

    }//onCreate

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        ClipData.Item item = new ClipData.Item((CharSequence)v.getTag());
        String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};
        ClipData data = new ClipData(v.getTag().toString(), mimeTypes, item);
        DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
        v.startDrag(data, shadowBuilder, null, 0);
        v.setVisibility(v.INVISIBLE);
        return true;
    }
    public boolean onDrag(View v, DragEvent event) {
        float x = 0;
        float y = 0;
        switch (event.getAction()) {

        }//switch

    return true;
    }//onDrag
}//gameActivity