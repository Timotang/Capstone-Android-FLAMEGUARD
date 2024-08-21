package com.bionsonluaguezosa.flameguard

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.ComponentActivity
import androidx.core.view.WindowCompat
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class CaptureFireActivity : ComponentActivity() {

    private val REQUEST_IMAGE_CAPTURE = 1
    private lateinit var cameraImageView: ImageView
    private lateinit var cameraTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set the content view to your XML layout
        setContentView(R.layout.activity_capture_fire)

        // Enable edge-to-edge display
        WindowCompat.getInsetsController(window, window.decorView).isAppearanceLightStatusBars = true

        // Initialize the ImageView and TextView
        cameraImageView = findViewById(R.id.camera_image)

        // Set up the camera button click listener
        cameraImageView.setOnClickListener {
            dispatchTakePictureIntent()
        }

        // Optionally, set up the TextView click listener if needed
        cameraTextView.setOnClickListener {
            dispatchTakePictureIntent()
        }

        // Set up bottom buttons
        findViewById<Button>(R.id.take_again_button).setOnClickListener {
            // Handle Take Again button click
            dispatchTakePictureIntent()
        }

        findViewById<Button>(R.id.next_button).setOnClickListener {
            // Handle Next button click
            goToReportDetailsActivity()
        }
    }

    private fun dispatchTakePictureIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            // Ensure that there's a camera activity to handle the intent
            takePictureIntent.resolveActivity(packageManager)?.also {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            cameraImageView.setImageBitmap(imageBitmap)
        } else {
            Toast.makeText(this, "Failed to capture image", Toast.LENGTH_SHORT).show()
        }
    }

    private fun goToReportDetailsActivity() {
        val intent = Intent(this, ReportDetailsActivity::class.java)
        startActivity(intent)
    }
}
