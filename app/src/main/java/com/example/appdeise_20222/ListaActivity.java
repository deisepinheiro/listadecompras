package com.example.appdeise_20222;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.appdeise_20222.dados.AppDatabase;
import com.example.appdeise_20222.dados.ItemLista;
import com.example.appdeise_20222.dados.ItenListaDao;
import com.example.appdeise_20222.dados.Produto;
import com.example.appdeise_20222.databinding.ActivityListaBinding;

import java.util.ArrayList;
import java.util.Locale;

public class ListaActivity extends AppCompatActivity implements ReturnTotal {


    private RecyclerView recycler;
    private ListaAdapter adapter;
    private ArrayList<ItemLista> itens = new ArrayList<>();
    private ArrayList<Produto> produtos = new ArrayList<>();
    private AppDatabase db;
    ActivityListaBinding binding;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =  DataBindingUtil.setContentView(this, R.layout.activity_lista);

        getSupportActionBar().hide();
        recycler = findViewById(R.id.recycler);


        String categorias[] = {"TODAS", "Padaria","Carnes", "Congelados", "Mercearia","Matinais","Frios e Laticínios", "Enlatados", "Limpeza", "Higiene",
                "Bebidas", "Hortifruti", "Utilidades Domésticas","Pet Shop"};


        //Inicia o Banco de dados
        db =   Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "lista_compras")
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build();

        cadastroPrevioDeProdutos();

        itens = (ArrayList<ItemLista>) db.itemListaDao().getAll();


        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_item, categorias);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        binding.spCategory.setAdapter(spinnerArrayAdapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ListaActivity.this,
                LinearLayoutManager.VERTICAL, false);


        setAutoCompleteProdutos();

        binding.autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String categoria = binding.spCategory.getSelectedItem().toString();
                String produto = produtos.get(position).toString();
                addNaLista(binding.autoCompleteTextView, produto, categoria);
            }
        });

        adapter = new ListaAdapter(this,itens, this);
        recycler.setLayoutManager(layoutManager);
        recycler.setItemAnimator(new DefaultItemAnimator());
        recycler.setAdapter(adapter);
        setTotalLista();

        binding.btAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AutoCompleteTextView autoCompleteTextView = findViewById(R.id.autoCompleteTextView);
                String produto = autoCompleteTextView.getText().toString();
                String categoria = binding.spCategory.getSelectedItem().toString();
                addNaLista(autoCompleteTextView, produto, categoria);
                db.produtoDao().insert(new Produto(produto));
                setAutoCompleteProdutos();
            }
        });


        binding.btShareLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compartilhaLista(getListaInString(itens));
            }
        });
    }

    private void cadastroPrevioDeProdutos() {
        if(db.produtoDao().getAll().isEmpty()){
            ArrayList<Produto> produtos = new ArrayList<>();
            produtos.add(new Produto("Feijão"));
            produtos.add(new Produto("Arroz"));
            produtos.add(new Produto("Azeite"));
            produtos.add(new Produto("Massa"));
            produtos.add(new Produto("Macarrão instantâneo"));
            produtos.add(new Produto("Lentilha"));
            produtos.add(new Produto("Sal"));
            produtos.add(new Produto("Extrato de tomate"));
            produtos.add(new Produto("Creme de leite"));
            produtos.add(new Produto("Carne bovina"));
            produtos.add(new Produto("Carne suina"));
            produtos.add(new Produto("Linguiça"));
            produtos.add(new Produto("Salsicha"));
            produtos.add(new Produto("Salsichão"));
            produtos.add(new Produto("Salame"));
            produtos.add(new Produto("Carne moida"));
            produtos.add(new Produto("Frango"));
            produtos.add(new Produto("Açucar"));
            produtos.add(new Produto("Achocolatado"));
            produtos.add(new Produto("Farinha de Trigo"));
            produtos.add(new Produto("Polvilho azedo"));
            produtos.add(new Produto("Polvilho doce"));
            produtos.add(new Produto("Ovos"));
            produtos.add(new Produto("Pão"));
            produtos.add(new Produto("Pão de forma"));
            produtos.add(new Produto("Pão francês"));
            produtos.add(new Produto("Manteiga"));
            produtos.add(new Produto("Margarina"));
            produtos.add(new Produto("Requeijão"));
            produtos.add(new Produto("Granola"));
            produtos.add(new Produto("Aveia"));
            produtos.add(new Produto("Leite"));
            produtos.add(new Produto("Leite em pó"));
            produtos.add(new Produto("Iogurte"));
            produtos.add(new Produto("Queijo"));
            produtos.add(new Produto("Presunto"));
            produtos.add(new Produto("Mortadela"));
            produtos.add(new Produto("Geleia"));
            produtos.add(new Produto("Papel toalha"));
            produtos.add(new Produto("Guardanapo"));
            produtos.add(new Produto("Papel filme"));
            produtos.add(new Produto("Papel laminado"));
            produtos.add(new Produto("Papel manteiga"));
            produtos.add(new Produto("Sabão em pó"));
            produtos.add(new Produto("Sabão liquido"));
            produtos.add(new Produto("Sabão em barra"));
            produtos.add(new Produto("Amaciante"));
            produtos.add(new Produto("Alvejante sem cloro"));
            produtos.add(new Produto("Água sanitária"));
            produtos.add(new Produto("Desinfetante"));
            produtos.add(new Produto("Multiuso"));
            produtos.add(new Produto("Detergente"));
            produtos.add(new Produto("Esponja louça"));
            produtos.add(new Produto("Sabonete"));
            produtos.add(new Produto("Shampoo"));
            produtos.add(new Produto("Condicionador"));
            produtos.add(new Produto("Creme de cabelo"));
            produtos.add(new Produto("Pasta de dente"));
            produtos.add(new Produto("Escova de dente"));
            produtos.add(new Produto("Papel higiênico"));
            produtos.add(new Produto("Desodorante"));
            produtos.add(new Produto("Aparelho de barbear"));
            produtos.add(new Produto("Enxaguante bucal"));
            produtos.add(new Produto("Absorvente"));
            produtos.add(new Produto("Protetor diário"));
            produtos.add(new Produto("Pedra sanitária"));


            db.produtoDao().insertAll(produtos);
        }
    }

    private void setAutoCompleteProdutos() {
        produtos = (ArrayList<Produto>) db.produtoDao().getAll();
        ArrayAdapter<Produto> auc_adapter = new ArrayAdapter<Produto>(this,
                android.R.layout.simple_dropdown_item_1line, produtos);
        binding.autoCompleteTextView.setAdapter(auc_adapter);
    }

    private void addNaLista(AutoCompleteTextView autoCompleteTextView, String produto, String categoria) {
        ItemLista item = new ItemLista(produto, categoria);
        itens.add(item);
        autoCompleteTextView.setText("");

        ItenListaDao itemListaDao = db.itemListaDao();
        itemListaDao.insertItem(item);
        adapter.notifyDataSetChanged();
        setTotalLista();
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

    private void setTotalLista () {
        Double total = 0d;
        Double total_carrinho = 0d;
        for (int i = 0; i < itens.size(); i++) {
            total += itens.get(i).getSubtotal();
            if(itens.get(i).getCarrinho()){
                total_carrinho+=itens.get(i).getSubtotal();
            }
        }

        binding.totalLista.setText(formataValor(total));
        binding.totalCarrinho.setText(formataValor(total_carrinho));
    }
    private String formataValor(Double valor){
        return String.format(Locale.GERMAN, "%,.2f", valor);
    }



    @Override
    public void atualiza() {
        setTotalLista ();
    }
}