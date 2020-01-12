package com.example.wastesortapp;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class GarbageItems extends game {
	private final DatabaseReference gameObjectRef;
	private FirebaseDatabase database;
	private DatabaseReference objectRef;
	private DatabaseReference DataRef;
	
	public String Color;
	public String itemName;
	
	public GarbageItems(int itemNum) {
		String itemId = String.valueOf(itemNum);
		database = FirebaseDatabase.getInstance();
		objectRef = database.getReference().child("1WTVDXleXTbtGu43obhTU9fwozWAtG0R1Cw464U3mvlk");
		gameObjectRef =  objectRef.child("gameObjects");
		
		DataRef = gameObjectRef.child(itemId);
		DatabaseReference colorDatabase = DataRef.child("Color");
		
		colorGrabber(colorDatabase);
		DatabaseReference itemNameDatabase = DataRef.child("Item");
		itemGrabber(itemNameDatabase);
		
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
	
	private void colorGrabber(DatabaseReference colorDatabase){
		colorDatabase.addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange( DataSnapshot dataSnapshot) {
				String Colors = dataSnapshot.getValue().toString();
				setColor(Colors);
			}
			@Override
			public void onCancelled(@NonNull DatabaseError databaseError) {
			}
		});
	}
	private void itemGrabber(DatabaseReference itemNameDatabase) {
		itemNameDatabase.addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange( DataSnapshot dataSnapshot) {
				String itemName = dataSnapshot.getValue().toString();
				setItemName(itemName);
			}
			@Override
			public void onCancelled(@NonNull DatabaseError databaseError) {
			}
		});
	}
	
}//GarbageItems
