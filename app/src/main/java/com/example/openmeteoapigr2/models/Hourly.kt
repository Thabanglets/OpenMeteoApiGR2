package com.example.openmeteoapigr2.models

data class Hourly(
    val temperature_2m: List<Double>,
    val time: List<String>
)