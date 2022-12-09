package com.example.appdeise_20222.presenter;

import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Handler;
import android.view.View;

import androidx.annotation.NonNull;

import com.example.appdeise_20222.broadcast.MyBroadcastReceiver;
import com.example.appdeise_20222.model.User;
import com.example.appdeise_20222.view.MainScreenActivity;
import com.example.appdeise_20222.view.RegisterActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginPresenter implements LoginPresenterContract.presenter{

    private LoginPresenterContract.view activity;

    public LoginPresenter(LoginPresenterContract.view activity) {
        this.activity = activity;
    }

    @Override
    public void checkLogin(User user) {

        if(user.getLogin().isBlank() || user.getSenha().isBlank()){
            activity.showSnakeBar("Preencha todos os campos");
        }else{
            autenticarUsuario(user);
        }
    }

    private void autenticarUsuario(User user) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(user.getLogin(), user.getSenha()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){

                    activity.progressBar(View.VISIBLE);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            startMainScreenActivity();
                        }
                    }, 3000);
                }else{

                    try {
                        throw task.getException();
                    }catch (Exception e){
                      activity.showSnakeBar("Erro ao Logar!");
                    }
                }
            }
        });
    }

    @Override
    public void startBroadcast() {
        MyBroadcastReceiver br = new MyBroadcastReceiver();
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        filter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        activity.getActivity().registerReceiver(br, filter);
    }

    @Override
    public void startRegisterActivity() {
        Intent intent = new Intent(activity.getActivity(), RegisterActivity.class);
        activity.getActivity().startActivity(intent);
    }
    @Override
    public void startMainScreenActivity() {
        Intent intent = new Intent(activity.getActivity(), MainScreenActivity.class);
        activity.getActivity().startActivity(intent);
    }
}
