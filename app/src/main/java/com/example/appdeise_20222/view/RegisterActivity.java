package com.example.appdeise_20222.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.appdeise_20222.R;
import com.example.appdeise_20222.databinding.ActivityRegisterBinding;
import com.example.appdeise_20222.model.User;
import com.example.appdeise_20222.presenter.LoginPresenter;
import com.example.appdeise_20222.presenter.LoginPresenterContract;
import com.example.appdeise_20222.presenter.RegisterPresenter;
import com.example.appdeise_20222.presenter.RegisterPresenterContract;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity implements RegisterPresenterContract.view{

    ActivityRegisterBinding binding;
    private RegisterPresenterContract.presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        setContentView(binding.getRoot());

        this.presenter = new RegisterPresenter(this);

        getSupportActionBar().hide();

        binding.btRegister.setOnClickListener(view -> {

            presenter.tryLogin(
                    new User(
                    binding.editName.getText().toString(),
                    binding.editEmail.getText().toString(),
                    binding.editPass.getText().toString())
            );

        });

    }

    @Override
    public void showSnakeBar(String msg) {
        Snackbar snackbar = Snackbar.make(binding.btRegister ,msg, Snackbar.LENGTH_SHORT);
        snackbar.setBackgroundTint(Color.WHITE);
        snackbar.setTextColor(Color.BLACK);
        snackbar.show();
    }



    @Override
    public Activity getActivity() {
        return this;
    }
}