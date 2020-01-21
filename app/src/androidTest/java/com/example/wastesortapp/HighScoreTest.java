package com.example.wastesortapp;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import org.junit.Test;

public class HighScoreTest {
  private final DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
  @Test
  public void sendData() {
    String key;
    DatabaseReference writeData = rootRef.child("1WTVDXleXTbtGu43obhTU9fwozWAtG0R1Cw464U3mvlk")
        .child("HighScore");
    if (!writeData.getKey().equals(String.valueOf(1))) {
      key = writeData.push().getKey();
    } else {
      key = "1";
    }
    assert !(key == null);
  }
}