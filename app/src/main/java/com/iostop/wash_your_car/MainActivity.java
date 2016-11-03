package com.iostop.wash_your_car;

import android.app.Fragment;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.iostop.wash_your_car.UI.FacebookFragment;
import com.iostop.wash_your_car.Weather.WeatherService;
import com.iostop.wash_your_car.common.Actions;

import org.w3c.dom.Text;


public class MainActivity extends AppCompatActivity {

    private BroadcastReceiver br;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(getApplication());
        setContentView(R.layout.activity_main);

        if(!isLoggedIn()){
            setLoginFragment();
        }

        Button settingsBtn = (Button)findViewById(R.id.settings_btn);
        settingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SettingsActivity.class));
            }
        });

    }

    private void setLoginFragment() {
        FacebookFragment facebookFragment = new FacebookFragment();

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.add(R.id.fragment_container, facebookFragment,
                getResources().getString(R.string.facebook_tag));
        ft.commit();

    }


    @Override
    protected void onStart() {
        super.onStart();
        WeatherService.start(this);

        br = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                if (intent != null) {
                    if (intent.getAction().equals(Actions.LOADED.toString())) {
                        String analyzeResult = getApplicationContext().getSharedPreferences("Weather", 0)
                                .getString("AnalyzeResult", ":)");
                        String currentDescription = getApplicationContext().getSharedPreferences("Weather", 0)
                                .getString("CurrentWeather", ":)");
                        System.out.println(analyzeResult);

                        TextView forecastTxtView = (TextView) findViewById(R.id.weather_forecast);
                        forecastTxtView.setText(currentDescription);
                        TextView washTip = (TextView)findViewById(R.id.wash_tip);
                        washTip.setText(analyzeResult);
                    }
                }
            }
        };

        IntentFilter intFilt = new IntentFilter(Actions.LOADED.toString());
        LocalBroadcastManager.getInstance(this).registerReceiver(br, intFilt);

//        SharedPreferences sharedPrefs =
//                getSharedPreferences("Weather", Context.MODE_PRIVATE);
//        String tip = sharedPrefs
//                .getString("AnalyzeResult", "Loading...");
//        String current_weather =
//                sharedPrefs.getString("CurrentWeather", "Loading...");

    }

    @Override
    protected void onStop() {
        super.onStop();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(br);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Fragment fragment = getFragmentManager()
                .findFragmentByTag(getResources().getString(R.string.facebook_tag));
        fragment.onActivityResult(requestCode, resultCode, data);
    }

    public boolean isLoggedIn(){
        return AccessToken.getCurrentAccessToken() != null;
    }

}
