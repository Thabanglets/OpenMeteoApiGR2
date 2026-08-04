package com.example.openmeteoapigr2

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object apiObject {
    val baseUrl = "https://api.open-meteo.com"
    var retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create<MeteoService>(MeteoService::class.java)



}