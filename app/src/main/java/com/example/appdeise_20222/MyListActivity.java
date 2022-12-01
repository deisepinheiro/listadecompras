package com.example.appdeise_20222;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MyListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        getSupportActionBar().hide();
    }
}