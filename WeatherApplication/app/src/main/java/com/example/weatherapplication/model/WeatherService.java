package com.example.weatherapplication.model;

import static com.example.weatherapplication.model.ApiConstants.BASE_URL_WEATHER;
import static com.example.weatherapplication.model.ApiConstants.WEATHER_API_KEY;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherService {

    private WeatherAPI weatherAPI;

    public WeatherService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL_WEATHER)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        weatherAPI = retrofit.create(WeatherAPI.class);
    }

    public void getWeatherCurrent(String cityName, Callback<WeatherCurrentResponse> callback) {
        Call<WeatherCurrentResponse> call = weatherAPI.getCurrentWeather(WEATHER_API_KEY, cityName);
        call.enqueue(callback);
    }

    public void getWeatherForecast(String cityName, int dayCount, Callback<WeatherForecastResponse> callback) {
        Call<WeatherForecastResponse> call = weatherAPI.getForecastWeather(WEATHER_API_KEY, cityName, dayCount);
        call.enqueue(callback);
    }
}