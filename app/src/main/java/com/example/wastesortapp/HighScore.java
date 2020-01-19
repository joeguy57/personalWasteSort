/*
 * HighScore.java
 * This Activity sends the data to the firebase for saved Data.
 * Here score will be sent from the game.java and request (but not require),
 * the user to input either their name or email.
 * */
package com.example.wastesortapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
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
  private Boolean emailValid;
  private String emailInput;

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
        Intent goToMainMenu = new Intent(getApplicationContext() , MainActivity.class);
        startActivity(goToMainMenu);

      }//onClick
    });


  }//onStart


  private Boolean checkEmail(EditText emailTextView) {
    String emailPref = "ualberta.ca";
    emailInput = emailTextView.getText().toString();
    int emailStartIndex = emailInput.indexOf('@');
    String emailSubString = emailInput.substring(emailStartIndex + 1);

    if (emailSubString.equals(emailPref)) {
      return true;
    }
    return false;
  }

  public void sendData() {
    if (nameInput.getText().length() == 0 && emailTextView.getText().length() == 0) {
      Intent goToActivity;
      startActivity(goToActivity = new Intent(getApplicationContext(), MainActivity.class));
    }//if
    else if (emailTextView.getText().length() == 0) {
      String key;
      DatabaseReference writeData = rootRef.child("1WTVDXleXTbtGu43obhTU9fwozWAtG0R1Cw464U3mvlk")
          .child("HighScore");
      if (writeData.getKey() != String.valueOf(1)) {
        key = writeData.push().getKey();
      } else {
        key = "1";
      }
      SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy 'at' HH:mm:ss z");
      Date date = new Date(System.currentTimeMillis());
      writeData.child(key).child("Date").setValue(formatter.format(date));
      writeData.child(key).child("Score").setValue(score);
      writeData.child(key).child("Name").setValue(nameInput.getText().toString());
      Intent goToActivity;
      startActivity(goToActivity = new Intent(getApplicationContext(), MainActivity.class));
    }
    if (checkEmail(emailTextView)) {
      String key;
      DatabaseReference writeData = rootRef.child("1WTVDXleXTbtGu43obhTU9fwozWAtG0R1Cw464U3mvlk")
          .child("HighScore");
      if (writeData.getKey() != String.valueOf(1)) {
        key = writeData.push().getKey();
      } else {
        key = "1";
      }
      SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy 'at' HH:mm:ss z");
      Date date = new Date(System.currentTimeMillis());
      writeData.child(key).child("Date").setValue(formatter.format(date));
      writeData.child(key).child("Score").setValue(score);
      writeData.child(key).child("Name").setValue(nameInput.getText().toString());

      writeData.child(key).child("emailAddress").setValue(emailInput);
      Intent goToActivity;
      startActivity(goToActivity = new Intent(getApplicationContext(), MainActivity.class));

    } else {
      emailTextView.setText("");
      emailTextView.setError("Please use ualberta email");
    }
  }//sendDataa

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

}//HighScore
