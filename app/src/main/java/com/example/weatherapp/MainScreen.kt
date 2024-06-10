package com.example.weatherapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainScreen : AppCompatActivity() {

    private lateinit var dailyTemperatures: Array<Double?>
    private lateinit var editTexts: Array<EditText>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)

        val editTextMonday = findViewById<EditText>(R.id.editTextMonday)
        val editTextTuesday = findViewById<EditText>(R.id.editTextTuesday)
        val editTextWednesday = findViewById<EditText>(R.id.editTextWednesday)
        val editTextThursday = findViewById<EditText>(R.id.editTextThursday)
        val editTextFriday = findViewById<EditText>(R.id.editTextFriday)
        val editTextSaturday = findViewById<EditText>(R.id.editTextSaturday)
        val editTextsSunday = findViewById<EditText>(R.id.editTextSunday)
        val clearBtn = findViewById<Button>(R.id.clearBtn)
        val viewWeatherBtn = findViewById<Button>(R.id.viewWeatherBtn)

        // editTexts for entering the temperatures
        editTexts = arrayOf(
            editTextMonday,
            editTextTuesday,
            editTextWednesday,
            editTextThursday,
            editTextFriday,
            editTextSaturday,
            editTextsSunday
        )

        dailyTemperatures = arrayOfNulls(editTexts.size)

        // code for linking the two activities
        viewWeatherBtn.setOnClickListener{
            intent = Intent(this,WeatherDetails::class.java)
            startActivity(intent)
        }

        clearBtn.setOnClickListener {
            clearData()

            viewWeatherBtn.setOnClickListener {
                if (saveData()) {
                    val intent = Intent(this, WeatherDetails::class.java)
                    intent.putExtra("dailyTemperatures", dailyTemperatures)
                    startActivity(intent)
                }
            }
        }

    }


    private fun clearData() {
        for (editText in editTexts) {
            editText.text.clear()
        }
        dailyTemperatures.fill(null)
    }


    private fun saveData(): Boolean {
        for (i in editTexts.indices) {
            val text = editTexts[i].text.toString()
            if (text.isNotBlank()) {
                try {
                    dailyTemperatures[i] = text.toDouble()
                } catch (e: NumberFormatException) {
                    Toast.makeText(this, "Please enter valid numbers.", Toast.LENGTH_SHORT).show()
                    return false
                }
            } else {
                dailyTemperatures[i] = 0.0
            }
        }
        return true


    }
}