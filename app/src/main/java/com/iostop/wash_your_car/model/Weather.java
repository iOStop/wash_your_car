package com.iostop.wash_your_car.model;

import java.util.ArrayList;

/**
 * Created by Leman on 02.11.16.
 */

public class Weather {
    private String date;

    private String maxtempC;
    private String maxtempF;
    private String mintempC;
    private String mintempF;

    private ArrayList<WeatherHourly> hourly;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMaxtempC() {
        return maxtempC;
    }

    public void setMaxtempC(String maxtempC) {
        this.maxtempC = maxtempC;
    }

    public String getMaxtempF() {
        return maxtempF;
    }

    public void setMaxtempF(String maxtempF) {
        this.maxtempF = maxtempF;
    }

    public String getMintempC() {
        return mintempC;
    }

    public void setMintempC(String mintempC) {
        this.mintempC = mintempC;
    }

    public String getMintempF() {
        return mintempF;
    }

    public void setMintempF(String mintempF) {
        this.mintempF = mintempF;
    }

    public ArrayList<WeatherHourly> getHourly() {
        return hourly;
    }

    public void setHourly(ArrayList<WeatherHourly> hourly) {
        this.hourly = hourly;
    }
}
