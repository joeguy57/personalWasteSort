package com.example.wastesortapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import java.util.Timer;
import java.util.TimerTask;

public class appLaunch extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_app_launch);
    goToMainMenu();
  }//onCreate

  public void goToMainMenu(){
    Timer timer = new Timer();
    timer.schedule(new TimerTask() {
      @Override
      public void run() {
        Intent mainMenu = new Intent(getApplicationContext() , MainActivity.class);
        startActivity(mainMenu);
        finish();
      }
    },2500);

  }//goToMainMenu


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

}
