package com.example.wastesortapp;

public class GarbageInfo {

  private String binType;
  private String Name;
  private String Description;

  public GarbageInfo() {
  }

  public GarbageInfo(String binType, String name, String description) {
    binType = binType;
    Name = name;
    Description = description;
  }

  public String getBinType() {
    return binType;
  }

  public void setBinType(String color) {
    binType = color;
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



}
