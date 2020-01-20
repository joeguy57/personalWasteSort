package com.example.wastesortapp;

import static org.junit.Assert.*;

import org.junit.Test;

public class increaseScore {
  public int score = 3;
  Boolean wasPointScored = true;
  @Test
  public void increaseScore() {
    if (wasPointScored) {
      score += 1;
      assertEquals(score, 4);
    }//if

  }//increaseScore

}