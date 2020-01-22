/**
 * In Main Menu Class the user are able to navigate to the their desired activity which are : - The
 * Waste Sort Game - Waste Guide - Contact Us / More Information
 *
 * This class uses the following methods :
 *-------------------------------------------------------------------------------------------------
 *
 * onCreate(Bundle savedInstanceState): All the navigation is declared in here and the animations
 *
 *-------------------------------------------------------------------------------------------------
 *
 * findItems(): Finds all views in MainMenu where animations will be applied
 *
 *-------------------------------------------------------------------------------------------------
 *
 * setAnimations(): Initializes animations and sets these animation to the items found in
 *                                                                                 findItems()
 *
 * ------------------------------------------------------------------------------------------------
 *
 * onBackPressed(): When the presses on the back button on their phone navigation bar, there is a
 *                  confirmation message. If YES the exit the app, else don't
 *
 *-------------------------------------------------------------------------------------------------
 *
 * goToContactUs(): Takes the user to the Contact page for more information
 *
 *-------------------------------------------------------------------------------------------------
 *
 * goToGame():  Take the user to a waste sorting Game
 *
 * ------------------------------------------------------------------------------------------------
 *
 * goToWasteSort(): Take the user to a activity to search for objects to dispose
 *
 *--------------------------------------------------------------------------------------------------
 * @author Harshil Vyas
 * ID : 158162
 * Date:   2020 - 01 - 19
 */

package com.example.wastesortapp;

import android.content.DialogInterface;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainMenu extends AppCompatActivity {

  //Global Variables
  protected Intent goToActivity;
  Button disposableHelpBtn;
  Button moreInfo;
  Button gameBtn;
  ImageView mainMenuLogo;
  TextView title;

  @Override
  /**
   * Navigation to the other feature and aspects of the app is declared in here and the animations
   * of the objects being displayed are also here.
   */
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main_menu);

    //View Objects
    findItems();
    setAnimations();
    goToContactUs();
    goToGame();
    goToWasteSort();

  }//OnCreate

  /**
   * Finds the appropriate items to set animations to
   */
  public void findItems(){
    mainMenuLogo = findViewById(R.id.mainMenuLogo);
    gameBtn = findViewById(R.id.gameBtn);
    disposableHelpBtn = findViewById(R.id.disposableHelpBtn);
    moreInfo = findViewById(R.id.moreInfoBtn);
    title = findViewById(R.id.title);
  }//findItems

  /**
   * Initializes  fade in and from bottom animations, assigngs the items from findItems()
   * one of these two animations
   */
  public void setAnimations(){
    //Animations Variables
    Animation fromBottom = AnimationUtils.loadAnimation(this, R.anim.from_bottom);
    Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);

    //Animations
    gameBtn.setAnimation(fromBottom);
    disposableHelpBtn.setAnimation(fromBottom);
    moreInfo.setAnimation(fromBottom);
    mainMenuLogo.setAnimation(fadeIn);
    title.setAnimation(fadeIn);
  }
  /**
   * Takes the user to a the Waste Sort Game
   */
  public void goToGame() {
    gameBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        startActivity(
            goToActivity = new Intent(getApplicationContext(), InstructionActivity.class));
        finish();
      }
    });
  }//goToGame

  /**
   * Take the user to a page to search object to learn to dispose
   */
  public void goToWasteSort() {
    disposableHelpBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        startActivity(goToActivity = new Intent(getApplicationContext(), WasteGuide.class));
        finish();
      }
    });
  }//goToWasteSort

  /**
   * Take the user to a page to get more information for help and contact information
   */
  public void goToContactUs() {
    moreInfo.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        startActivity(goToActivity = new Intent(getApplicationContext(), ContactUs.class));
        finish();
      }
    });
  }//goToContactUs

  /**
   * If the user pressed on the back button on the users phone from the navigation bar then the user
   * will exit the application based on the users answer (YES / NO)
   */
  public void onBackPressed() {
    AlertDialog.Builder confirmation = new AlertDialog.Builder(this);

    confirmation.setMessage("Are you sure you want to go exit?")
        //.setCancelable(true)
        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int which) {
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

}//class
