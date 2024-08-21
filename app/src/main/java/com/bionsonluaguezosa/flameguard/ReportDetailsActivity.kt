package com.bionsonluaguezosa.flameguard

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class ReportDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report_details)

        // Your existing code for initializing views and setting data

        val reportButton: View = findViewById(R.id.report_button)
        reportButton.setOnClickListener {
            // Handle button click to go back to MainActivity
            val intent = Intent(this@ReportDetailsActivity, MainActivity::class.java)
            startActivity(intent)
            finish() // Optional: Finish current activity to prevent going back to it with back button
        }
    }

    // Other methods and logic for your activity
}
