package com.example.openmeteoapigr2

data class ForecastItem(
    val temperature: String,
    val date: String,
    val time: String,
    val weatherCode: Int? = null
)