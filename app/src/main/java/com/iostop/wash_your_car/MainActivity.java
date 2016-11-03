package com.iostop.wash_your_car;

import android.app.Fragment;

import android.app.FragmentTransaction;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.iostop.wash_your_car.UI.FacebookFragment;
import com.iostop.wash_your_car.Weather.WeatherService;
import com.iostop.wash_your_car.common.Actions;


public class MainActivity extends AppCompatActivity implements FacebookFragment.FacebookListener {

    private FacebookFragment facebookFragment;

    private BroadcastReceiver br;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        setContentView(R.layout.activity_main);
//        if (facebookFragment == null) {
//            setLoginFragment();
//        }
    }

    void setLoginFragment() {
        this.facebookFragment = new FacebookFragment();
        facebookFragment.setFacebookListener(this);

        FragmentTransaction ft = getFragmentManager().beginTransaction();

        ft.add(new Fragment(), "");
        Fragment fragment = this.facebookFragment;
        ft.add((Fragment) this.facebookFragment, "FacebookLogin");
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
    public void onSuccess() {

    }

}
