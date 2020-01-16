package com.example.wastesortapp;

import androidx.annotation.NonNull;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

class GarbageItems  {

  private final DatabaseReference gameObjectRef;

  GarbageItems() {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    gameObjectRef = database.getReference().child("gameObjects");
  }//GarbageItems(Constructor)

  void readColorData(final FirebaseCallback firebaseCallback, int itemKey){
    ValueEventListener valueEventListener = new ValueEventListener() {
      @Override
      public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        String color = dataSnapshot.getValue(String.class);
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
  }//readColorData

  void readItemNameData(final FirebaseCallback firebaseCallback, int itemKey){
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
  }//readItemNameData

  void readRandomKey(final DataCountCallback dataCountCallback){
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
  }//readRandomKey

  interface FirebaseCallback{
    void onCallback(String result);
  }

  interface DataCountCallback{
    void onCallback(int result);
  }



}//GarbageItems
