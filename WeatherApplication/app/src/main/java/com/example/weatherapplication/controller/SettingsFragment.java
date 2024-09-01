package com.example.weatherapplication.controller;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.Toast;

import com.example.weatherapplication.R;
import com.example.weatherapplication.model.WeatherCurrentResponse;
import com.example.weatherapplication.model.WeatherPreferences;
import com.example.weatherapplication.model.WeatherService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SettingsFragment extends Fragment {
    private EditText input_et;
    private Switch switch_button;
    private ImageButton save_btn;
    private WeatherPreferences weatherPreferences;
    private Toast toast;
    private WeatherService weatherService;
    private boolean isEnabled = false;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);

    }

    private void init(View view) {
        input_et = view.findViewById(R.id.input_et);
        switch_button = view.findViewById(R.id.switch_button);
        save_btn = view.findViewById(R.id.save_btn);
        weatherPreferences = new WeatherPreferences(getContext());
        input_et.setText(weatherPreferences.getCurrentLocation());
        isEnabled = weatherPreferences.isWeatherMessage();
        switch_button.setChecked(isEnabled);

        weatherService = new WeatherService();
        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!input_et.getText().toString().isEmpty()) {
                    if (!input_et.getText().toString().isEmpty()) {
                        weatherService.getWeatherCurrent(input_et.getText().toString(), new Callback<WeatherCurrentResponse>() {
                            @Override
                            public void onResponse(Call<WeatherCurrentResponse> call, Response<WeatherCurrentResponse> response) {
                                if (response.isSuccessful()) {
                                    weatherPreferences.saveIsWeatherMessage(switch_button.isChecked());
                                    weatherPreferences.saveCurrentLocation(input_et.getText().toString());
                                    if (toast != null) {
                                        toast.cancel();
                                    }
                                    toast = Toast.makeText(getActivity(), "Успішно", Toast.LENGTH_SHORT);
                                    toast.show();
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
                } else {
                    if (toast != null) {
                        toast.cancel();
                    }
                    toast = Toast.makeText(getActivity(), "Помилка", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }
}