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
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
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
import com.squareup.picasso.Picasso;
import java.util.Random;

public class game extends AppCompatActivity implements  ImageView.OnDragListener,
          ImageView.OnTouchListener {

  ImageView imageView2;
  private ObjectAnimator mAnimation;
  private DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
  //private DatabaseReference databaseReference = firebaseDatabase.getReference();
  private DatabaseReference imagesUrlsRef = rootRef.child("gameObjects");
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
  boolean wasThereDrop = false;
  Sound sound = new Sound(this);



  //-----------------------------------------------------------------------------------
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    sound.initializeIncorrectSound();
    sound.initializeCorrectSound();
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_game);

    DisplayMetrics displayMetrics = new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);


    ImageView backBtn = findViewById(R.id.backBtn);
    final ProgressBar progressBar = (ProgressBar) findViewById(R.id.timerBar);
    backBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        finish();
      }
    });
    mAnimation = ObjectAnimator.ofInt(progressBar, "progress", 100, 0);
    mAnimation.setDuration(7000);
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

    createNewImages();
    findItems();


//    grabNewItem();

  }//onStart


  protected void createNewImages() {
    imagesUrlsRef.addValueEventListener(new ValueEventListener() {
      @Override
      public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        //  ImageView imageGet = findViewById(R.id.imageGet);
        long numChildern =  dataSnapshot.getChildrenCount();
        //TextView urlNum = findViewById(R.id.textView3);
        String num = String.valueOf(numChildern);
        //urlNum.setText(num);
        int randNum = new Random().nextInt(Integer.parseInt(num));
        String rand = String.valueOf(randNum + 1);
        String link = dataSnapshot.child(rand).child("Image").getValue(String.class);
        String data = dataSnapshot.child(rand).child("Color").getValue(String.class);
        //urlNum.setText(data + " " + rand);
        // itemSpawnLocation.addView();
        color = data;
        Picasso.get().load(link).into(imageView2);
        imageView2.setVisibility(View.VISIBLE);

      }

      @Override
      public void onCancelled(@NonNull DatabaseError databaseError) {

      }
    });
  }//createNewImages


  public void findItems() {
    constraintLayout = findViewById(R.id.ConstraintLayoutDrop);
    scoreView = findViewById(R.id.scoreView);
    dropLayoutGreen = findViewById(R.id.dropLayoutGreen);
    dropLayoutBlue = findViewById(R.id.dropLayoutBlue);
    dropLayoutYellow = findViewById(R.id.dropLayoutYellow);
    dropLayoutBlack = findViewById(R.id.dropLayoutBlack);
    itemSpawnLocation = findViewById(R.id.itemSpawnLocation);
    imageView2 = findViewById(R.id.imageView2);
    setItemAttributes();
  }
  public void setItemAttributes(){
    constraintLayout.setOnDragListener(this);
    dropLayoutGreen.setOnDragListener(this);
    dropLayoutBlue.setOnDragListener(this);
    dropLayoutYellow.setOnDragListener(this);
    dropLayoutBlack.setOnDragListener(this);
    itemSpawnLocation.setOnDragListener(this);
    imageView2.setOnTouchListener(this);
    dropLayoutGreen.setTag("Green");
    dropLayoutBlue.setTag("Blue");
    dropLayoutYellow.setTag("Yellow");
    dropLayoutBlack.setTag("Black");
    itemSpawnLocation.setTag("Outside");
    constraintLayout.setTag("Outside");
  }
  public void checkForPoint(String binChoice){
//    if(binChoice == null || wasThereDrop == false){
//      imageView2.setVisibility(View.VISIBLE);
//    }
    //System.out.println("COMPARISON "+ binChoice + " CORRECT ANSWER " + color);
    if(binChoice.equals("Outside")){
      imageView2.setVisibility(View.VISIBLE);
    }
    else if(binChoice.equals(color)) {
      increaseScore(true);
      createNewImages();
      sound.playCorrectSound();

    }//if
     else if(binChoice != constraintLayout.getTag() && binChoice != itemSpawnLocation.getTag()){
       increaseScore(false);
       createNewImages();
       sound.playIncorrectSound();
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
//  public void grabNewItem(){
//    garbageItems item = new garbageItems(this, itemSpawnLocation);
//    color = item.getColor();
//  }
 // @Override
//  public boolean onTouch(View v, MotionEvent event) {
//    DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
//    v.startDrag(null, shadowBuilder, v, 0);
//    v.setVisibility(v.INVISIBLE);
//    return true;
//  }

  public boolean onDrag(View v, DragEvent event) {

    ConstraintLayout container = null;
    switch (event.getAction()) {
      case DragEvent.ACTION_DRAG_STARTED:
        wasThereDrop = false;
        Log.d(TAG, "onDrag: ACTION_DRAG_STARTED ");
        break;
      case DragEvent.ACTION_DRAG_ENTERED:
        Log.d(TAG, "onDrag: ACTION_DRAG_ENTERED ");
        break;
      case DragEvent.ACTION_DRAG_EXITED:
        Log.d(TAG, "onDrag: ACTION_DRAG_EXITED ");
        break;
      case DragEvent.ACTION_DROP:
        Log.d(TAG,"onDrag: ACTION_DRAG_DROP");
        View view = (View) event.getLocalState();
       // ViewGroup owner = (ViewGroup) view.getParent();
        container = (ConstraintLayout) v;
        //container.addView(view);
        //view.setVisibility(View.VISIBLE);
        System.out.println("CONTAINER " + container.getTag());
        wasThereDrop = true;
        checkForPoint((String) container.getTag());

        break;
      case DragEvent.ACTION_DRAG_ENDED:
        Log.d(TAG, "onDrag: ACTION_DRAG_ENDED ");
        outOfBoundsCheck(wasThereDrop);
      default:
        break;
    }//switch
    return true;
  }
  public boolean onTouch(View v, MotionEvent event) {
    switch(event.getAction()){
      case MotionEvent.ACTION_DOWN:
        System.out.println(v.getX() + " X " +v.getY() + " Y     ONCLICK"  );
      case MotionEvent.ACTION_BUTTON_RELEASE:
        System.out.println(v.getX() + " X " +v.getY() + " Y    ONRELEASE"  );
    }
    if(event.getAction() == MotionEvent.ACTION_DOWN){
      System.out.println(v.getX() + " X " +v.getY() + " Y     ONCLICK"  );
    }//if
    if(event.getAction() == MotionEvent.ACTION_UP){
      System.out.println(v.getX() + " X " +v.getY() + " Y    ONRELEASE"  );
    }
    DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
    v.startDrag(null, shadowBuilder, v, 0);
    v.setVisibility(v.INVISIBLE);

    return true;
  }//onTouch
  public void outOfBoundsCheck(boolean wasThereDrop){
    if(wasThereDrop == false){
      imageView2.setVisibility(View.VISIBLE);
    }
  }
}//gameActivity