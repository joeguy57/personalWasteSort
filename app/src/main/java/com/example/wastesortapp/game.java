/**
 * GameActivity.java
 * <p>This activity is going to show the gamePlay aspect of the application. There is a
 * fixed amount of bins for the objects to be sorted into. It will create bin objects from the
 * Bin.java class, as well as take information from objects in the WasteItems.java class</p>
 *
 * @author Jared Matson unless specified otherwise
 */
package com.example.wastesortapp;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.Random;

public class game extends AppCompatActivity implements
    ImageView.OnDragListener  {

  private ObjectAnimator mAnimation;
  private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
  private DatabaseReference databaseReference = firebaseDatabase.getReference();
  private DatabaseReference first = databaseReference.child("image");
  public static Bin[] theBins = new Bin[5];

  private ImageView blackBin;
  private ConstraintLayout constraintLayout;
  private ConstraintLayout dropLayoutGreen;
  private ConstraintLayout dropLayoutBlue;
  private ConstraintLayout dropLayoutYellow;
  private ConstraintLayout dropLayoutBlack;
  private ConstraintLayout itemSpawnLocation;

  private String color;
  private TextView scoreView;
  private int score = 0;
  private static final String TAG = "MyActivity";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_game);

    ImageView backBtn = findViewById(R.id.backBtn);
    final ProgressBar progressBar = (ProgressBar) findViewById(R.id.timerBar);
    backBtn.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        finish();
      }
    });
    mAnimation = ObjectAnimator.ofInt(progressBar, "progress", 100, 0);
    mAnimation.setDuration(60000);
    mAnimation.setInterpolator(new DecelerateInterpolator());
    mAnimation.addListener(new Animator.AnimatorListener() {
      @Override
      public void onAnimationStart(Animator animator) {
      }
      @Override
      public void onAnimationEnd(Animator animator) {
        //do something when the countdown is complete
      }

      @Override
      public void onAnimationCancel(Animator animator) {
      }

      @Override
      public void onAnimationRepeat(Animator animator) {
      }
    });
  }

  @Override
  protected void onStart() {
    super.onStart();
    mAnimation.start();
    first.addValueEventListener(new ValueEventListener() {
      @Override
      public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                String link = dataSnapshot.getValue(String.class);
//                Picasso.get().load(link).into(imageView);
      }

      @Override
      public void onCancelled(@NonNull DatabaseError databaseError) {

      }
    });
    findItems();
    grabNewItem();

  }//onCreate

  public void findItems() {
    constraintLayout = findViewById(R.id.ConstraintLayoutDrop);
    scoreView = findViewById(R.id.scoreView);
    dropLayoutGreen = findViewById(R.id.dropLayoutGreen);
    dropLayoutBlue = findViewById(R.id.dropLayoutBlue);
    dropLayoutYellow = findViewById(R.id.dropLayoutYellow);
    dropLayoutBlack = findViewById(R.id.dropLayoutBlack);
    itemSpawnLocation = findViewById(R.id.itemSpawnLocation);
//    theBins[0] = new Bin("Green", "Organics");
//    theBins[1] = new Bin("Blue", "Recyclables");
//    theBins[2] = new Bin("Yellow", "Yellow");
//    theBins[3] = new Bin("Black", "Landfill");
//    theBins[4] = new Bin("Gray", "None of the above");
    setItemAttributes();
  }
  public void setItemAttributes(){
    constraintLayout.setOnDragListener(this);
    dropLayoutGreen.setOnDragListener(this);
    dropLayoutBlue.setOnDragListener(this);
    dropLayoutYellow.setOnDragListener(this);
    dropLayoutBlack.setOnDragListener(this);
    itemSpawnLocation.setOnDragListener(this);
    dropLayoutGreen.setTag("Green");
    dropLayoutBlue.setTag("Blue");
    dropLayoutYellow.setTag("Yellow");
    dropLayoutBlack.setTag("Black");
//    itemSpawnLocation.setTag("Outside");
//    constraintLayout.setTag("Outside");
  }
  public void checkForPoint(String binChoice){
    if(binChoice == color) {
      increaseScore(true);
      grabNewItem();
    }//if
     else if(binChoice != constraintLayout.getTag() && binChoice != itemSpawnLocation.getTag()){
       increaseScore(false);
       grabNewItem();
     }//if

  }//checkForPoints

  public void increaseScore(boolean wasPointScored){
    if(wasPointScored == true) {
      score += 1;
    }
    else{
      score -= 1;
    }
    scoreView.setText(""+ score);
  }//changeScore
  public void grabNewItem(){
    garbageItems item = new garbageItems(this, itemSpawnLocation);
    color = item.getColor();
  }
  @Override
//  public boolean onTouch(View v, MotionEvent event) {
//    DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
//    v.startDrag(null, shadowBuilder, v, 0);
//    v.setVisibility(v.INVISIBLE);
//    return true;
//  }

  public boolean onDrag(View v, DragEvent event) {
    switch (event.getAction()) {
      case DragEvent.ACTION_DRAG_STARTED:
        Log.d(TAG, "onDrag: ACTION_DRAG_STARTED ");
        break;
      case DragEvent.ACTION_DRAG_ENTERED:
        Log.d(TAG, "onDrag: ACTION_DRAG_ENTERED ");
        break;
      case DragEvent.ACTION_DRAG_EXITED:
        Log.d(TAG, "onDrag: ACTION_DRAG_EXITED ");
        break;
      case DragEvent.ACTION_DROP:
        View view = (View) event.getLocalState();
       // ViewGroup owner = (ViewGroup) view.getParent();
        ConstraintLayout container = (ConstraintLayout) v;
        if(container.getTag() == null){
          view.setVisibility(view.VISIBLE);
        }
        //container.addView(view);
        //view.setVisibility(View.VISIBLE);
        System.out.println("CONTAINER " + container.getTag());
        checkForPoint((String) container.getTag());
        break;
      case DragEvent.ACTION_DRAG_ENDED:
        Log.d(TAG, "onDrag: ACTION_DRAG_ENDED ");
      default:
        break;
    }//switch
    return true;

  }
}//gameActivity