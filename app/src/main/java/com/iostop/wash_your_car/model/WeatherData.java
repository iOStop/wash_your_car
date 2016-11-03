package com.iostop.wash_your_car.model;

import java.util.ArrayList;

/**
 * Created by Leman on 02.11.16.
 */

public class WeatherData {
    transient private CurrentCondition current_condition;
    private ArrayList<Weather> weather;

    public CurrentCondition getCurrent_condition() {
        return current_condition;
    }

    public void setCurrent_condition(CurrentCondition current_condition) {
        this.current_condition = current_condition;
    }

    public ArrayList<Weather> getWeather() {
        return weather;
    }

    public void setWeather(ArrayList<Weather> weather) {
        this.weather = weather;
    }
}
