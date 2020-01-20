/**
 * In Main Menu Class the user are able to navigate to the their desired activity which are :
 *    - The Waste Sort Game
 *    - Waste Guide
 *    - Contact Us / More Information
 *
 * This class uses the following methods :
 *
 * onCreate(Bundle savedInstanceState): All the navigation is declared in here and the animations
 *
 * onBackPressed(): When the presses on the back button on their phone navigation bar, there is a
 *                   confirmation message. If YES the exit the app, else don't
 *
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

public class MainActivity extends AppCompatActivity {

  //Global Variables
  private Intent goToActivity;

  @Override
  /**
   * Navigation to the other feature and aspects of the app is declared in here and the animations
   * of the objects being displayed are also here.
   */
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    //View Objects
    ImageView mainMenuLogo = findViewById(R.id.mainMenuLogo);
    Button gameBtn = findViewById(R.id.gameBtn);
    Button disposableHelpBtn = findViewById(R.id.disposableHelpBtn);
    Button moreInfo = findViewById(R.id.moreInfoBtn);
    TextView title = findViewById(R.id.title);

    //Animations Variables
    Animation fromBottom = AnimationUtils.loadAnimation(this, R.anim.from_bottom);
    Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);

    //Animations
    gameBtn.setAnimation(fromBottom);
    disposableHelpBtn.setAnimation(fromBottom);
    moreInfo.setAnimation(fromBottom);
    mainMenuLogo.setAnimation(fadeIn);
    title.setAnimation(fadeIn);

    //Game Activity Navigation
    gameBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        startActivity(
            goToActivity = new Intent(getApplicationContext(), InstructionActivity.class));
        finish();

      }
    });

    //Waste Sort Activity Navigation
    disposableHelpBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        startActivity(goToActivity = new Intent(getApplicationContext(), DisposableHelp.class));
        finish();
      }
    });

    //Contact Us Navigation
    moreInfo.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        startActivity(goToActivity = new Intent(getApplicationContext(), ContactUs.class));
        finish();
      }
    });

  }//OnCreate

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
