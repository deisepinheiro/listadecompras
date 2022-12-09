package com.example.appdeise_20222.presenter;

import android.app.Activity;

import com.example.appdeise_20222.model.User;


public class LoginPresenterContract {

        public interface view {
            Activity getActivity();
            void showSnakeBar(String msg);
            void progressBar(int visibility);
        }

        public interface presenter {
            void checkLogin(User user);
            void startBroadcast();
            void startRegisterActivity();
            void startMainScreenActivity();
        }
}

