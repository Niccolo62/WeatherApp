package com.example.weatherapplication.model;

public class WeatherCurrentResponse {

    private Current current;
    private Location location;


    public Current getCurrent() {
        return current;
    }

    public void setCurrent(Current current) {
        this.current = current;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public class Current {

        private float temp_c;
        private float wind_kph;
        private int humidity;


        public float getWind_kph() {
            return wind_kph;
        }

        public void setWind_kph(float wind_kph) {
            this.wind_kph = wind_kph;
        }

        public int getHumidity() {
            return humidity;
        }

        public void setHumidity(int humidity) {
            this.humidity = humidity;
        }

        public float getTemp_c() {
            return temp_c;
        }

        public void setTemp_c(float temp_c) {
            this.temp_c = temp_c;
        }
    }

    public class Location {
        private String name;
        private String region;
        private String localtime;

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

        public String getLocaltime() {
            return localtime;
        }

        public void setLocaltime(String localtime) {
            this.localtime = localtime;
        }
    }
}
