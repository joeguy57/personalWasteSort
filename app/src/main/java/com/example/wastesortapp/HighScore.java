/*
* HighScore.java
* This Activity sends the data to the firebase for saved Data.
* Here score will be sent from the game.java and request (but not require),
* the user to input either their name or email.
* */
package com.example.wastesortapp;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
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
    playerScore.setText(String.valueOf(score));

  }//onStart

  private Boolean checkEmail(EditText emailTextView) {
    String emailPref = "ualberta.ca";
    emailInput = emailTextView.getText().toString();
    int emailStartIndex = emailInput.indexOf('@');
    String emailSubString = emailInput.substring(emailStartIndex + 1);

    if (emailSubString.equals(emailPref)){
      return true;
    }
    return false;
  }

  public void sendData(View view){

    String key;
    DatabaseReference writeData = rootRef.child("1WTVDXleXTbtGu43obhTU9fwozWAtG0R1Cw464U3mvlk").child("HighScore");
    if (writeData.getKey() != String.valueOf(1)) {
      key = writeData.push().getKey();
    }
    else{
      key = "1";
    }
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy 'at' HH:mm:ss z" );
    Date date = new Date(System.currentTimeMillis());
    writeData.child(key).child("Date").setValue(formatter.format(date));
    writeData.child(key).child("Score").setValue(score);
    writeData.child(key).child("Name").setValue(nameInput.getText().toString());
    if(!emailInput.isEmpty()) {
      emailValid = checkEmail(emailTextView);
      while (!emailValid) {
        emailTextView.setError("Invalid Email: please use your ualberta email address");
      }//if
      writeData.child(key).child("Email").setValue(emailInput);
    }//outter if

  }//sendDataa
}//HighScore
