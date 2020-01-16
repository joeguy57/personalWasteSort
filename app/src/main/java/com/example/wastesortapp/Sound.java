package com.example.wastesortapp;

import android.content.Context;
import android.media.MediaPlayer;
import android.provider.MediaStore.Audio.Media;

public class Sound {
  private Context context;
  private MediaPlayer correctSortSound;
  private MediaPlayer incorrectSortSound;
  private MediaPlayer timerSound;
  private MediaPlayer buzzerSound;

  private static boolean soundToggle = true;

  public Sound(Context context){
    this.context = context;
  }//Sound

  public void disableSound(){
    soundToggle = false;
  }//disableSound

  public void enableSound(){
    soundToggle = true;
  }//enableSound

  public void initializeCorrectSort(){

  }//initializeCorrectSort

  public void initializeIncorrectSort(){

  }//initializeIncorrectSort
}
