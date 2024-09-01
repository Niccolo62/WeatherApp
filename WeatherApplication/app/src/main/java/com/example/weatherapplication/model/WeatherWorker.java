package com.example.weatherapplication.model;

import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.weatherapplication.R;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherWorker extends Worker {
    public Context context;


    public WeatherWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        this.context = context;
    }

    @NonNull
    @Override
    public Result doWork() {
        Log.d("TAG", "doWorkWeather: " + true);
        WeatherPreferences weatherPreferences = new WeatherPreferences(context);
        if (!weatherPreferences.isWeatherMessage() && weatherPreferences.getCurrentLocation().isEmpty()) {
            return Result.success();
        }

        final CountDownLatch latch = new CountDownLatch(1);
        final String[] message = {""};

        getWeatherInfo(new Runnable() {
            @Override
            public void run() {
                if (!message[0].isEmpty()) {
                    sendNotification(message[0]);
                }
                latch.countDown();
            }
        }, message);

        try {
            latch.await(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return Result.failure();
        }

        return Result.success();


    }

    private void getWeatherInfo(final Runnable callback, final String[] message) {
        WeatherPreferences weatherPreferences = new WeatherPreferences(context);
        WeatherService weatherService = new WeatherService();
        weatherService.getWeatherCurrent(weatherPreferences.getCurrentLocation(), new Callback<WeatherCurrentResponse>() {
            @Override
            public void onResponse(Call<WeatherCurrentResponse> call, Response<WeatherCurrentResponse> response) {
                if (response.isSuccessful()) {
                    WeatherCurrentResponse weather = response.body();
                    message[0] = "У " + weather.getLocation().getName()+ " " + weather.getCurrent().getTemp_c() + "C\u00B0";
                }
                callback.run();
            }

            @Override
            public void onFailure(Call<WeatherCurrentResponse> call, Throwable t) {
                callback.run();
            }
        });
    }

    private void sendNotification(String weatherInfo) {
        Log.d("TAG", "sendNotification: " + weatherInfo);
        NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        String channelId = "weather_channel";
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), channelId)
                .setSmallIcon(R.drawable.temperature)
                .setContentTitle("Weather Update")
                .setContentText(weatherInfo)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        if (notificationManager != null) {
            notificationManager.notify(1, builder.build());
        }
    }


}

