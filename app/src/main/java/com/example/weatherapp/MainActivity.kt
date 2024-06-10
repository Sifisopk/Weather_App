package com.example.weatherapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainScreenBtn = findViewById<Button>(R.id.mainScreenBtn)
        val exitAppBtn = findViewById<Button>(R.id.exitAppBtn)

        // code for linking the two activities
        mainScreenBtn.setOnClickListener{
            intent = Intent(this, MainScreen::class.java)
            startActivity(intent)
        }

        exitAppBtn.setOnClickListener {
            finishAffinity()
        }

    }
}