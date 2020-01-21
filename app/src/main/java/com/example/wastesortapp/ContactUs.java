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
 * --------------------------------------------------------------------------------------------------
 *
 * backBtnPressed(): Takes the user to the home screen when the back button is pressed
 *
 * -------------------------------------------------------------------------------------------------
 *
 * @author Harshil Vyas - 158162
 * @author Joseph Menezes - 1510740
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
    backBtnPressed();

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
  public void backBtnPressed(){
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
   * Looks to see if the back button was pressed on the navigation bar, will go to home screen
   * if it was hit
   */
  public void onBackPressed() {
    AlertDialog.Builder confirmation = new AlertDialog.Builder(this);

    confirmation.setMessage("Are you sure you want to go back?")
        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int which) {

            Intent goToHome = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(goToHome);
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
