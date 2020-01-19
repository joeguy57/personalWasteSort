package com.example.wastesortapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_disposable_help);
    //back button
    ImageView backBtn = findViewById(R.id.backBtn);
    backBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
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
          if (searchView == null) {
            onStart();
          }
          return true;
        }
      });
    }
  }

  private void search(String newText) {
    ArrayList<GarbageInfo> myList = new ArrayList<>();
    for (GarbageInfo object : list) {
      if (object.getName().toLowerCase().contains(newText.toLowerCase())) {
        myList.add(object);
      }
    }
    Adapter adapter = new Adapter(myList);
    mGarbageList.setAdapter(adapter);
  }
}//DisposableHelp
