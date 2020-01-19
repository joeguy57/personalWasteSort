package com.example.wastesortapp;

import static org.junit.Assert.*;

import androidx.appcompat.app.AppCompatActivity;
import org.junit.Test;

public class gameTest extends AppCompatActivity {
  game game;
  @Test
  public void checkSound() {
    Sound sound = new Sound(this);
    sound.disableSound();
    assert(game.disableSoundButton.getAlpha() == 0);
  }
}