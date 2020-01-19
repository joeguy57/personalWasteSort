/**
 * Sound.java This class takes care of all sound that occurs in game.java
 * It contains four sounds that are played at various staged in the game. These
 * sounds can be easily enabled and disabled with enable and disable buttons also located
 * in game.java
 *
 * @author Jared Matson
 *
 * Methods :
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

  /**
   * Constructor for sound
   *
   * @param context which activity sound is being created in
   */
  public Sound(Context context) {
    this.context = context;
  }//Sound

  /**
   * Initializes all sounds in one method call
   */
  public void initializeAllGameSounds() {
    initializeCorrectSound();
    initializeIncorrectSound();
    initializeTickingSound();
    initializeGameOverSound();
  }//initializeAllGameSounds

  /**
   * Disables sound in game
   */
  public void disableSound() {
    soundToggle = false;
  }//disableSound

  /**
   * Enables sound in game
   */
  public void enableSound() {
    soundToggle = true;
  }//enableSound

  /**
   * Initializes the correctSound sound in game
   */
  private void initializeCorrectSound() {
    correctSortSound = MediaPlayer.create(context, R.raw.correctsound);
  }//initializeCorrectSort

  /**
   * Initializes the incorrectSound sound in game
   */
  private void initializeGameOverSound() {
    gameOverSound = MediaPlayer.create(context, R.raw.gameoversound);
  }//initializeGameOverSound

  /**
   * Initializes the ticking sound in game
   */
  private void initializeIncorrectSound() {
    incorrectSortSound = MediaPlayer.create(context, R.raw.incorrectsound);
  }//initializeIncorrectSort

  /**
   * Initializes the ticking sound in game
   */
  private void initializeTickingSound() {
    tickingSound = MediaPlayer.create(context, R.raw.tickingsound2);
  }//initializeIncorrectSort

  /**
   * Plays the correct sound (if player scores a point)
   */
  public void playCorrectSound() {
    if (soundToggle == true) {
      correctSortSound.start();
    }//if

  }//playCorrectSound

  /**
   * Plays the incorrect sound (if player loses a point)
   */
  public void playIncorrectSound() {
    if (soundToggle == true) {
      incorrectSortSound.start();
    }//if
  }//playIncorrectSound

  /**
   * Plays the ticking sound when there is only 15 seconds left in the game
   */
  public void playTickingSound() {
    if (soundToggle == true) {
      tickingSound.start();
    }//if
  }//playTickingSound

  /**
   * Plays the game over sound when the game is completed
   */
  public void playGameOverSound() {
    gameOverSound.start();
  }//playGameOverSound
}//Sound
