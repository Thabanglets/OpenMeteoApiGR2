package com.example.openmeteoapigr2

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: ForecastAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewForecast)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ForecastAdapter()
        recyclerView.adapter = adapter

        lifecycleScope.launch {
            try {
                val response = apiObject.retrofit.getWeather(-25.7444, 28.186)
                val hourly = response.hourly
                val unit = response.hourly_units.temperature_2m

                val forecastItems = hourly.time.indices.map { index ->
                    val rawTimeString = hourly.time[index]
                    val temp = hourly.temperature_2m.getOrNull(index)
                    val weatherCode = hourly.weather_code?.getOrNull(index)

                    val parts = rawTimeString.split("T")
                    val dateStr = if (parts.isNotEmpty()) parts[0] else rawTimeString
                    val timeStr = if (parts.size > 1) parts[1] else ""

                    val tempStr = if (temp != null) "$temp $unit" else "--"

                    ForecastItem(
                        temperature = tempStr,
                        date = dateStr,
                        time = timeStr,
                        weatherCode = weatherCode
                    )
                }

                adapter.updateItems(forecastItems)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}