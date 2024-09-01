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
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weatherapplication.R;
import com.example.weatherapplication.model.WeatherCurrentResponse;
import com.example.weatherapplication.model.WeatherForecastResponse;
import com.example.weatherapplication.model.WeatherService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherForecastFragment extends Fragment {
    private ScrollView scroll_view;
    private LinearLayout scroll_cont;
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
        scroll_view = view.findViewById(R.id.scroll_view);
        scroll_cont = view.findViewById(R.id.scroll_cont);
        input_et = view.findViewById(R.id.input_et);
        check_btn = view.findViewById(R.id.check_btn);
        weatherService = new WeatherService();
        check_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!input_et.getText().toString().isEmpty()) {
                    weatherService.getWeatherForecast(input_et.getText().toString(), 7, new Callback<WeatherForecastResponse>() {
                        @Override
                        public void onResponse(Call<WeatherForecastResponse> call, Response<WeatherForecastResponse> response) {
                            if (response.isSuccessful()) {
                                scroll_cont.removeAllViews();
                                WeatherForecastResponse weather = response.body();
                                for (int i = 0; i < 7; i++) {
                                    create_new_element_in_scroll_view(
                                            weather.getForecast().getForecastday().get(i).getDate(),
                                            weather.getLocation().getName() + " " + weather.getLocation().getRegion(),
                                            weather.getForecast().getForecastday().get(i).getDay().getMaxtemp_c() + "\u00B0",
                                            weather.getForecast().getForecastday().get(i).getDay().getAvgtemp_c() + "\u00B0",
                                            weather.getForecast().getForecastday().get(i).getDay().getMintemp_c() + "\u00B0",
                                            weather.getForecast().getForecastday().get(i).getDay().getMaxwind_kph() + "км/г",
                                            weather.getForecast().getForecastday().get(i).getDay().getAvghumidity() + "%",
                                            weather.getForecast().getForecastday().get(i).getDay().getDaily_chance_of_rain() + "%"
                                    );
                                }

                            } else {
                                if (toast != null) {
                                    toast.cancel();
                                }
                                toast = Toast.makeText(getActivity(), "Не вдалось завантажити дані", Toast.LENGTH_SHORT);
                                toast.show();
                            }
                        }

                        @Override
                        public void onFailure(Call<WeatherForecastResponse> call, Throwable t) {
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

    private void create_new_element_in_scroll_view(String data, String location, String max_temp,
                                                   String avg_temp, String min_temp, String max_wind,
                                                   String avg_humidity, String chance_rain) {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View view = layoutInflater.inflate(R.layout.sample_forecast_weather, null);
        LinearLayout main = view.findViewById(R.id.main);
        TextView data_tv = view.findViewById(R.id.data_tv);
        TextView location_tv = view.findViewById(R.id.location_tv);
        TextView maxtemp_tv = view.findViewById(R.id.maxtemp_tv);
        TextView avgtemp_tv = view.findViewById(R.id.avgtemp_tv);
        TextView mintemp_tv = view.findViewById(R.id.mintemp_tv);
        TextView maxwind_tv = view.findViewById(R.id.maxwind_tv);
        TextView avghumidity_tv = view.findViewById(R.id.avghumidity_tv);
        TextView chance_rain_tv = view.findViewById(R.id.chance_rain_tv);
        data_tv.setText(data);
        location_tv.setText(location);
        maxtemp_tv.setText(max_temp);
        avgtemp_tv.setText(avg_temp);
        mintemp_tv.setText(min_temp);
        maxwind_tv.setText(max_wind);
        avghumidity_tv.setText(avg_humidity);
        chance_rain_tv.setText(chance_rain);
        main.setLayoutParams(new LinearLayout.LayoutParams(scroll_view.getWidth(), scroll_view.getHeight()));
        setMargins(main,0,10,0,10);
        scroll_cont.addView(main);
    }
    private void setMargins (View view, int left, int top, int right, int bottom) {
        if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            p.setMargins(left, top, right, bottom);
            view.requestLayout();
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_forecast, container, false);
    }
}