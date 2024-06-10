package com.example.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class WeatherDetails : AppCompatActivity() {
    private lateinit var minTemps: Array<Int?>
    private lateinit var maxTemps: Array<Int?>
    private lateinit var conditions: Array<String?>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_details)


        val backBtn = findViewById<Button>(R.id.backToMainBtn)
        backBtn.setOnClickListener {
            finish()
        }

        minTemps = intent.getSerializableExtra("minTemps") as Array<Int?>
        maxTemps = intent.getSerializableExtra("maxTemps") as Array<Int?>
        conditions = intent.getSerializableExtra("conditions") as Array<String?>

        displayDetails()
    }

    private fun displayDetails() {
        val days = arrayOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
        val details = StringBuilder()

        var totalMin = 0
        var totalMax = 0
        var count = 0

        for (i in days.indices) {
            details.append("${days[i]}: Min: ${minTemps[i]}°, Max: ${maxTemps[i]}°, Condition: ${conditions[i]}\n")
            if (minTemps[i] != null && maxTemps[i] != null) {
                totalMin += minTemps[i]!!
                totalMax += maxTemps[i]!!
                count++
            }
        }

        val averageMin = if (count > 0) totalMin / count else 0
        val averageMax = if (count > 0) totalMax / count else 0

        val detailsView = findViewById<TextView>(R.id.detailsView)
        val averageView = findViewById<TextView>(R.id.averageView)

        detailsView.text = details.toString()
        averageView.text = "Average Min Temp: $averageMin°, Average Max Temp: $averageMax°"

        }
}