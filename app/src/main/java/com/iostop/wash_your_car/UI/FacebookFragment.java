package com.iostop.wash_your_car.UI;

import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.iostop.wash_your_car.R;

public class FacebookFragment extends Fragment {
    CallbackManager callbackManager;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_facebook, container, false);
        LoginButton loginButton = (LoginButton) rootView.findViewById(R.id.login_button);
        loginButton.setReadPermissions("email");
        loginButton.setFragment(this);
        // Other app specific specialization

        callbackManager = CallbackManager.Factory.create();
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
            }

            @Override
            public void onCancel() {
                Toast.makeText(getActivity(), "Login attempt canceled!", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(FacebookException exception) {
                Toast.makeText(getActivity(), "Login attempt failed!", Toast.LENGTH_LONG).show();
            }
        });

        return rootView;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
