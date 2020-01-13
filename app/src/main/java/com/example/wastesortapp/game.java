package com.example.wastesortapp;

import android.os.Build.VERSION_CODES;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.concurrent.CompletableFuture;

public class game extends AppCompatActivity {

  TextView colorTextView;
  TextView itemTextView;
  private String Color;
  private String itemName;

  @RequiresApi(api = VERSION_CODES.N)
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_game);
    colorTextView = findViewById(R.id.ColorTextView);
    itemTextView = findViewById(R.id.ItemNameTextView);

    //GarbageItems garbageItems = new GarbageItems(1);
    //Color =  garbageItems.getColor();

    itemInformationGrabber("1");
  }

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

  private void itemInformationGrabber(String itemId) {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference gameObjectRef = database.getReference().child("gameObjects");

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


}
