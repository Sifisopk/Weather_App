package com.example.weatherapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainScreen : AppCompatActivity() {

    private lateinit var editTextsMin: Array<EditText>
    private lateinit var editTextsMax: Array<EditText>
    private lateinit var editTextsCondition: Array<EditText>
    private var minTemps: Array<Int?> = arrayOfNulls(7)
    private var maxTemps: Array<Int?> = arrayOfNulls(7)
    private var conditions: Array<String?> = arrayOfNulls(7)
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)


        editTextsMin = arrayOf(
            findViewById(R.id.mondayMinTemp)
        )

        editTextsMax = arrayOf(
            findViewById(R.id.mondayMaxTemp)
        )


        editTextsCondition = arrayOf(
          findViewById(R.id.mondaycondition)

        )

        findViewById<Button>(R.id.saveBtn).setOnClickListener {
            if (saveData()) {
                Toast.makeText(this, "Data saved", Toast.LENGTH_SHORT).show()
            }
        }

        findViewById<Button>(R.id.clearBtn).setOnClickListener {
            clearData()
        }

        findViewById<Button>(R.id.viewWeatherBtn).setOnClickListener {
            if (saveData() && isDataComplete()) {
                val intent = Intent(this, WeatherDetails::class.java)
                intent.putExtra("minTemps", minTemps)
                intent.putExtra("maxTemps", maxTemps)
                intent.putExtra("conditions", conditions)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Please fill all fields correctly.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun clearData() {
        editTextsMin.forEach { it.text.clear() }
        editTextsMax.forEach { it.text.clear() }
        editTextsCondition.forEach { it.text.clear() }
        minTemps.fill(null)
        maxTemps.fill(null)
        conditions.fill(null)
    }

    private fun saveData(): Boolean {
        for (i in editTextsMin.indices) {
            val minText = editTextsMin[i].text.toString()
            val maxText = editTextsMax[i].text.toString()
            val conditionText = editTextsCondition[i].text.toString()
            if (minText.isNotBlank() && maxText.isNotBlank() && conditionText.isNotBlank()) {
                try {
                    minTemps[i] = minText.toInt()
                    maxTemps[i] = maxText.toInt()
                    conditions[i] = conditionText
                } catch (e: NumberFormatException) {
                    Toast.makeText(this, "Please enter valid numbers.", Toast.LENGTH_SHORT).show()
                    return false
                }
            } else {
                Toast.makeText(this, "Please fill all fields.", Toast.LENGTH_SHORT).show()
                return false
            }
        }
        return true
    }

    private fun isDataComplete(): Boolean {
        return minTemps.all { it != null } && maxTemps.all { it != null } && conditions.all { it != null }

    }
}