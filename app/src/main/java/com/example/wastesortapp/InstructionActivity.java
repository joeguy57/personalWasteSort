/**
 * instruction_activity.java
 * This class represents the transition between MainMenu.java and Game.java. When the user
 * chooses to play the Game in MainMenu, this activity will imitate a loading screen
 * which will display brief instructions on how to play the Game.
 *
 * Methods:
 *  - onCreate(Bundle)
 *      Initializes activity
 *  - goToGame()
 *      Takes the user to the Game after a five second delay (during this delay, the Game
 *      instructions will be displayed with a loading gif)
 *  - onBackPressed()
 *      Will take the user out of the application if they hit the back button on the navigation
 *      bar, will ask for confirmation through a dialog box before doing so
 *
 * @author Harshil Vyas
 */
package com.example.wastesortapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Timer;
import java.util.TimerTask;

public class InstructionActivity extends AppCompatActivity {

  private Timer timer;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_instruction_activity);
    goToGame();
  }//onCreate

  /**
   * After 5 seconds, will bring the user to the Game
   */
  private void goToGame() {
    timer = new Timer();
    timer.schedule(new TimerTask() {
      @Override
      public void run() {
        Intent mainMenu = new Intent(getApplicationContext(), Game.class);
        startActivity(mainMenu);
      }
    }, 5000);

  }//goToMainMenu

  /**
   * Will close the application if the user hits the back button on the navigation bar. Before it
   * does this, the user will be prompted with a dialog box to confirm this action.
   */
  public void onBackPressed() {
    AlertDialog.Builder confirmation = new AlertDialog.Builder(this);

    confirmation.setMessage("Are you sure you want to go exit?")
        //.setCancelable(true)
        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int which) {
            timer.cancel();
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
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
