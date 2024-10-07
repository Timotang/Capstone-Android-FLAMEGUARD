package com.bionsonluaguezosa.flameguard

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class EditProfileActivity : AppCompatActivity() {

    private lateinit var firestore: FirebaseFirestore
    private lateinit var userId: String  // The current user ID

    // UI components
    private lateinit var firstNameEditText: EditText
    private lateinit var lastNameEditText: EditText
    private lateinit var phoneNumberEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var addressEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        // Initialize Firestore
        firestore = FirebaseFirestore.getInstance()

        // Fetch the current user ID from FirebaseAuth
        val currentUser = FirebaseAuth.getInstance().currentUser
        userId = currentUser?.uid ?: ""

        if (userId.isEmpty()) {
            Toast.makeText(this, "Error: No user is logged in", Toast.LENGTH_SHORT).show()
            finish()  // Exit if user is not logged in
            return
        }

        // Bind UI elements to their respective views
        firstNameEditText = findViewById(R.id.edit_firstname)
        lastNameEditText = findViewById(R.id.edit_lastname)
        phoneNumberEditText = findViewById(R.id.edit_phonenumber)
        emailEditText = findViewById(R.id.edit_email)
        addressEditText = findViewById(R.id.edit_address)

        val cancelButton: Button = findViewById(R.id.cancel_btn)
        val applyButton: Button = findViewById(R.id.apply_btn)

        // Load profile data from Firestore
        loadProfileData()

        // Cancel button action: close activity without saving
        cancelButton.setOnClickListener {
            finish()
        }

        // Apply button action: update profile data and save to Firestore
        applyButton.setOnClickListener {
            updateProfileData()
        }
    }

    // Function to load the current user profile data from Firestore
    private fun loadProfileData() {
        firestore.collection("caller").document(userId)
            .get()
            .addOnSuccessListener { document ->
                if (document != null && document.exists()) {
                    firstNameEditText.setText(document.getString("firstName") ?: "")
                    lastNameEditText.setText(document.getString("lastName") ?: "")
                    phoneNumberEditText.setText(document.getString("phoneNumber") ?: "")
                    emailEditText.setText(document.getString("email") ?: "")
                    addressEditText.setText(document.getString("address") ?: "")
                } else {
                    Toast.makeText(this, "Profile data not found", Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener { exception ->
                Toast.makeText(this, "Error fetching data: ${exception.message}", Toast.LENGTH_SHORT).show()
            }
    }

    // Function to update the user's profile data in Firestore
    private fun updateProfileData() {
        // Get the data from the EditText fields
        val updatedData = hashMapOf<String, Any>(
            "firstName" to firstNameEditText.text.toString(),
            "lastName" to lastNameEditText.text.toString(),
            "phoneNumber" to phoneNumberEditText.text.toString(),
            "email" to emailEditText.text.toString(),
            "address" to addressEditText.text.toString()
        )

        // Check if inputs are valid
        if (validateInput(updatedData)) {
            // Update the document in Firestore
            firestore.collection("caller").document(userId)
                .update(updatedData)
                .addOnSuccessListener {
                    Toast.makeText(this, "Profile updated successfully", Toast.LENGTH_SHORT).show()
                    finish()  // Close the activity
                }
                .addOnFailureListener { exception ->
                    Toast.makeText(this, "Failed to update profile: ${exception.message}", Toast.LENGTH_SHORT).show()
                }
        } else {
            Toast.makeText(this, "Please fill out all fields correctly", Toast.LENGTH_SHORT).show()
        }
    }

    // Optional: Validation method to ensure the data is filled out correctly
    private fun validateInput(updatedData: Map<String, Any>): Boolean {
        // Simple check to ensure no fields are empty
        return updatedData.values.all { it.toString().isNotEmpty() }
    }
}
