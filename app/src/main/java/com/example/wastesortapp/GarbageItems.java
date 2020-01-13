package com.example.wastesortapp;

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

  public GarbageItems(int itemNum) {
    final String itemId = String.valueOf(itemNum);

    database = FirebaseDatabase.getInstance();
    gameObjectRef = database.getReference().child("gameObjects");
    itemInformationGrabber(gameObjectRef, itemId);

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

  private void itemInformationGrabber(DatabaseReference gameObjectRef, String itemId) {
    DatabaseReference dataReference = gameObjectRef.child(itemId);
    DatabaseReference itemColor = dataReference.child("Color");
    DatabaseReference itemNames = dataReference.child("Item");
    itemColor.addValueEventListener(new ValueEventListener() {
      @Override
      public void onDataChange( DataSnapshot dataSnapshot) {
        String color = dataSnapshot.getValue(String.class);
        setColor(color);
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

  }



}//GarbageItems
