package com.example.wastesortapp;

/**
 * The bin class represents the colored bins within the gameActivity. They will be
 * when a bin has an item dragged over it, will access a unique function which will check an array
 * to see if the information matches
 */
public class Bin {
  public String color = "";
  public String info = "";

  public Bin(String color, String info){
    this.color = color;
    this.info = info;
  }//Bin
  public String getColor(){
    return color;
  }//getColor
  public String getInfo(){
    return info;
  }//getInfo
}
