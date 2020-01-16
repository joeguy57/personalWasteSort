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

  public Sound(Context context){
    this.context = context;
  }//Sound

  public void initializeAllGameSounds(){
    initializeCorrectSound();
    initializeIncorrectSound();
    initializeTickingSound();
  }
  public void disableSound(){
    soundToggle = false;
  }//disableSound

  public void enableSound(){
    soundToggle = true;
  }//enableSound

  public void initializeCorrectSound(){
    correctSortSound = MediaPlayer.create(context, R.raw.correctsound);
  }//initializeCorrectSort
  public void initializeGameOverSound(){
    gameOverSound = MediaPlayer.create(context,R.raw.gameoversound);
  }
  public void initializeIncorrectSound(){
    incorrectSortSound = MediaPlayer.create(context,R.raw.incorrectsound);
  }//initializeIncorrectSort

  public void initializeTickingSound(){
    tickingSound = MediaPlayer.create(context,R.raw.tickingsound);
  }//initializeIncorrectSort

  public void playCorrectSound(){
    if(soundToggle == true){
      correctSortSound.start();
    }//if

  }//playCorrectSound
  public void playIncorrectSound(){
    if(soundToggle == true){
      incorrectSortSound.start();
    }//if

  }//playIncorrectSound
  public void playTickingSound() {
    if (soundToggle == true) {
      tickingSound.start();
    }
  }//playTickingSound
  public void playGameOverSound(){
    gameOverSound.start();
  }
}
