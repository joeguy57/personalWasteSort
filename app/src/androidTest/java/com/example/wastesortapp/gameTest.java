package com.example.wastesortapp;

import androidx.appcompat.app.AppCompatActivity;
import org.junit.Test;

public class gameTest extends AppCompatActivity {

  private Game game;
  @Test
  public void checkSound() {
    Sound sound = new Sound(this);
    sound.disableSound();
    //assert(Game.disableSoundButton.getAlpha() == 0);
  }

}