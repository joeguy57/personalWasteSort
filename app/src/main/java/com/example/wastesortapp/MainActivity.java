package com.example.wastesortapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
  private Bin bin1;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

  }
  public void goToGame(View v){
    Intent goToGame = new Intent(getApplicationContext(), GameActivity.class);
    startActivity(goToGame);
    finish();
  }//GoToGame
}
