package com.example.openmeteoapigr2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ForecastAdapter(private var items: List<ForecastItem> = emptyList()) :
    RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder>() {

    class ForecastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivWeatherIcon: ImageView = itemView.findViewById(R.id.ivWeatherIcon)
        val tvTemperature: TextView = itemView.findViewById(R.id.tvTemperature)
        val tvDate: TextView = itemView.findViewById(R.id.tvDate)
        val tvTime: TextView = itemView.findViewById(R.id.tvTime)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_forecast, parent, false)
        return ForecastViewHolder(view)
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        val item = items[position]
        holder.tvTemperature.text = item.temperature
        holder.tvDate.text = item.date
        holder.tvTime.text = item.time

        val iconRes = getWeatherIconResource(item.weatherCode)
        holder.ivWeatherIcon.setImageResource(iconRes)
    }

    override fun getItemCount(): Int = items.size

    fun updateItems(newItems: List<ForecastItem>) {
        items = newItems
        notifyDataSetChanged()
    }

    private fun getWeatherIconResource(weatherCode: Int?): Int {
        if (weatherCode == null) return R.drawable.ic_weather_default
        return when (weatherCode) {
            0, 1 -> R.drawable.ic_weather_sunny
            2, 3 -> R.drawable.ic_weather_cloudy
            in 51..67, in 80..82 -> R.drawable.ic_weather_rainy
            in 71..77, in 85..86 -> R.drawable.ic_weather_snowy
            else -> R.drawable.ic_weather_default
        }
    }
}