package com.example.appdeise_20222.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.os.Bundle;

import com.example.appdeise_20222.R;
import com.example.appdeise_20222.databinding.ActivityMainScreenBinding;
import com.example.appdeise_20222.presenter.MainPresenter;
import com.example.appdeise_20222.presenter.MainPresenterContract;

public class MainScreenActivity extends AppCompatActivity implements MainPresenterContract.view{

    private ActivityMainScreenBinding binding;
    private MainPresenterContract.presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =  DataBindingUtil.setContentView(this, R.layout.activity_main_screen);
        getSupportActionBar().hide();

        this.presenter = new MainPresenter(this);

        presenter.registerReceiver();

        binding.btNewList.setOnClickListener(v -> presenter.startListaActivity());

        binding.btMyLists.setOnClickListener(v -> presenter.startModelosActivity());

        binding.btLogout.setOnClickListener(v -> presenter.logoff());
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.getUserInfo();

    }
    @Override
    public void setViews(String email, String login) {
        binding.textUserEmail.setText(email);
        binding.textUserName.setText(login);
    }


    @Override
    public Activity getActivity() {
        return this;
    }


}