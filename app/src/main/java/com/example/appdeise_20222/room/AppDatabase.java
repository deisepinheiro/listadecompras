package com.example.appdeise_20222.room;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.appdeise_20222.model.ItemLista;
import com.example.appdeise_20222.model.Lista;
import com.example.appdeise_20222.model.Produto;

@Database(entities = {ItemLista.class, Produto.class, Lista.class}, version = 11)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ItenListaDao itemListaDao();
    public abstract ProdutoDAO produtoDao();
    public abstract ListaDao listaDao();
}



