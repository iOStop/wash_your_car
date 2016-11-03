package com.iostop.wash_your_car.Weather;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.iostop.wash_your_car.common.Actions;
import com.iostop.wash_your_car.model.Weather;

import java.io.IOException;

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

    void handleIntent(Intent intent) {
        try {
            Weather weather = WeatherRest.getInstance().loadWeather();
            if (weather != null) {
                LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent((Actions.LOADED.toString())));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
