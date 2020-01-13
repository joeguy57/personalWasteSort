package com.example.wastesortapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ContactUs extends AppCompatActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_contact_us);
		ImageView backBtn = findViewById(R.id.backBtn);
		
		backBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}
	
	public void goToBrowser(View view) {
		Intent browserAccess = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.ualberta" +
        ".ca/augustana/about-us/departments-offices-and-units/lab/sustainability/campus" +
        "-initiatives/waste"));
		startActivity(browserAccess);
	}
}
