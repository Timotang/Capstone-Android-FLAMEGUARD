package com.bionsonluaguezosa.flameguard

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bionsonluaguezosa.flameguard.databinding.ActivityRegistrationBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class RegistrationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistrationBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    // Define EditText fields
    private lateinit var firstNameEditText: TextInputEditText
    private lateinit var lastNameEditText: TextInputEditText
    private lateinit var phoneNumberEditText: TextInputEditText
    private lateinit var passwordEditText: TextInputEditText
    private lateinit var emailEditText: TextInputEditText
    private lateinit var addressEditText: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Firebase Auth and Firestore
        firebaseAuth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        // Initialize views
        firstNameEditText = binding.firstname
        lastNameEditText = binding.lastname
        phoneNumberEditText = binding.phoneNumber
        passwordEditText = binding.password
        emailEditText = binding.email
        addressEditText = binding.address

        // Back button action
        binding.backButton.setOnClickListener {
            finish() // Go back to the previous activity
        }

        // Submit button action
        binding.submitButton.setOnClickListener {
            val firstName = firstNameEditText.text.toString()
            val lastName = lastNameEditText.text.toString()
            val phoneNumber = phoneNumberEditText.text.toString()
            val email = emailEditText.text.toString()
            val address = addressEditText.text.toString()
            val password = passwordEditText.text.toString()

            // Validate inputs
            if (firstName.isEmpty() || lastName.isEmpty() || phoneNumber.isEmpty() || email.isEmpty() || address.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            // Create a new user with email and password in Firebase Auth
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val user = firebaseAuth.currentUser
                        if (user != null) {
                            saveUserData(
                                firstName,
                                lastName,
                                phoneNumber,
                                email,
                                address,
                                user.uid
                            )
                        } else {
                            Toast.makeText(
                                this,
                                "User registration successful, but user data is not available",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    } else {
                        val errorMessage = task.exception?.message ?: "Registration failed"
                        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
                    }
                }
        }
    }

    // Function to save user data in Firestore
    private fun saveUserData(
        firstName: String,
        lastName: String,
        phoneNumber: String,
        email: String,
        address: String,
        userId: String
    ) {
        // Create a map to hold user data
        val userData = hashMapOf(
            "firstName" to firstName,
            "lastName" to lastName,
            "phoneNumber" to phoneNumber,
            "email" to email,
            "address" to address
        )

        // Save the data in Firestore under "caller" collection using the userId
        firestore.collection("caller").document(userId).set(userData)
            .addOnSuccessListener {
                Toast.makeText(this, "User data saved successfully", Toast.LENGTH_LONG).show()
                Log.d("Firestore", "User data saved successfully")
                // Navigate to LoginActivity after successful registration
                val intent = Intent(this@RegistrationActivity, LoginActivity::class.java)
                startActivity(intent)
                finish() // Finish current activity
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Error saving user data: ${e.message}", Toast.LENGTH_LONG)
                    .show()
                Log.e("Firestore", "Error saving user data: ${e.message}")
            }
    }
}
