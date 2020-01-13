package com.example.wastesortapp;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;


import androidx.annotation.Nullable;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.Map;

public class GarbageItems extends game {

  private final DatabaseReference gameObjectRef;
  private FirebaseDatabase database;

  public String Color;
  public String itemName;

  public GarbageItems() {
    //final String itemId = String.valueOf(itemNum);

    database = FirebaseDatabase.getInstance();
    gameObjectRef = database.getReference().child("gameObjects");

    /*readColorData(new FirebaseCallback() {
      @Override
      public void onCallback(String result) {
            Log.d("Color",result);
      }
    }, itemId);
    readItemNameData(new FirebaseCallback() {
      @Override
      public void onCallback(String result) {
        Log.d("Item",result);
      }
    }, itemId);*/
  }//GarbageItems(Constructor)


  public String getColor() {
    return Color;
  }

  public void setColor(String color) {
    this.Color = color;
  }

  public String getItemName() {
    return itemName;
  }

  public void setItemName(String itemName) {
    this.itemName = itemName;

  }

  /*private void itemInformationGrabber(DatabaseReference gameObjectRef, String itemId) {
    DatabaseReference dataReference = gameObjectRef.child(itemId);
    DatabaseReference itemColor = dataReference.child("Color");
    DatabaseReference itemNames = dataReference.child("Item");
    itemColor.addValueEventListener(new ValueEventListener() {
      @Override
      public void onDataChange( DataSnapshot dataSnapshot) {
        String color = dataSnapshot.getValue(String.class);
      }
      @Override
      public void onCancelled(@NonNull DatabaseError databaseError) {
      }
    });
    itemNames.addValueEventListener(new ValueEventListener() {
      @Override
      public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        String name = dataSnapshot.getValue(String.class);
        setItemName(name);
      }
      @Override
      public void onCancelled(@NonNull DatabaseError databaseError) {
      }
    });

  }//itemInformationGrabber
*/
  protected void readColorData (final FirebaseCallback firebaseCallback, int itemKey){
    ValueEventListener valueEventListener = new ValueEventListener() {
      @Override
      public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        String color = dataSnapshot.getValue(String.class);
        setColor(color);

        firebaseCallback.onCallback(color);
      }

      @Override
      public void onCancelled(@NonNull DatabaseError databaseError) {

      }
    };
    String itemId = String.valueOf(itemKey);
    DatabaseReference dataReference = gameObjectRef.child(itemId);
    DatabaseReference itemColor = dataReference.child("Color");
    itemColor.addValueEventListener(valueEventListener);
  }

  protected void readItemNameData(final FirebaseCallback firebaseCallback, int itemKey){
    ValueEventListener valueEventListener = new ValueEventListener() {
      @Override
      public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        String item = dataSnapshot.getValue(String.class);
        firebaseCallback.onCallback(item);
      }

      @Override
      public void onCancelled(@NonNull DatabaseError databaseError) {

      }
    };
    String itemId = String.valueOf(itemKey);
    DatabaseReference dataReference = gameObjectRef.child(itemId);
    DatabaseReference itemNames = dataReference.child("Item");
    itemNames.addValueEventListener(valueEventListener);
  }
  protected void readRandomKey(final DataCountCallback dataCountCallback){
    ValueEventListener valueEventListener = new ValueEventListener() {
      @Override
      public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        int dataCount = (int) dataSnapshot.getChildrenCount();
        int randomKey = (int) (Math.random()*((dataCount - 1) + 1)) + 1;
        dataCountCallback.onCallback(randomKey);
      }

      @Override
      public void onCancelled(@NonNull DatabaseError databaseError) {

      }
    };
    gameObjectRef.addValueEventListener(valueEventListener);
  }
  protected interface FirebaseCallback{
    void onCallback(String result);
  }

  protected interface DataCountCallback{
    void onCallback(int result);
  }



}//GarbageItems
