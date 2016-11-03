package com.iostop.wash_your_car;

import android.app.Fragment;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.iostop.wash_your_car.UI.FacebookFragment;
import com.iostop.wash_your_car.Weather.WeatherService;
import com.iostop.wash_your_car.common.Actions;



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

                    }
                }
            }
        };

        IntentFilter intFilt = new IntentFilter(Actions.LOADED.toString());
        LocalBroadcastManager.getInstance(this).registerReceiver(br, intFilt);
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
