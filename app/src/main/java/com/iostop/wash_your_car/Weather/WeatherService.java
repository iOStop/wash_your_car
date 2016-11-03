package com.iostop.wash_your_car.Weather;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;

import com.iostop.wash_your_car.common.Actions;
import com.iostop.wash_your_car.model.Weather;
import com.iostop.wash_your_car.model.WeatherData;
import com.iostop.wash_your_car.model.WeatherHourly;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Leman on 02.11.16.
 */

public class WeatherService extends IntentService {

    public WeatherService() {
        super("WeatherService");
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, WeatherService.class);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            handleIntent(intent);
        }
    }

    private void handleIntent(Intent intent) {
        try {
            WeatherData weatherData = WeatherRest.getInstance().loadWeather();
            if (weatherData != null) {
                currentWeatherDescription(weatherData.getCurrent_condition().get(0));
                analyzeWeather(weatherData);
                LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent((Actions.LOADED.toString())));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void currentWeatherDescription(WeatherHourly weatherHourly) {
        String desctiption = weatherHourly.getWeatherDesc().get(0).getValue();
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("Weather", 0);
        sharedPreferences.edit().putString("CurrentWeather", desctiption).apply();
    }

    void analyzeWeather(WeatherData weatherData) {
        ArrayList<Boolean> rainyHours = new ArrayList<>();
        for (Weather weather : weatherData.getWeather()) {
            for (WeatherHourly hours : weather.getHourly()) {
                String descriptionWeather = hours.getWeatherDesc().get(0).getValue();
                if (checkIfWeatherIsBad(descriptionWeather)) {
                    rainyHours.add(true);
                } else {
                    rainyHours.add(false);
                }
            }
        }

        Boolean badWeatherIsComing = false;
        for (int i = 0; i < 2; ++i) {
            Weather weather = weatherData.getWeather().get(i);
            for (WeatherHourly hours : weather.getHourly()) {
                String descriptionWeather = hours.getWeatherDesc().get(0).getValue();
                if (checkIfWeatherIsBad(descriptionWeather)) {
                    badWeatherIsComing = true;
                    break;
                }
            }
        }

        String result;
        if (rainyHours.size() < 5 && !badWeatherIsComing) {
            result = "Do it!";
        } else {
            result = "Better no";
        }

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("Weather", 0);
        sharedPreferences.edit().putString("AnalyzeResult", result).apply();
    }

    @NonNull
    private Boolean checkIfWeatherIsBad(String descriptionWeather) {
        if (descriptionWeather.contains("rain") || descriptionWeather.contains("snow")) {
            return true;
        }
        return false;
    }

}
