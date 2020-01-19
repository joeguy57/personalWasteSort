/**
 * Sound.java This class takes care of all sound that occurs in game.java
 */
package com.example.wastesortapp;

import android.content.Context;
import android.media.MediaPlayer;

public class Sound {

  private Context context;
  private MediaPlayer correctSortSound;
  private MediaPlayer incorrectSortSound;
  private MediaPlayer tickingSound;
  private MediaPlayer gameOverSound;

  private static boolean soundToggle = true;

  public Sound(Context context) {
    this.context = context;
  }//Sound

  public void initializeAllGameSounds() {
    initializeCorrectSound();
    initializeIncorrectSound();
    initializeTickingSound();
    initializeGameOverSound();
  }

  public void disableSound() {
    soundToggle = false;
  }//disableSound

  public void enableSound() {
    soundToggle = true;
  }//enableSound

  private void initializeCorrectSound() {
    correctSortSound = MediaPlayer.create(context, R.raw.correctsound);
  }//initializeCorrectSort

  private void initializeGameOverSound() {
    gameOverSound = MediaPlayer.create(context, R.raw.gameoversound);
  }

  private void initializeIncorrectSound() {
    incorrectSortSound = MediaPlayer.create(context, R.raw.incorrectsound);
  }//initializeIncorrectSort

  private void initializeTickingSound() {
    tickingSound = MediaPlayer.create(context, R.raw.tickingsound2);
  }//initializeIncorrectSort

  public void playCorrectSound() {
    if (soundToggle == true) {
      correctSortSound.start();
    }//if

  }//playCorrectSound

  /**
   * plays the incorrect sound
   */
  public void playIncorrectSound() {
    if (soundToggle == true) {
      incorrectSortSound.start();
    }//if
  }//playIncorrectSound

  /**
   * Plays the ticking sound
   */
  public void playTickingSound() {
    if (soundToggle == true) {
      tickingSound.start();
    }//if
  }//playTickingSound

  /**
   * Plays the game over sound
   */
  public void playGameOverSound() {
    gameOverSound.start();
  }//playGameOverSound
}//Sound
