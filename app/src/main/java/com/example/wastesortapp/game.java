/**
 * GameActivity.java
 * <p>This activity is going to show the gamePlay aspect of the application. There is a
 * fixed amount of bins for the objects to be sorted into. It will have bin objects displayed at the
 * bottom of the screen where items can be dropped into. These items will be pulled from a FireBase
 * server to help reduce the size of the application. After the game is completed, this class will
 * send the players score to the HighScore class where the user may enter their email which will be
 * sent to a Google Sheet.
 *
 * @author Joseph Menenez, Jared Matson, Harshil Vyas
 *
 * Methods (Non trivial) :
 *  - OnCreate(Bundle)
 *     Initializes activity
 *  - onFinish()
 *     When the timer for the 45 second marker ends, will play the timer ticking sound.
 *  - onAnimationStart(Animator)
 *     Will initialize the time bar which dictates the time left in the game.
 *  - onAnimationEnd(Animator)
 *     Will take the user to the HighScore class when the timer bar animation ends
 *  - onStart()
 *     On the start of the activity, will initialize sound and the time bar animation
 *  - createNewImages()
 *     Pulls an item from the FireBase and will assign that item a name/bin color/image
 *  - findItems()
 *      Method that finds all the ConstraintLayout and Views that are used in the activity
 *  - setItemAttributes()
 *      Sets attributes to the objects found in findItems() will either assign an onDragListener,
 *      onTouchListener, or a Tag
 *   - checkForPoint(String)
 *      Will check to see if the user placed the item in the right bin
 *   - increaseScore(boolean)
 *      Will either increment or decrement the score, based on if the user sorted an item correctly
 *   - onDrag(View, DragEvent)
 *      Allows for constraint layouts to determine if an item was dragged over top of them.
 *   - onTouch(View, MotionEvent)
 *      Allows for items spawned in the game to be draggable
 *   - outOfBoundsCheck(boolean)
 *      Will make sure the image being sorted will still be visible if the user dragged the item
 *      to either the navigation bar or the top of the screen where there are no constraint layouts
 *   - enableVolume(View)
 *      Enables volume in game
 *   - disableVolume(View)
 *      Disables volume in game
 *   - onBackPressed()
 *      Will take the user out of the application if they hit the back arrow on the navigation bar
 *   - backBtnPress()
 *      Will take the user back to the Main Menu.
 * </p>
 **/
package com.example.wastesortapp;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
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
import java.util.TimerTask;
import java.util.Timer;

