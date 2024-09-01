package com.example.weatherapplication.model;

import android.content.Context;
import android.content.SharedPreferences;

public class WeatherPreferences {
    private static final String PREFS_NAME = "weather_prefs";
    private static final String KEY_CURRENT_LOCATION = "current_location";
    private static final String KEY_IS_WEATHER_MESSAGE = "is_weather_message";

    private SharedPreferences sharedPreferences;

    public WeatherPreferences(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public void saveCurrentLocation(String location) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_CURRENT_LOCATION, location);
        editor.apply();  // Используй apply() для асинхронного сохранения
    }

    public String getCurrentLocation() {
        return sharedPreferences.getString(KEY_CURRENT_LOCATION, "");
    }

    public void saveIsWeatherMessage(boolean isWeatherMessage) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(KEY_IS_WEATHER_MESSAGE, isWeatherMessage);
        editor.apply();
    }

    public boolean isWeatherMessage() {
        return sharedPreferences.getBoolean(KEY_IS_WEATHER_MESSAGE, true);
    }
}
