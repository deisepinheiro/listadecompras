package com.example.appdeise_20222;

import android.os.Bundle;

import com.example.appdeise_20222.dados.AppDatabase;
import com.example.appdeise_20222.dados.ListaComItens;

import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Adapter;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.appdeise_20222.databinding.ActivityModelosBinding;

import java.util.ArrayList;
import java.util.List;

public class ModelosActivity extends AppCompatActivity {

    private ActivityModelosBinding binding;
    private List<ListaComItens> modelos = new ArrayList<>();
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityModelosBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db =   Room.databaseBuilder(getApplicationContext(),
                        AppDatabase.class, "lista_compras")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();



        modelos = db.listaDao().getListaComItensByStatus(false);
        if(modelos.isEmpty()){
            Toast.makeText(this,"Nenhum modelo criado!",Toast.LENGTH_LONG).show();
        }else{
            setRecicleView();
        }



        binding.autoCompleteTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                modelos = db.listaDao().getListaComItensbyStatusAndDescription(false,s.toString());
                setRecicleView();
            }

            @Override
            public void afterTextChanged(Editable s) {       }
        });
    }

    private void setRecicleView() {
        ModeloAdapter adapter = new ModeloAdapter(this, modelos );
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        binding.recycler.setLayoutManager(layoutManager);
        binding.recycler.setAdapter(adapter);
    }


}