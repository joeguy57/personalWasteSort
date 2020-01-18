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
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.DragEvent;
import android.view.KeyEvent;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Timer;

public class game extends AppCompatActivity implements  ImageView.OnDragListener,
          ImageView.OnTouchListener {
  Sound sound = new Sound(this);
  ImageView imageView2;
  private ImageView enableSoundButton;
  private ImageView disableSoundButton;
  private ObjectAnimator mAnimation;
  private DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
  private DatabaseReference imagesUrlsRef = rootRef.child("GameObjects");
  private ConstraintLayout constraintLayout;
  private ConstraintLayout dropLayoutGreen;
  private ConstraintLayout dropLayoutBlue;
  private ConstraintLayout dropLayoutYellow;
  private ConstraintLayout dropLayoutBlack;
  private ConstraintLayout itemSpawnLocation;
  private TextView itemNameTextView;
  private String color;
  private TextView scoreView;
  private int score = 0;
  private static final String TAG = "MyActivity";
  private boolean wasThereDrop = false;
  private CountDownTimer timer;
  ArrayList<Integer> itemsChosen = new ArrayList<Integer>();

  //-----------------------------------------------------------------------------------
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    timer = new CountDownTimer(45000,1000) {
      @Override
      public void onTick(long millisUntilFinished) {
        System.out.println("One second");
      }

      @Override
      public void onFinish() {
        sound.playTickingSound();
      }
    };timer.start();

    sound.initializeAllGameSounds();
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_game);

    DisplayMetrics displayMetrics = new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

    ImageView backBtn = findViewById(R.id.backBtn);
    final ProgressBar progressBar = (ProgressBar) findViewById(R.id.timerBar);
    progressBar.getDrawingTime();
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
        mAnimation.cancel();
        Intent highscore = new Intent(getApplicationContext(), HighScore.class);
        highscore.putExtra("Score", score);
        sound.playGameOverSound();
        startActivity(highscore);
        finish();

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

  }//onStart


  protected void createNewImages() {
    imagesUrlsRef.addValueEventListener(new ValueEventListener() {
      @Override
      public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

        long numChildren =  dataSnapshot.getChildrenCount();
        String[] randKey = new String[(int) numChildren];
        List<String> list = Arrays.asList(randKey);
        String num = String.valueOf(numChildren);
        int randNum = new Random().nextInt(Integer.parseInt(num));
        System.out.println(randNum);

        if(itemsChosen.contains(randNum)){
          createNewImages();
        }
        else {
          //System.out.println(itemsChosen);
          String rand = String.valueOf(randNum);
          String link = dataSnapshot.child(rand).child("Image").getValue(String.class);
          String data = dataSnapshot.child(rand).child("Color").getValue(String.class);
          String ItemName = dataSnapshot.child(rand).child("Item").getValue(String.class);
          color = data;
          Picasso.get().load(link).into(imageView2);
          imageView2.setVisibility(View.VISIBLE);
          itemNameTextView.setText(ItemName);
          itemsChosen.add(randNum);
          if(itemsChosen.size() == numChildren){
            itemsChosen.clear();
          }
        //  }//if
        }//else
      }//onDataChange

      @Override
      public void onCancelled(@NonNull DatabaseError databaseError) {
      }
    });
  }//createNewImages
  public void findItems() {
    enableSoundButton = findViewById(R.id.soundOffImage);
    disableSoundButton = findViewById(R.id.soundOnImage);
    constraintLayout = findViewById(R.id.ConstraintLayoutDrop);
    scoreView = findViewById(R.id.scoreView);
    dropLayoutGreen = findViewById(R.id.dropLayoutGreen);
    dropLayoutBlue = findViewById(R.id.dropLayoutBlue);
    dropLayoutYellow = findViewById(R.id.dropLayoutYellow);
    dropLayoutBlack = findViewById(R.id.dropLayoutBlack);
    itemSpawnLocation = findViewById(R.id.itemSpawnLocation);
    imageView2 = findViewById(R.id.imageView2);
    itemNameTextView = findViewById(R.id.itemNameTextView);
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
    if(wasPointScored) {
      score += 1;
    }
    else{
      score -= 1;
    }
    scoreView.setText(""+ score);
  }//changeScore

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
        container = (ConstraintLayout) v;
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
  }//onDrag
  public boolean onTouch(View v, MotionEvent event) {
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
  public void enableVolume(View v) {
    sound.enableSound();
    disableSoundButton.setVisibility(View.VISIBLE);
    enableSoundButton.setVisibility(View.INVISIBLE);
  } // enableVolume
  public void disableVolume(View v) {
    sound.disableSound();
    disableSoundButton.setVisibility(View.INVISIBLE);
    enableSoundButton.setVisibility(View.VISIBLE);
  } // enableVolume
  public boolean onKeyDown(int keyCode, KeyEvent event){
    if(keyCode == KeyEvent.KEYCODE_BACK){
      finish();
      System.exit(0);
    }
    return true;
  }//onKeyDown
}//gameActivity