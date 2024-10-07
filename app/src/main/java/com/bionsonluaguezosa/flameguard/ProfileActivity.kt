package com.bionsonluaguezosa.flameguard

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bionsonluaguezosa.flameguard.databinding.ActivityProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        fetchUserData()

        binding.editBtn.setOnClickListener {
            val intent = Intent(this, EditProfileActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        // Re-fetch user data every time the activity resumes
        fetchUserData()
    }

    private fun fetchUserData() {
        val currentUser = firebaseAuth.currentUser

        if (currentUser != null) {
            val userId = currentUser.uid

            firestore.collection("caller").document(userId).get()
                .addOnSuccessListener { document ->
                    if (document != null && document.exists()) {
                        val firstName = document.getString("firstName") ?: ""
                        val lastName = document.getString("lastName") ?: ""
                        val phoneNumber = document.getString("phoneNumber") ?: ""
                        val email = document.getString("email") ?: ""
                        val address = document.getString("address") ?: ""

                        binding.username.text = "$firstName $lastName"
                        binding.phoneNumber.text = phoneNumber
                        binding.email.text = email
                        binding.address.text = address
                    } else {
                        Toast.makeText(this, "User data not found", Toast.LENGTH_LONG).show()
                    }
                }
                .addOnFailureListener { exception ->
                    Toast.makeText(this, "Error fetching user data: ${exception.message}", Toast.LENGTH_LONG).show()
                    Log.e("Firestore", "Error fetching user data", exception)
                }
        } else {
            Toast.makeText(this, "User not logged in", Toast.LENGTH_LONG).show()
        }
    }
}
