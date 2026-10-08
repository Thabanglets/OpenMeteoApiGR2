package com.example.openmeteoapigr2.models

import com.google.gson.annotations.SerializedName

data class Hourly(
    val temperature_2m: List<Double>,
    val time: List<String>,
    @SerializedName("weather_code", alternate = ["weathercode"])
    val weather_code: List<Int>? = null
)