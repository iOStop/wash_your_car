package com.iostop.wash_your_car;

import android.app.Fragment;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(getApplication());
        setContentView(R.layout.activity_main);

        setLoginFragment();

    }

    private void setLoginFragment() {
        FacebookFragment facebookFragment = new FacebookFragment();

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.add(R.id.settings_fragment_container, facebookFragment,
                getResources().getString(R.string.facebook_tag));
        ft.commit();

    }
    private void setSettingsFragment() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Fragment fragment = getFragmentManager()
                .findFragmentByTag(getResources().getString(R.string.facebook_tag));
        fragment.onActivityResult(requestCode, resultCode, data);
    }


}
