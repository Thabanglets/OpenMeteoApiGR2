package com.example.openmeteoapigr2

import com.example.openmeteoapigr2.models.MeteoResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MeteoService {

    @GET("/v1/forecast")
    suspend fun getWeather(
        @Query ("latitude") lat : Double,
        @Query ("longitude") lon : Double,
        @Query ("hourly") hourly : String = "temperature_2m,weather_code",
        @Query ("timezone") tz : String = "auto")
    : MeteoResponse

}