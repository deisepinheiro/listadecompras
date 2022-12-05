package com.example.appdeise_20222;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.appdeise_20222.dados.AppDatabase;
import com.example.appdeise_20222.dados.ItemLista;
import com.example.appdeise_20222.dados.ItenListaDao;

import java.util.ArrayList;
import java.util.List;

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
        itens = new ArrayList<>();



        String categorias[] = {"TODAS", "Padaria","Carnes", "Congelados", "Mercearia","Matinais","Frios e Laticínios", "Enlatados", "Limpeza", "Higiene",
                "Bebidas", "Hortifruti", "Utilidades Domésticas","Pet Shop"};


        //Inicia o Banco de dados
        AppDatabase db =
                Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "lista_compras")
                        .allowMainThreadQueries()
                        .build();

        itens = (ArrayList<ItemLista>) db.itemListaDao().getAll();
        compartilhaLista(getListaInString(itens));



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
                ItemLista item = new ItemLista(produto, categoria);
                itens.add(item);
                autoCompleteTextView.setText("");

                ItenListaDao itemListaDao = db.itemListaDao();
                itemListaDao.insertItem(item);

                adapter.notifyDataSetChanged();


            }
        });


    }

    private String getListaInString(ArrayList<ItemLista> itens){

        StringBuilder texto = new StringBuilder();
        texto.append("Minha Lista: \n");
        for(ItemLista item : itens){
            texto.append(item.toString() + "\n");
        }

        return texto.toString();
    }

    private void compartilhaLista(String texto){

        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, texto);
        sendIntent.setType("text/plain");

        if (sendIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(sendIntent);
        }
    }


}