package com.example.appdeise_20222.presenter;

import android.app.Activity;

import com.example.appdeise_20222.model.User;


public class RegisterPresenterContract {

    public interface view {
        Activity getActivity();
        void showSnakeBar(String msg);
    }

    public interface presenter {
        void tryLogin(User user);
    }
}
