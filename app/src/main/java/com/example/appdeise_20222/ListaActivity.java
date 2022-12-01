package com.example.appdeise_20222;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;

import com.example.appdeise_20222.dados.ItemLista;

import java.util.ArrayList;

public class ListaActivity extends AppCompatActivity {


    private RecyclerView recycler;
    private ListaAdapter adapter;
    private ArrayList<ItemLista> itens;
    private Button bt_addItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        getSupportActionBar().hide();

        recycler = findViewById(R.id.recycler);
        itens = new ArrayList<ItemLista>();


        //itens.add(new ItemLista("Arroz","KG","Grãos"));
        //itens.add(new ItemLista("Feijão","KG","Grãos"));
        //itens.add(new ItemLista("Leite","Litro","Matinais"));
        //itens.add(new ItemLista("Papel Higiênico","pacote","higiene"));

        String categorias[] = {"TODAS", "Padaria","Carnes", "Congelados", "Mercearia","Matinais","Frios e Laticínios", "Enlatados", "Limpeza", "Higiene",
                "Bebidas", "Hortifruti", "Utilidades Domésticas","Pet Shop"};



        // Selection of the spinner
        Spinner spinner = (Spinner) findViewById(R.id.sp_category);

        // Application of the Array to the Spinner
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_item, categorias);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        spinner.setAdapter(spinnerArrayAdapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ListaActivity.this,
                LinearLayoutManager.VERTICAL, false);


        adapter = new ListaAdapter(this,itens);
        recycler.setLayoutManager(layoutManager);
        recycler.setItemAnimator(new DefaultItemAnimator());
        recycler.setAdapter(adapter);

        bt_addItem = findViewById(R.id.bt_addItem);

        bt_addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AutoCompleteTextView autoCompleteTextView = findViewById(R.id.autoCompleteTextView);
                String produto = autoCompleteTextView.getText().toString();
                String categoria = spinner.getSelectedItem().toString();

                itens.add(new ItemLista(produto, categoria));
                autoCompleteTextView.setText("");
                adapter.notifyDataSetChanged();


            }
        });


    }


}