package com.example.chat_app;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Profile extends AppCompatActivity {

    private ImageView profileImage;
    private TextView profileName;
    private TextView profileEmail;
    private Button editProfileButton,button;
    FirebaseAuth auth;
    FirebaseUser user;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profileImage = findViewById(R.id.profile_image);
        profileName = findViewById(R.id.profile_name);
        profileEmail = findViewById(R.id.profile_email);
        editProfileButton = findViewById(R.id.edit_profile_button);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        profileEmail.setText(user.getEmail());
        button = findViewById(R.id.b2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);


            }
        });

        // Set the current profile name in the TextView
        String currentProfileName = " "; // Replace with your logic to retrieve the current profile name
        profileName.setText(currentProfileName);

        // Set click listener for the edit profile button
        editProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the EditProfileActivity to allow the user to edit their profile
                Intent intent = new Intent(Profile.this, EditProfileActivity.class);
                intent.putExtra("profileName", currentProfileName);
                startActivityForResult(intent,1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            // Retrieve the edited profile name from the result intent
            String editedProfileName = data.getStringExtra("editedProfileName");

            // Update the profile name in the TextView
            profileName.setText(editedProfileName);
        }
    }
}
