package com.example.travelbook

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travelbook.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var cityList: ArrayList<City>
    private lateinit var cityAdapter: CityAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        cityList = ArrayList()
        cityAdapter = CityAdapter(cityList)

        getData()
    }

    private fun getData() {
        try {
            val database: SQLiteDatabase = openOrCreateDatabase("Cities", MODE_PRIVATE, null)
            val cursor: Cursor = database.rawQuery("SELECT * FROM cities", null)
            val nameIx = cursor.getColumnIndex("cityname")
            val countryIx = cursor.getColumnIndex("countryname")
            val detailsIx = cursor.getColumnIndex("traveldetails")
            val idIx = cursor.getColumnIndex("id")

            while (cursor.moveToNext()) {
                val name = cursor.getString(nameIx)
                val country = cursor.getString(countryIx)
                val details = cursor.getString(detailsIx)
                val id = cursor.getInt(idIx)
                val city = City(name, country, details, id)
                cityList.add(city)
            }
            cityAdapter.notifyDataSetChanged()
            cursor.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater: MenuInflater = menuInflater
        menuInflater.inflate(R.menu.add_city, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.add_city_item) {
            val intent = Intent(this@MainActivity, DetailsActivity::class.java)
            intent.putExtra("info", "new")
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }
}
