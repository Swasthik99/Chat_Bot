package com.example.chat_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class EditProfileActivity extends AppCompatActivity {

    private ImageView editProfileImage;
    private EditText editProfileName;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        editProfileImage = findViewById(R.id.edit_profile_image);
        editProfileName = findViewById(R.id.edit_profile_name);
        saveButton = findViewById(R.id.save_button);

        // Retrieve the current profile name and image from the intent
        Intent intent = getIntent();
        String currentProfileName = intent.getStringExtra("profileName");
        // If the profile name is not empty, set it in the EditText
        if (currentProfileName != null && !currentProfileName.isEmpty()) {
            editProfileName.setText(currentProfileName);
        }

        // Set click listener for the save button
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the edited profile name
                String editedProfileName = editProfileName.getText().toString().trim();

                // Pass the edited profile name back to the Profile activity
                Intent resultIntent = new Intent();
                resultIntent.putExtra("editedProfileName", editedProfileName);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }
}
