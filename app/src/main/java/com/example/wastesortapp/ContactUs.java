/**
 * In the Contact Us class so I there is link for to connect to the internet to get more
 * information
 *
 * The following methods are used:
 *--------------------------------------------------------------------------------------------------
 *
 * goToBrowser(): For uses how need to more information about Sustainability there is a link
 *               (University of Alberta) for that
 *
 *         Note : The user needs to be connected to the internet to be able to access the link
 *
 * -------------------------------------------------------------------------------------------------
 *
 * onBackPressed(): When the presses on the back button on their phone navigation bar, there is a
 *                  confirmation message. If YES the exit the app, else don't
 *
 *--------------------------------------------------------------------------------------------------
 *
 * backBtnPressed(): Takes the user to the home screen when the back button is pressed
 *
 * -------------------------------------------------------------------------------------------------
 *
 * @author Harshil Vyas
 * ID : 158162
 * Date:   2020 - 01 - 19
 */

package com.example.wastesortapp;

import android.content.DialogInterface;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ContactUs extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_contact_us);
    backBtnPresed();

  }//onCreate

  /**
   * Connects the users to the University of Alberta Sustainability Website to get more
   *
   * Note : The user needs to be connected to the Internet
   *
   * @param view is set up to an onClick button
   */
  public void goToBrowser(View view) {
    Intent browserAccess = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.ualberta" +
        ".ca/augustana/about-us/departments-offices-and-units/lab/sustainability/campus" +
        "-initiatives/waste"));
    startActivity(browserAccess);
  }//goToBrowser

  /**
   * Take the user to the Main Menu (Home) Screen
   */
  public void backBtnPresed(){
    ImageView backBtn = findViewById(R.id.backBtn);
    backBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent goToMain = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(goToMain);
        finish();
      }
    });
  }//backBtnPressed

  /**
   * If the user pressed on the back button on the users phone from the navigation bar then the user
   * will exit the application based on the users answer (YES / NO)
   */
  public void onBackPressed() {
    AlertDialog.Builder confirmation = new AlertDialog.Builder(this);

    confirmation.setMessage("Are you sure you want to go exit?")
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
