package com.example.weatherapplication.controller;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weatherapplication.R;
import com.example.weatherapplication.model.WeatherCurrentResponse;
import com.example.weatherapplication.model.WeatherService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class WeatherCurrentFragment extends Fragment {
    private LinearLayout info_cont;
    private TextView data_tv, location_tv, temperature_tv, wind_tv, humidity_tv;
    private EditText input_et;
    private ImageButton check_btn;
    private WeatherService weatherService;
    private Toast toast;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
    }

    private void init(View view) {
        info_cont = view.findViewById(R.id.info_cont);
        data_tv = view.findViewById(R.id.data_tv);
        location_tv = view.findViewById(R.id.location_tv);
        temperature_tv = view.findViewById(R.id.temperature_tv);
        wind_tv = view.findViewById(R.id.wind_tv);
        humidity_tv = view.findViewById(R.id.humidity_tv);
        input_et = view.findViewById(R.id.input_et);
        check_btn = view.findViewById(R.id.check_btn);
        weatherService = new WeatherService();
        check_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //info_cont.setVisibility(View.VISIBLE);
                if (!input_et.getText().toString().isEmpty()) {
                    weatherService.getWeatherCurrent(input_et.getText().toString(), new Callback<WeatherCurrentResponse>() {
                        @Override
                        public void onResponse(Call<WeatherCurrentResponse> call, Response<WeatherCurrentResponse> response) {
                            if (response.isSuccessful()) {
                                WeatherCurrentResponse weather = response.body();
                                location_tv.setText(weather.getLocation().getName() + " " + weather.getLocation().getRegion());
                                data_tv.setText(weather.getLocation().getLocaltime());
                                temperature_tv.setText(weather.getCurrent().getTemp_c() + "\u00B0");
                                wind_tv.setText(weather.getCurrent().getWind_kph() + "км/г");
                                humidity_tv.setText(weather.getCurrent().getHumidity() + "%");
                            } else {
                                if (toast != null) {
                                    toast.cancel();
                                }
                                toast = Toast.makeText(getActivity(), "Не вдалось завантажити дані", Toast.LENGTH_SHORT);
                                toast.show();
                            }
                        }

                        @Override
                        public void onFailure(Call<WeatherCurrentResponse> call, Throwable t) {
                            if (toast != null) {
                                toast.cancel();
                            }
                            toast = Toast.makeText(getActivity(), "Помилка запиту", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    });
                }
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_current, container, false);
    }


}