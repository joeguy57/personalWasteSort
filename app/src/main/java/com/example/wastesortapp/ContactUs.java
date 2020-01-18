package com.example.wastesortapp;

import android.content.DialogInterface;
import androidx.appcompat.app.AlertDialog;
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
	}//goToBrower

	public void onBackPressed() {
		AlertDialog.Builder confirmation = new AlertDialog.Builder(this);

		confirmation.setMessage("Are you sure you want to go back?")
				//.setCancelable(true)
				.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						finish();
						System.exit(0);
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

	}//onbackpressed
}
