package com.bionsonluaguezosa.flameguard

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textview.MaterialTextView

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        // Find views
        val emailEditText: TextInputEditText = findViewById(R.id.email)
        val passwordEditText: TextInputEditText = findViewById(R.id.password)
        val signinButton: MaterialButton = findViewById(R.id.signin)
        val registerTextView: MaterialTextView = findViewById(R.id.register)

        // Set OnClickListener for sign-in button
        signinButton.setOnClickListener {
            // Example: Perform sign-in logic here
            // For demonstration, assume sign-in is successful and navigate to MainActivity
            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(intent)
            finish() // Optional: Finish current activity to prevent going back to it with back button
        }

        // Set OnClickListener for registration text view
        registerTextView.setOnClickListener {
            val intent = Intent(this@LoginActivity, RegistrationActivity::class.java)
            startActivity(intent)
        }
    }
}
