/**
 * This Activity class is used for the as the boot up screen
 *
 * The following methods are used:
 *-------------------------------------------------------------------------------------------------
 *
 * goToMainMenu(): This method is used to go navigate to the main menu page with a delay of 2.5 secs
 *
 * -------------------------------------------------------------------------------------------------
 *
 * onBackPressed(): When the presses on the back button on their phone navigation bar, there is a
 *                  confirmation message. If YES the exit the app, else don't
 *
 *-------------------------------------------------------------------------------------------------
 *
 * @author Harshil Vyas
 * ID : 158162
 * Date:   2020 - 01 - 19
 */

package com.example.wastesortapp;

import android.content.DialogInterface;
import android.content.Intent;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import java.util.Timer;
import java.util.TimerTask;

public class AppLaunch extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_app_launch);
    goToMainMenu();
  }//onCreate

  /**
   * From the boot up page to go to the Main Menu activity is has a 2.5 sec delay
   */
  private void goToMainMenu(){
    Timer timer = new Timer();
    timer.schedule(new TimerTask() {
      @Override
      public void run() {
        Intent mainMenu = new Intent(getApplicationContext() , MainActivity.class);
        startActivity(mainMenu);
        finish();
      }//run
    },2500);

  }//goToMainMenu

  /**
   * If the user pressed on the back button on the users phone from the navigation bar then the
   * user will exit the application based on the users answer (YES / NO)
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
            finish();
          }//onClick
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
