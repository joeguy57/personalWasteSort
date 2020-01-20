/*
 * HighScore.java
 * This Activity sends the data to the firebase for saved Data.
 * Here score will be sent from the game.java and request (but not require),
 * the user to input either their name or email.
 *
 * Methods:
 * checkEmail() - check to see if its a ualberta.ca email.
 * sendData()- Writes the data to google sheet to be viewed by the Client.
 * onBackPressed - When the back button on the interface is pressed, a Dialog (alert pops up)...
 * created on: 13/01/2019
 * Completed and ready for MVP
 * */
package com.example.wastesortapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HighScore extends AppCompatActivity {

  private int score;
  private DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
  private EditText nameInput;
  private EditText emailTextView;
  private String emailInput;
  //--------------------------------------------------------------------------------------------
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_high_score);
    Bundle intent = getIntent().getExtras();
    score = intent.getInt("Score", 1);
    nameInput = findViewById(R.id.userInputName);
    emailTextView = findViewById(R.id.userEmailName);
    TextView playerScore = findViewById(R.id.playerScore);
    playerScore.setText("Your Score is: " + score);
    ImageView mainMenu = findViewById(R.id.mainMenuBtn);
    ImageView submitBtn = findViewById(R.id.submitBtn);

    submitBtn.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        sendData();
      }
    });

    mainMenu.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent goToMainMenu = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(goToMainMenu);

      }//onClick
    });


  }//onStart

  /**
   * This function does a check on the users email if it has been inputted.
   * @param emailTextView
   * @return true if its a ualberta email address
   */
  private Boolean checkEmail(EditText emailTextView) {
    String emailPref = "ualberta.ca";
    emailInput = emailTextView.getText().toString();

    int emailStartIndex = emailInput.indexOf('@');
    String emailSubString = emailInput.substring(emailStartIndex + 1);

    return emailSubString.equals(emailPref);
  }//checkEmail

  /**
   * Takes the data that a user chooses to input and then sends into be processed to
   * be used as a google sheets.
   */
  public void sendData() {
    //if user doesn't enter values and presses the submit button an error message is produced.
    if (nameInput.getText().length() == 0 && emailTextView.getText().length() == 0) {
      nameInput.setText("");
      emailTextView.setText("");
      nameInput.setError(
          "If you do not want to submit your high score, hit the main menu button. "
              + "Else fill in either your Name or both Name and Email Address");
    }//if
    // if user choose to not  provide their email and press the submit button.
    else if (emailTextView.getText().length() == 0) {
      String key;
      DatabaseReference writeData = rootRef.child("1WTVDXleXTbtGu43obhTU9fwozWAtG0R1Cw464U3mvlk")
          .child("HighScore");
      if (!writeData.getKey().equals(String.valueOf(1))) {
        key = writeData.push().getKey();
      } //if
      else {
        key = "1";
      }//else
      SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy 'at' HH:mm:ss z");
      Date date = new Date(System.currentTimeMillis());
      writeData.child(key).child("Date").setValue(formatter.format(date));
      writeData.child(key).child("Score").setValue(score);
      writeData.child(key).child("Name").setValue(nameInput.getText().toString());
      startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }//else if
    //if user types in the email address
    if (checkEmail(emailTextView)) {
      String key;
      DatabaseReference writeData = rootRef.child("1WTVDXleXTbtGu43obhTU9fwozWAtG0R1Cw464U3mvlk")
          .child("HighScore");
      if (writeData.getKey() != String.valueOf(1)) {
        key = writeData.push().getKey();
      }//if
      else {
        key = "1";
      }//else
      SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy 'at' HH:mm:ss z");
      Date date = new Date(System.currentTimeMillis());
      writeData.child(key).child("Date").setValue(formatter.format(date));
      writeData.child(key).child("Score").setValue(score);
      writeData.child(key).child("Name").setValue(nameInput.getText().toString());

      writeData.child(key).child("emailAddress").setValue(emailInput);
      startActivity(new Intent(getApplicationContext(), MainActivity.class));

    }//if
    //if email is not entered correctly an error is placed.
    else {
      emailTextView.setText("");
      emailTextView.setError("Please use ualberta email");
    }//else
  }//sendData

  /**
   *When the back button on Navigation is pressed then a Dialog box alerts
   *the user of what they are doing and exits thee app.
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
  }//onBackPressed
}//HighScore
