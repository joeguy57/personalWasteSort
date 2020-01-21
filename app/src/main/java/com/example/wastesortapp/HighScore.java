/**
 * HighScore.java
 * This Activity sends the data to the firebase for saved Data.
 * Here score will be sent from the Game.java and request (but not require),
 * the user to input either their name or email.
 *
 * Methods:
 * checkEmail() - check to see if its a ualberta.ca email.
 * sendData()- Writes the data to google sheet to be viewed by the Client.
 * onBackPressed - When the back button on the interface is pressed, a Dialog (alert pops up)...
 * created on: 13/01/2019
 * Completed and ready for MVP
 *
 * @author : Jared , Joseph , Harshil
 **/

package com.example.wastesortapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HighScore extends AppCompatActivity {

  private int score;
  private final DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
  private EditText nameTextView;
  private EditText emailTextView;
  private ImageView yourScoreLocation;
  private TextView playerScore;
  private ImageView mainMenuBtn;
  private ImageView submitBtn;
  private ImageView nameLocation;
  private TextView enterNameView;
  private ImageView emailLocation;
  private TextView userEmailView;
  private ImageView nameInputLocation;
  private ImageView emailInputLocation;

  //--------------------------------------------------------------------------------------------
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_high_score);
    Bundle bundleIntent = getIntent().getExtras();
    assert bundleIntent != null;

    score = bundleIntent.getInt("Score", 1);
    nameTextView = findViewById(R.id.userInputName);
    emailTextView = findViewById(R.id.userEmailName);
    playerScore = findViewById(R.id.playerScore);
    mainMenuBtn = findViewById(R.id.mainMenuBtn);
    submitBtn = findViewById(R.id.submitBtn);
    nameLocation = findViewById(R.id.nameLocation);
    yourScoreLocation = findViewById(R.id.yourScoreLocation);
    nameLocation = findViewById(R.id.nameLocation);
    enterNameView = findViewById(R.id.enterName);
    emailLocation = findViewById(R.id.emaillocation);
    userEmailView = findViewById(R.id.userEmail);
    nameInputLocation = findViewById(R.id.nameinputlocation);
    emailInputLocation = findViewById(R.id.emailinputlocation);

    animationDisplay();

    playerScore.setText(String.format("Your Score is: %d", score));


    submitBtn.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        sendData();
      }
    });
    mainMenuBtn.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent goToMainMenu = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(goToMainMenu);
      }//onClick
    });


  }//onStart

  /**
   * This function does a check on the users email if it has been inputted.
   *
   * @param emailTextView - textView where email is added1
   * @return true if its a ualberta email address
   */
  private Boolean checkEmail(EditText emailTextView) {
    String emailPref = "ualberta.ca";
    String emailInput = emailTextView.getText().toString();
    int emailStartIndex = emailInput.indexOf('@');
    String emailSubString = emailInput.substring(emailStartIndex + 1);
    return emailSubString.equals(emailPref);
  }//checkEmail

  /**
   * this error will be run when user presses submit leaving an error message to make the
   * correction.
   */
  private void submissionErrorBlank() {
    nameTextView.setText("");
    emailTextView.setText("");
    nameTextView.setError("If you do not want to submit your high score, hit the main menu button."
        + " Else fill in either your Name or both Name and Email Address");
  }//submissionErrorBlank

  /**
   * Data will be sent to firebase in order to load the
   *
   * @param writeData- Database connection with android
   * @param key - the parent Index
   * @param isEmail - checks to see if email is required
   */
  private void sendNameData(DatabaseReference writeData, String key, Boolean isEmail) {
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy 'at' HH:mm:ss z");
    Date date = new Date(System.currentTimeMillis());
    writeData.child(key).child("Date").setValue(formatter.format(date));
    writeData.child(key).child("Score").setValue(score);
    writeData.child(key).child("Name").setValue(nameTextView.getText().toString());
    startActivity(new Intent(getApplicationContext(), MainActivity.class));
    if (isEmail){
      writeData.child(key).child("emailAddress").setValue(emailTextView.getText().toString());
    }
  }

  /**
   * Takes the data that a user chooses to input and then sends into be processed to be used as a
   * google sheets.
   */
  private void sendData() {
    //Create DatabaseReference
    DatabaseReference writeData = rootRef.child("1WTVDXleXTbtGu43obhTU9fwozWAtG0R1Cw464U3mvlk")
        .child("HighScore");
    //if user doesn't enter values and presses the submit button an error message is produced.
    if ((nameTextView.getText().length() == 0) && (emailTextView.getText().length() == 0)) {
      submissionErrorBlank();
    }//if
    // if user choose to not  provide their email and press the submit button.
    else if (emailTextView.getText().length() == 0) {
      String key;
      //creates the key value to check the parent of the database.
      key = createKey(writeData);
      //Sends only the name to the user.
      sendNameData(writeData, key, false);
    }//else if

    //if user types in the email address
    if (checkEmail(emailTextView)) {
      String key;
      key = createKey(writeData);
      //Sends all information saved.
      sendNameData(writeData, key, true);
      //goToMainActivity
      startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }//if
    //if email is not entered correctly an error is placed.

    else {
      emailTextView.setText("");
      emailTextView.setError("Please use ualberta email");
    }//else

  }//sendData

  private String createKey(DatabaseReference writeData) {
    String key;
    if (!writeData.getKey().equals(String.valueOf(1))) {
      key = writeData.push().getKey();
    }//if
    else {
      key = "1";
    }//else
    return key;
  }//createKey

  /**
   * All the animations at using such as fly in from top and bottom for transition
   */
  private void animationDisplay() {
    //Animation Setup
    Animation fromBottom = AnimationUtils.loadAnimation(this, R.anim.from_bottom);
    Animation fromRight = AnimationUtils.loadAnimation(this, R.anim.fly_in_right);
    Animation fromLeft = AnimationUtils.loadAnimation(this, R.anim.fly_in_left);
    Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);


    //Animations
    submitBtn.setAnimation(fromBottom);
    mainMenuBtn.setAnimation(fromBottom);

    yourScoreLocation.setAnimation(fadeIn);
    playerScore.setAnimation(fadeIn);

    nameInputLocation.setAnimation(fromLeft);
    nameTextView.setAnimation(fromLeft);
    emailTextView.setAnimation(fromLeft);
    emailInputLocation.setAnimation(fromLeft);

    userEmailView.setAnimation(fromRight);
    nameLocation.setAnimation(fromRight);
    enterNameView.setAnimation(fromRight);
    emailLocation.setAnimation(fromRight);


  }//animationDisplay

}//HighScore
