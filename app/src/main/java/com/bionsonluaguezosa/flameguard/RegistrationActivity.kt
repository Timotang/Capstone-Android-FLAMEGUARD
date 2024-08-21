package com.bionsonluaguezosa.flameguard

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class RegistrationActivity : AppCompatActivity() {
    private lateinit var firstNameEditText: TextInputEditText
    private lateinit var lastNameEditText: TextInputEditText
    private lateinit var phoneNumberEditText: TextInputEditText
    private lateinit var passwordEditText: TextInputEditText
    private lateinit var emailEditText: TextInputEditText
    private lateinit var addressEditText: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        // Initialize views
        firstNameEditText = findViewById(R.id.firstname)
        lastNameEditText = findViewById(R.id.lastname)
        phoneNumberEditText = findViewById(R.id.phoneNumber)
        passwordEditText = findViewById(R.id.password)
        emailEditText = findViewById(R.id.email)
        addressEditText = findViewById(R.id.address)

        val submitButton: Button = findViewById(R.id.submit_button)
        submitButton.setOnClickListener {
            // Example: Navigate to MainActivity after registration
            val intent = Intent(this@RegistrationActivity, LoginActivity::class.java)
            startActivity(intent)
            finish() // Optional: Finish current activity to prevent going back to it with back button
        }

        val backButton: Button = findViewById(R.id.back_button)
        backButton.setOnClickListener {
            finish()
        }
    }
}
