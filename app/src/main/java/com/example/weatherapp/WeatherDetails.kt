package com.example.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class WeatherDetails : AppCompatActivity() {
    private lateinit var dailyTemperatures: Array<Double?>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_details)


        val backToMainScreen = findViewById<Button>(R.id.backToMainBtn)

        dailyTemperatures = intent.getSerializableExtra("dailyTemperatures") as Array<Double?>

        displayDetails()
        backToMainScreen.setOnClickListener {
            finish()
        }
    }


    private fun displayDetails() {
            val days = arrayOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
            val details = StringBuilder()

            var total = 0.0
            var count = 0

            for (i in dailyTemperatures.indices) {
                details.append("${days[i]}: ${dailyTemperatures[i] ?: 0.0} celcius\n")
                if (dailyTemperatures[i] != null) {
                    total += dailyTemperatures[i]!!
                    count++
                }
            }


            val detailsView = findViewById<TextView>(R.id.detailsView)
            val averageView = findViewById<TextView>(R.id.averageView)


            val average = if (count > 0) total / count else 0.0
            detailsView.text= details.toString()
            averageView.text = "Average screen time: %.2f hours".format(average)

        }
}