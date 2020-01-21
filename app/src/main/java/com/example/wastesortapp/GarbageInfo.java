/**
 * GarbageInfo.java This class is used in our Waste Guide(WasteGuide.java) to help set and
 * retrieve specific information regarding each waste item such as the color of bin it corresponds
 * to, its name, and also any additional information regarding that item.
 *
 * Methods:
 *  - GarbageInfo(String,String,String)
 *      Constructor of an item with garbage information
 *  - getBinType()
 *      Returns the binType of the item
 *  - setBinType(String)
 *      Changes the binType of the item
 *  - getName()
 *      Returns the name of the item
 *   - setName(String)
 *      Changes the name of the item
 *   - getDescription()
 *      Returns the description of the item
 *   - setDescription(String)
 *      Changes the description of the item
 */
package com.example.wastesortapp;

class GarbageInfo {

  private String binType;
  private String Name;
  private String Description;

  /**
   * Even if this constructor is empty this line of code must remain in order for code to
   * run successfully. This is not a bug, as GarbageInfo() call the constructor in object
   * class to perform the necessary actions.
   */
  public GarbageInfo() {
  }

  /**
   * Constructor for GarbageInfo
   *
   * @param binType color of bin item will go in
   * @param name name of item
   * @param description extra information regarding that item
   */
  public GarbageInfo(String binType, String name, String description) {
    this.binType = binType;
    this.Name = name;
    this.Description = description;
  }//GarbageInfo

  /**
   * Returns binType of an item (color)
   *
   * @return binType
   */
  public String getBinType() {
    return binType;
  }//getBinType

  /**
   * Will change binType of an item (color)
   *
   * @param color the color the binType is being changed to
   */
  public void setBinType(String color) {
    binType = color;
  }//setBinType

  /**
   * Will return the name of an item
   *
   * @return name of item
   */
  public String getName() {
    return Name;
  }//getName

  /**
   * Will set the name of an item
   *
   * @param name the name the item will now be called
   */
  public void setName(String name) {
    Name = name;
  }//setName

  /**
   * Will return the description reguarding an item
   *
   * @return description of item
   */
  public String getDescription() {
    return Description;
  }//getDescription

  /**
   * Will set the description of an item
   *
   * @param description description the item will now be corresponded with
   */
  public void setDescription(String description) {
    Description = description;
  }//setDescription
}//GarbageInfo
