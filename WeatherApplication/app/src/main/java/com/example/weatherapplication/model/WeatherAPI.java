package com.example.weatherapplication.model;

import static com.example.weatherapplication.model.ApiConstants.CURRENT_WEATHER_ENDPOINT;
import static com.example.weatherapplication.model.ApiConstants.FORECAST_ENDPOINT;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherAPI {
    @GET(CURRENT_WEATHER_ENDPOINT)
    Call<WeatherCurrentResponse> getCurrentWeather(
            @Query("key") String apiKey,
            @Query("q") String cityName
    );

    @GET(FORECAST_ENDPOINT)
    Call<WeatherForecastResponse> getForecastWeather(
            @Query("key") String apiKey,
            @Query("q") String cityName,
            @Query("days") int dayCount
    );
}
