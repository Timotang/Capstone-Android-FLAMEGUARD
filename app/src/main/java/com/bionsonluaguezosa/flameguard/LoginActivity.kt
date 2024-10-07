package com.bionsonluaguezosa.flameguard

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bionsonluaguezosa.flameguard.databinding.ActivityLoginBinding
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textview.MaterialTextView
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Firebase Auth
        firebaseAuth = FirebaseAuth.getInstance()

        // Find views
        val emailEditText: TextInputEditText = binding.email
        val passwordEditText: TextInputEditText = binding.password
        val signinButton: MaterialButton = binding.signin
        val registerTextView: MaterialTextView = binding.register

        // Set OnClickListener for sign-in button
        signinButton.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            // Validate inputs
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter both email and password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Perform authentication using Firebase
            firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // Sign-in success, navigate to MainActivity
                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
                        startActivity(intent)
                        finish() // Optional: Finish current activity to prevent going back to it
                    } else {
                        // Sign-in failed, display an error message
                        Toast.makeText(this, "Authentication failed: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                        Log.e("LoginActivity", "Authentication failed", task.exception)
                    }
                }
        }

        // Set OnClickListener for registration text view
        registerTextView.setOnClickListener {
            val intent = Intent(this@LoginActivity, RegistrationActivity::class.java)
            startActivity(intent)
        }
    }
}
