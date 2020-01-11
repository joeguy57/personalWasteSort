package com.example.wastesortapp;

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
	
	private String Color;
	private String itemName;
	
	public GarbageItems() {
		database = FirebaseDatabase.getInstance();
		objectRef = database.getReference().child("1WTVDXleXTbtGu43obhTU9fwozWAtG0R1Cw464U3mvlk");
		gameObjectRef =  objectRef.child("gameObjects");
		
		DataRef = gameObjectRef.child("1");
		DatabaseReference colorDatabase = DataRef.child("Color");
		
		colorDatabase.addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
				String Colors = dataSnapshot.getValue().toString();
				setColor(Colors);
			}
			@Override
			public void onCancelled(@NonNull DatabaseError databaseError) {
			}
		});
		
		DatabaseReference itemNameDatabase = DataRef.child("Item");
		
		itemNameDatabase.addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
				String itemName = dataSnapshot.getValue().toString();
				setItemName(itemName);
			}
			@Override
			public void onCancelled(@NonNull DatabaseError databaseError) {
			}
		});
		
	}//GarbageItems(Constructor)
	
	public String getColor() {
		return this.Color;
	}
	public void setColor(String color) {
		this.Color = color;
	}
	public String getItemName() {
		return this.itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
}//GarbageItems
