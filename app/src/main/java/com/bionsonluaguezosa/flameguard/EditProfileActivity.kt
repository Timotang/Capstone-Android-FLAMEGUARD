package com.bionsonluaguezosa.flameguard

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button

class EditProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_edit_profile)

        // Find the Cancel button
        val cancelButton: Button = findViewById(R.id.cancel_btn)
        // Set OnClickListener to navigate back to ProfileActivity
        cancelButton.setOnClickListener {
            finish() // This will close the EditProfileActivity and return to ProfileActivity
        }

        // Find the Apply button
        val applyButton: Button = findViewById(R.id.apply_btn)
        // Set OnClickListener to navigate back to ProfileActivity
        applyButton.setOnClickListener {
            // Handle data submission here if necessary
            // For now, just finish the activity
            finish() // This will close the EditProfileActivity and return to ProfileActivity
        }
    }
}
