package com.iostop.wash_your_car.Weather;

import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import com.iostop.wash_your_car.R;
import com.iostop.wash_your_car.model.*;
/**
 * Created by Leman on 02.11.16.
 */

public class WeatherRest {


    private static final WeatherRest INSTANCE = new WeatherRest();
    private final OkHttpClient HTTP_CLIENT = new OkHttpClient();
    private final Gson gson = new Gson();

    public static WeatherRest getInstance() {
        return INSTANCE;
    }

    public Weather loadWeather() throws IOException {
        Request request = (new Request.Builder()).url("\n" +
                "http://api.worldweatheronline.com/premium/v1/weather.ashx?key=" + (R.string.weather_api_key)
                +"&q=Moscow&format=json&num_of_days=5").build();
        Response response = this.HTTP_CLIENT.newCall(request).execute();

        Weather weather;
        try {
            if(!response.isSuccessful()) {
                throw new IOException("Wrong status: " + response.code() + "; body: " + response.body().string());
            }

            WeatherResponse weatherResponse = (WeatherResponse)this.gson.fromJson(response.body().string(), WeatherResponse.class);
            weather = weatherResponse.getData().getWeather();
            Log.d("WeatherRest", "load weather: " + weather);
        } finally {
            response.body().close();
        }

        return weather;
    }
}
