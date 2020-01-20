/**
 * This class allows the user to: 1. Search for items they want to throw away. 2. see through the
 * catalog for general reference This will allow the user to gain more information in an instant,
 * about the zero waste management steps taken by Augustana.
 *
 * This app holds the features to scroll through item and search for an option that is needed.
 *
 * Methods: onStart() - on start runs on the initialization of the app...
 * search() - Goes through a global list created in other to store the cardView place holder.
 * onBackPressed- When the back button on the interface is pressed, a Dialog (alert pops up)...
 */
package com.example.wastesortapp;

import android.content.DialogInterface;
import android.content.Intent;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

public class DisposableHelp extends AppCompatActivity {

  private SearchView searchView;
  private RecyclerView mGarbageList;
  private DatabaseReference mData;
  private ArrayList<GarbageInfo> list;
  private ConstraintLayout creditsLay;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_disposable_help);
    creditsLay = findViewById(R.id.creditsLayout);
    creditsLay.setVisibility(View.INVISIBLE);
    //back button
    ImageView backBtn = findViewById(R.id.backBtn);
    backBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent goToMain = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(goToMain);
        finish();
      }
    });
    mGarbageList = findViewById(R.id.recycleView);
    searchView = findViewById(R.id.searchView);
    mGarbageList.setHasFixedSize(true);
    mGarbageList.setLayoutManager(new LinearLayoutManager(this));

    mData = FirebaseDatabase.getInstance().getReference()
        .child("1WTVDXleXTbtGu43obhTU9fwozWAtG0R1Cw464U3mvlk").child("Catalogue");
  }

  /**
   * on start runs on the initialization of the app. Here we go through the Firebase database,
   * in order to display the item information to the user.
   *
   * if the user starts typing in the search bar, then a filtering on the recycler view is then
   * made to get the closet answer to the search.
   */
  @Override
  protected void onStart() {
    super.onStart();
    if (mData != null) {
      mData.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
          if (dataSnapshot.exists()) {
            list = new ArrayList<>();

            for (DataSnapshot ds : dataSnapshot.getChildren()) {
              if (!ds.getKey().equals("0")) {
                list.add(ds.getValue(GarbageInfo.class));
              }
            }
            Adapter adapter = new Adapter(list);
            mGarbageList.setAdapter(adapter);
          }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
      });
    }
    if (searchView != null) {
      searchView.setOnQueryTextListener(new OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
          return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
          search(newText);
          return true;
        }
      });
    } else {
      onStart();
    }
  }

  /**
   * This method takes in a users input this then causes the recycler view to reduce
   * the number of options viewed.
   * @param newText - the text being typed by the user gets added in to this function to narrow
   * down the the options
   */
  private void search(String newText) {
    ArrayList<GarbageInfo> myList = new ArrayList<>();
    for (GarbageInfo object : list) {
      if (object.getName().toLowerCase().contains(newText.toLowerCase())) {
        myList.add(object);
      }
    }
    Adapter adapter = new Adapter(myList);
    mGarbageList.setAdapter(adapter);
    if (newText.toLowerCase().contains("credits")) {
      creditsLay.setVisibility(View.VISIBLE);
      return;
    }
    creditsLay.setVisibility(View.INVISIBLE);
  }//search

  /**
   *When the back button on Navigation is pressed then we
   *
   */
  public void onBackPressed() {
    AlertDialog.Builder confirmation = new AlertDialog.Builder(this);

    confirmation.setMessage("Are you sure you want to go exit?")
        //.setCancelable(true)
        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int which) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
          }
        })

        .setNegativeButton("No", new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int which) {
            dialog.cancel();
          }
        });
    AlertDialog alertDialog = confirmation.create();
    alertDialog.show();

  }//onBackPressed
}//DisposableHelp
