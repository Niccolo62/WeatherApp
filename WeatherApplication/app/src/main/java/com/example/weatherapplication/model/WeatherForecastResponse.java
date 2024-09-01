package com.example.weatherapplication.model;

import java.util.List;

public class WeatherForecastResponse {

    private Forecast forecast;
    private Location location;

    public Forecast getForecast() {
        return forecast;
    }

    public void setForecast(Forecast forecast) {
        this.forecast = forecast;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public class Location {
        private String name;
        private String region;


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }
    }

    public class Forecast {
        private List<ForecastDay> forecastday;

        public List<ForecastDay> getForecastday() {
            return forecastday;
        }

        public void setForecastday(List<ForecastDay> forecastday) {
            this.forecastday = forecastday;
        }
    }

    public class ForecastDay {
        private Day day;
        private String date;

        public Day getDay() {
            return day;
        }

        public void setDay(Day day) {
            this.day = day;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }
    }

    public class Day {
        private float maxtemp_c;
        private float mintemp_c;
        private float avgtemp_c;
        private float maxwind_kph;
        private int avghumidity;
        private int daily_chance_of_rain;

        public float getMaxtemp_c() {
            return maxtemp_c;
        }

        public void setMaxtemp_c(float maxtemp_c) {
            this.maxtemp_c = maxtemp_c;
        }

        public float getMintemp_c() {
            return mintemp_c;
        }

        public void setMintemp_c(float mintemp_c) {
            this.mintemp_c = mintemp_c;
        }

        public float getAvgtemp_c() {
            return avgtemp_c;
        }

        public void setAvgtemp_c(float avgtemp_c) {
            this.avgtemp_c = avgtemp_c;
        }

        public float getMaxwind_kph() {
            return maxwind_kph;
        }

        public void setMaxwind_kph(float maxwind_kph) {
            this.maxwind_kph = maxwind_kph;
        }

        public int getAvghumidity() {
            return avghumidity;
        }

        public void setAvghumidity(int avghumidity) {
            this.avghumidity = avghumidity;
        }

        public int getDaily_chance_of_rain() {
            return daily_chance_of_rain;
        }

        public void setDaily_chance_of_rain(int daily_chance_of_rain) {
            this.daily_chance_of_rain = daily_chance_of_rain;
        }
    }
}
