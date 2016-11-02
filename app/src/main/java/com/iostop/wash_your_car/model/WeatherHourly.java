package com.iostop.wash_your_car.model;

/**
 * Created by Leman on 02.11.16.
 */

public class WeatherHourly {
    private String time;

    private String temp_C;
    private String temp_F;

    private WeatherIcon weatherIconUrl;
    private WeatherDesc weatherDesc;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTemp_C() {
        return temp_C;
    }

    public void setTemp_C(String temp_C) {
        this.temp_C = temp_C;
    }

    public String getTemp_F() {
        return temp_F;
    }

    public void setTemp_F(String temp_F) {
        this.temp_F = temp_F;
    }

    public WeatherIcon getWeatherIconUrl() {
        return weatherIconUrl;
    }

    public void setWeatherIconUrl(WeatherIcon weatherIconUrl) {
        this.weatherIconUrl = weatherIconUrl;
    }

    public WeatherDesc getWeatherDesc() {
        return weatherDesc;
    }

    public void setWeatherDesc(WeatherDesc weatherDesc) {
        this.weatherDesc = weatherDesc;
    }
}