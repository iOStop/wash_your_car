package com.iostop.wash_your_car.model;

/**
 * Created by Leman on 02.11.16.
 */

public class WeatherData {
    private CurrentCondition current_condition;
    private Weather weather;

    public CurrentCondition getCurrent_condition() {
        return current_condition;
    }

    public void setCurrent_condition(CurrentCondition current_condition) {
        this.current_condition = current_condition;
    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }
}
