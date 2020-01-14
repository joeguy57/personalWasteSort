package com.example.wastesortapp;

import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class game extends AppCompatActivity {

  ImageView imageView;
  private ObjectAnimator mAnimation;
  private DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
  //private DatabaseReference databaseReference = firebaseDatabase.getReference();
  private DatabaseReference imagesUrlsRef = rootRef.child("gameObjects");
  private ValueEventListener valueEventListener = new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
  };



  //-----------------------------------------------------------------------------------
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_game);

    imageView = findViewById(R.id.imageGet);
    ImageView backBtn = findViewById(R.id.backBtn);
    final ProgressBar progressBar = (ProgressBar) findViewById(R.id.timerBar);
    backBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        finish();
      }
    });
    mAnimation = ObjectAnimator.ofInt(progressBar, "progress", 100, 0);
    mAnimation.setDuration(7000);
    mAnimation.setInterpolator(new DecelerateInterpolator());
    mAnimation.addListener(new Animator.AnimatorListener() {
      @Override
      public void onAnimationStart(Animator animator) {
      }

      @Override
      public void onAnimationEnd(Animator animator) {
        //do something when the countdown is complete

      }

      @Override
      public void onAnimationCancel(Animator animator) {
      }

      @Override
      public void onAnimationRepeat(Animator animator) {
      }
    });
  }

  @Override
  protected void onStart() {
    super.onStart();
    mAnimation.start();
    imagesUrlsRef.addValueEventListener(new ValueEventListener() {
      @Override
      public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String link = dataSnapshot.child("1").child("Image").getValue(String.class);
                Picasso.get().load(link).into(imageView);
      }

      @Override
      public void onCancelled(@NonNull DatabaseError databaseError) {

      }
    });

  }//onStart


  protected void getImage(DataSnapshot dataSnapshot){
    List<String> urlList = new ArrayList<>();

    for(DataSnapshot dataShot : dataSnapshot.getChildren()){
      String url = dataShot.child("url").getValue(String.class);
      urlList.add(url);
    }//for

    int urlCount = urlList.size();
    System.out.println(urlCount);
    TextView urlNum = findViewById(R.id.textView3);
    urlNum.setText(urlCount);
    int randNum = new Random().nextInt(urlCount);
    List<String> randomUrlList = new ArrayList<>();


    }//getImage
}
