package com.example.appdeise_20222.presenter;

import android.app.Activity;

public class MainPresenterContract {

    public interface view {
        Activity getActivity();
        void setViews(String email, String login);
    }

    public interface presenter {
        void registerReceiver();
        void startListaActivity();
        void startModelosActivity();
        void logoff();
        void getUserInfo();
    }
}