public class game extends AppCompatActivity implements ImageView.OnDragListener,
    ImageView.OnTouchListener {

  private final Sound sound = new Sound(this);
  private ImageView imageView2;
  private ImageView enableSoundButton;
  private ImageView disableSoundButton;
  private ObjectAnimator mAnimation;
  private final DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
  private final DatabaseReference imagesUrlsRef = rootRef.child("GameObjects");
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
  private final ArrayList<Integer> itemsChosen = new ArrayList<Integer>();

  //-----------------------------------------------------------------------------------
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    timer = new CountDownTimer(45000, 1000) { //45 second timer to do ticking sound
      //which will inform user game is coming to end
      @Override
      public void onTick(long millisUntilFinished) {
      }

      @Override
      public void onFinish() {
        sound.playTickingSound();
      }
    };
    timer.start();

    sound.initializeAllGameSounds();
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_game);

    DisplayMetrics displayMetrics = new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

    ImageView yellowBin = findViewById(R.id.beverageBin);
    ImageView blueBin = findViewById(R.id.recycleBin);
    ImageView greenBin = findViewById(R.id.organicBin);
    ImageView blackBin = findViewById(R.id.trashCan);
    ProgressBar progressBar = findViewById(R.id.timerBar);
    ImageView timerText = findViewById(R.id.timeText);
    ImageView scoreText = findViewById(R.id.scoreText);
    ImageView scoreDisplay = findViewById(R.id.score);
    TextView organicLabel = findViewById(R.id.organicLabel);
    TextView recycleLabel = findViewById(R.id.recycleLabel);
    TextView landFillLabel = findViewById(R.id.landFillLabel);
    TextView beverageLabel = findViewById(R.id.beverageLabel);
    ImageView backBtn = findViewById(R.id.backBtn);
    scoreView = findViewById(R.id.scoreView);
    enableSoundButton = findViewById(R.id.soundOnImage);
    disableSoundButton = findViewById(R.id.soundOffImage);
    itemNameTextView = findViewById(R.id.itemNameTextView);

    //Animation Setup
    Animation fromBottom = AnimationUtils.loadAnimation(this, R.anim.from_bottom);
    Animation fromTop = AnimationUtils.loadAnimation(this, R.anim.from_top);

    //Animations
    organicLabel.setAnimation(fromBottom);
    beverageLabel.setAnimation(fromBottom);
    landFillLabel.setAnimation(fromBottom);
    recycleLabel.setAnimation(fromBottom);
    yellowBin.setAnimation(fromBottom);
    blackBin.setAnimation(fromBottom);
    blueBin.setAnimation(fromBottom);
    greenBin.setAnimation(fromBottom);
    progressBar.setAnimation(fromTop);
    timerText.setAnimation(fromTop);
    scoreText.setAnimation(fromTop);
    scoreDisplay.setAnimation(fromTop);
    enableSoundButton.setAnimation(fromTop);
    disableSoundButton.setAnimation(fromTop);
    scoreView.setAnimation(fromTop);
    itemNameTextView.setAnimation(fromTop);
    backBtn.setAnimation(fromTop);

    backBtn.setOnClickListener(new View.OnClickListener() {
      /**
       * Will bring the user back to the main menu if the back button is pressed
       *
       * @param v the back button being pressed
       */
      @Override
      public void onClick(View v) {
        backBtnPress();

      }
    });

    mAnimation = ObjectAnimator.ofInt(progressBar, "progress", 100, 0);
    mAnimation.setDuration(60000);
    mAnimation.addListener(new Animator.AnimatorListener() {
      /**
       * Initializes the time bar.
       *
       * @param animator the time bar
       */
      @Override
      public void onAnimationStart(Animator animator) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
          @Override
          public void run() {
          }
        }, 2500);
      }

      /**
       * When the animation ends (the time bar is empty after a minute) this method will
       * bring the user to the HighScore class.
       *
       * @param animator the time bar
       */
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

  /**
   * On the start of the activity, will initiate sound, start the time bar animation, and spawn a
   * single image for the user to sort.
   */
  @Override
  protected void onStart() {
    super.onStart();
    sound.enableSound();
    mAnimation.start();
    createNewImages();
    findItems();

  }//onStart

  /**
   * This function will randomly pull items from our FireBase server, it will then pull specific
   * information from these items such as its name, the image associated with it, and the color of
   * bin it goes in. This information is used to determine points and give the user more information
   * about what is being sorted.
   */
  private void createNewImages() {
    imagesUrlsRef.addValueEventListener(new ValueEventListener() {
      @Override
      public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

        long numChildren = dataSnapshot.getChildrenCount();
        String[] randKey = new String[(int) numChildren];
        List<String> list = Arrays.asList(randKey);
        String num = String.valueOf(numChildren);
        int randNum = new Random().nextInt(Integer.parseInt(num));
        System.out.println(randNum);

        if (itemsChosen.contains(randNum)) {
          createNewImages();
        } else {
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
          if (itemsChosen.size() == numChildren) {
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

  /**
   * Finds all of the ConstraintLayouts and Views that will be used within the activity
   */
  private void findItems() {
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
  }//findItems

  /**
   * Will set onDragListeners for all the constraint layouts in the game. This include all binds,
   * where the game items spawn, and the game screen as a whole. Will also set tags to these
   * constraint layouts which will help determine point scoring.
   */
  private void setItemAttributes() {
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
  }//setItemAttributes

  /**
   * Will check to see if the user placed the item in the right bin, it will do this by matching the
   * color of where the item is supposed to go, and the tag of the bin that it was dropped in. If
   * the color and tag match, then a point is awarded, otherwise a point is deducted
   *
   * @param binChoice Color of bin the item was dropped in
   */
  private void checkForPoint(String binChoice) {
    if (binChoice.equals("Outside")) {
      imageView2.setVisibility(View.VISIBLE);
    }//if
    else if (binChoice.equals(color)) {
      increaseScore(true);
      createNewImages();
      sound.playCorrectSound();
    }//if
    else if (binChoice != constraintLayout.getTag() && binChoice != itemSpawnLocation.getTag()) {
      //if the player picks a wrong bin, otherwise the app assumes that the user just dragged
      //into no container and will thus not register a point
      increaseScore(false);
      createNewImages();
      sound.playIncorrectSound();
    }//if
  }//checkForPoints

  /**
   * Will either increment or decrement the score, based on if they sorted an item correctly
   *
   * @param wasPointScored did the person sort the image correctly
   */
  private void increaseScore(boolean wasPointScored) {
    if (wasPointScored) {
      score += 1;
    }//if
    else {
      score -= 1;
    }//else
    scoreView.setText("" + score);
  }//changeScore

  /**
   * Allows for constraint layouts to determine if an item was dragged overtop of them. Applied to
   * where the item spawns, the game screen as a whole, and each individual bin.
   *
   * @param v What is being drug around
   * @param event what is happening during the drag event
   * @return was there a drag event
   */
  public boolean onDrag(View v, DragEvent event) {
    ConstraintLayout container;
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
        Log.d(TAG, "onDrag: ACTION_DRAG_DROP");
        container = (ConstraintLayout) v;
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

  /**
   * Allows for items spawned in the game to be draggable, the item technically does not delete when
   * dragged, it just goes invisible
   *
   * @param v the imageView being dragged (in our case)
   * @param event what is happening to this imageView
   * @return was the item dragged
   */
  public boolean onTouch(View v, MotionEvent event) {
    DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
    v.startDrag(null, shadowBuilder, v, 0);
    v.setVisibility(View.INVISIBLE);

    return true;
  }//onTouch

  /**
   * Will make sure the image being sorted will still be visible if the user dragged the item to
   * either the navigation bar or the top of the screen where there are no constraint layouts
   *
   * @param wasThereDrop was the item placed in a constraint layout provided
   */
  private void outOfBoundsCheck(boolean wasThereDrop) {
    if (!wasThereDrop) {
      imageView2.setVisibility(View.VISIBLE);
    }//if
  }//outOfBoundsCheck

  /**
   * Enables volume if volume is disabled, must click on enableSoundButton in top right of game
   * activity to do so
   *
   * @param v the enable volume button being pressed
   */
  public void enableVolume(View v) {
    sound.enableSound();
    disableSoundButton.setVisibility(View.VISIBLE);
    enableSoundButton.setVisibility(View.INVISIBLE);
  } // enableVolume

  /**
   * Disables volume if volume is enabled, must click on disableSoundButton in top right of game
   * activity to do so
   *
   * @param v the disable volume button being pressed
   */
  public void disableVolume(View v) {
    sound.disableSound();
    disableSoundButton.setVisibility(View.INVISIBLE);
    enableSoundButton.setVisibility(View.VISIBLE);
  } // enableVolume

  /**
   * Looks to see if the back button was pressed on the navigation bar, will close app if it was
   * hit
   */
  public void onBackPressed() {
    AlertDialog.Builder confirmation = new AlertDialog.Builder(this);

    confirmation.setMessage("Are you sure you want to go exit?")
        //.setCancelable(true)
        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int which) {
            mAnimation.cancel();
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
          }
        })

        .setNegativeButton("No", new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int which) {
            dialog.cancel();
          }
        });
    AlertDialog alertDialog = confirmation.create();
    alertDialog.show();

  }//onbackpressed

  /**
   * Will bring the user to the Main Menu. This will be called if the back button at the top left of
   * the activity is pressed.
   */
  private void backBtnPress() {
    AlertDialog.Builder confirmation = new AlertDialog.Builder(this);

    confirmation.setMessage("Are you sure you want to go back?")
        .setCancelable(false)
        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int which) {
            Intent goBack = new Intent(getApplicationContext(), MainActivity.class);
            sound.disableSound();
            mAnimation.cancel();
            timer.cancel();

            finish();
            startActivity(goBack);

          }
        })

        .setNegativeButton("No", new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int which) {
            dialog.cancel();
          }
        });
    AlertDialog alertDialog = confirmation.create();
    alertDialog.show();

  }//backBtnPressed
}//gameActivity