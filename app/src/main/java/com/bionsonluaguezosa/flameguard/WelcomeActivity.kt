package com.bionsonluaguezosa.flameguard

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.bionsonluaguezosa.flameguard.MainActivity
import com.bionsonluaguezosa.flameguard.R

class WelcomeActivity : AppCompatActivity() {

    private val DELAY_MILLISECONDS: Long = 3000 // 3 seconds delay

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        // Delayed transition to MainActivity after 3 seconds
        Handler().postDelayed({
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish() // Finish WelcomeActivity to prevent going back to it on back press
        }, DELAY_MILLISECONDS)
    }
}
