package com.example.appdeise_20222.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.example.appdeise_20222.adapter.ListaAdapter;
import com.example.appdeise_20222.R;
import com.example.appdeise_20222.databinding.ActivityListaBinding;
import com.example.appdeise_20222.presenter.ListaPresenter;
import com.example.appdeise_20222.presenter.ListaPresenterContract;
import com.example.appdeise_20222.model.ItemLista;
import com.example.appdeise_20222.model.ListaComItens;
import com.example.appdeise_20222.model.Produto;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ListaActivity extends AppCompatActivity implements ReturnTotal, ListaPresenterContract.view {

    private ListaAdapter adapter;
    private ArrayList<Produto> produtos = new ArrayList<>();
    private ListaPresenterContract.presenter presenter;
    private long IdMinhaLista;
    ActivityListaBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =  DataBindingUtil.setContentView(this, R.layout.activity_lista);
        getSupportActionBar().hide();

        presenter = new ListaPresenter(this);

        presenter.cadastroPrevioDeProdutos();
        presenter.setDadosListaCategoria();
        presenter.setDadpsAutoCompleteProduto();

        Long id_modelo = getIntent().getLongExtra("id_modelo",0l);
        if (id_modelo>0l){
            IdMinhaLista = presenter.getListaDeComprasbyId(id_modelo);
        }else{
            IdMinhaLista = presenter.getListaDeComprasAtual();
        }

        binding.autoCompleteTextView.setOnItemClickListener((parent, view, position, id) -> {
            String categoria = binding.spCategory.getSelectedItem().toString();
            String produto = parent.getItemAtPosition(position).toString();
            ItemLista item = new ItemLista(produto, categoria,IdMinhaLista);
            presenter.addItemNaLista(item);
        });

        binding.btAddItem.setOnClickListener(v -> {
            AutoCompleteTextView autoCompleteTextView = findViewById(R.id.autoCompleteTextView);
            String produto = autoCompleteTextView.getText().toString();
            String categoria = binding.spCategory.getSelectedItem().toString();
            ItemLista item = new ItemLista(produto, categoria,IdMinhaLista);
            presenter.addItemNaLista(item);
            presenter.setDadpsAutoCompleteProduto();
        });

        binding.btShareLista.setOnClickListener(v -> presenter.compartilhaLista());

        binding.btSave.setOnClickListener(v -> {
            presenter.saveModeloDeLista(binding.tvNamelist.getText().toString());
            binding.tvNamelist.setText("");
        });

    }

    @Override
    public void setMyRecicleView(ListaComItens minhaLista){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ListaActivity.this,
                LinearLayoutManager.VERTICAL, false);
        adapter = new ListaAdapter(this, minhaLista, this);
        binding.recycler.setLayoutManager(layoutManager);
        binding.recycler.setItemAnimator(new DefaultItemAnimator());
        binding.recycler.setAdapter(adapter);
        binding.tvNamelist.setText(minhaLista.lista.getDescricao());
    }

    @Override
    public void setSpinnerCategoria(String[] categorias) {
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_item, categorias);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spCategory.setAdapter(spinnerArrayAdapter);
    }

    @Override
    public void setAutoCompleteProdutos(List<Produto> produtos) {
        ArrayAdapter<Produto> auc_adapter = new ArrayAdapter<Produto>(this,
                android.R.layout.simple_dropdown_item_1line, produtos);
        binding.autoCompleteTextView.setAdapter(auc_adapter);
    }

    @Override
    public void setTextAutoComplete(String text) {
        binding.autoCompleteTextView.setText(text);
    }

    @Override
    public void setTotalListaAndCarrinho(Double totalLista,  Double totalCarrinho) {
        binding.totalLista.setText(formataValor(totalLista));
        binding.totalCarrinho.setText(formataValor(totalCarrinho));
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
    }

    private String formataValor(Double valor){
        return String.format(Locale.GERMAN, "%,.2f", valor);
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void atualiza() {
        presenter.calculaTotaisDaLista();
    }
}