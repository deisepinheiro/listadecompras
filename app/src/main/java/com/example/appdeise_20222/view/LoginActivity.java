package com.example.appdeise_20222.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;


import com.example.appdeise_20222.R;
import com.example.appdeise_20222.databinding.ActivityLoginBinding;
import com.example.appdeise_20222.model.User;
import com.example.appdeise_20222.presenter.LoginPresenter;
import com.example.appdeise_20222.presenter.LoginPresenterContract;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity implements LoginPresenterContract.view {

    ActivityLoginBinding binding;
    private LoginPresenterContract.presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        setContentView(binding.getRoot());

        getSupportActionBar().hide();
        this.presenter = new LoginPresenter(this);

        presenter.startBroadcast();

        binding.textRegisterScreen.setOnClickListener(v -> presenter.startRegisterActivity());

        binding.btLogin.setOnClickListener(
                v -> presenter.checkLogin(new User(binding.editEmail.getText().toString(), binding.editPassword.getText().toString(), binding.editPassword.getText().toString()))
        );

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser usuarioAtual = FirebaseAuth.getInstance().getCurrentUser();
        if(usuarioAtual != null){
            presenter.startMainScreenActivity();
        }
    }

    @Override
    public void progressBar(int visible) {
        binding.progressbar.setVisibility(visible);
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void showSnakeBar(String msg) {
        Snackbar snackbar = Snackbar.make(binding.btLogin ,msg, Snackbar.LENGTH_SHORT);
        snackbar.setBackgroundTint(Color.WHITE);
        snackbar.setTextColor(Color.BLACK);
        snackbar.show();
    }
}