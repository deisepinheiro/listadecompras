package com.example.appdeise_20222.view;

import android.app.Activity;
import android.os.Bundle;

import com.example.appdeise_20222.adapter.ModeloAdapter;
import com.example.appdeise_20222.presenter.MainPresenter;
import com.example.appdeise_20222.presenter.MainPresenterContract;
import com.example.appdeise_20222.presenter.ModeloPresenter;
import com.example.appdeise_20222.presenter.ModeloPresenterContract;
import com.example.appdeise_20222.room.AppDatabase;
import com.example.appdeise_20222.model.ListaComItens;

import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.appdeise_20222.databinding.ActivityModelosBinding;

import java.util.ArrayList;
import java.util.List;

public class ModelosActivity extends AppCompatActivity implements CallbackFinish, ModeloPresenterContract.view {

    private ActivityModelosBinding binding;
    private ModeloPresenterContract.presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityModelosBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        presenter = new ModeloPresenter(this);
        presenter.setDataInRecicleView();

        setFilterModelos();
    }

    private void setFilterModelos() {
        binding.autoCompleteTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                presenter.getListaComItens(false,s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {  }
        });
    }

    @Override
    public void setRecicleView(List<ListaComItens> modelos) {
        ModeloAdapter adapter = new ModeloAdapter(this, modelos , this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        binding.recycler.setLayoutManager(layoutManager);
        binding.recycler.setAdapter(adapter);
    }


    @Override
    public void desempilharActivity() {
        finish();
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
    }
}