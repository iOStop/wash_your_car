package com.iostop.wash_your_car;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;


public class MainActivity extends AppCompatActivity implements FacebookFragment.FacebookListener {

    private FacebookFragment facebookFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        setContentView(R.layout.activity_login);
        if (facebookFragment == null) {
            setLoginFragment();
        }
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
    public void onSuccess() {

    }


}
