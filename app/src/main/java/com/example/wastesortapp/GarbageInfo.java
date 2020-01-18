package com.example.wastesortapp;

public class GarbageInfo {

  private String Color;
  private String Name;

  public GarbageInfo() {
  }

  public GarbageInfo(String color, String name, String description) {
    Color = color;
    Name = name;
    Description = description;
  }

  public String getColor() {
    return Color;
  }

  public void setColor(String color) {
    Color = color;
  }

  public String getName() {
    return Name;
  }

  public void setName(String name) {
    Name = name;
  }

  public String getDescription() {
    return Description;
  }

  public void setDescription(String description) {
    Description = description;
  }

  private String Description;

}
