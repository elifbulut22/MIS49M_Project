package com.example.travelbook

import android.content.ContentValues
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.travelbook.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding
    private lateinit var database: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = openOrCreateDatabase("Cities", MODE_PRIVATE, null)
        database.execSQL("CREATE TABLE IF NOT EXISTS cities (id INTEGER PRIMARY KEY, cityname VARCHAR, countryname VARCHAR, traveldetails VARCHAR)")

        val info = intent.getStringExtra("info")

        if (info != null && info == "new") {
            binding.button.visibility = View.VISIBLE
        } else {
            binding.button.visibility = View.INVISIBLE
        }
    }

    fun save(view: View) {
        val cityName = binding.cityNameText.text.toString()
        val countryName = binding.countryNameText.text.toString()
        val travelDetails = binding.travelDetailsText.text.toString()

        try {
            val values = ContentValues()
            values.put("cityname", cityName)
            values.put("countryname", countryName)
            values.put("traveldetails", travelDetails)
            database.insert("cities", null, values)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        val intent = Intent(this@DetailsActivity, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }
}
