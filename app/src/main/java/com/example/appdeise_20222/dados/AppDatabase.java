package com.example.appdeise_20222.dados;


import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {ItemLista.class, Produto.class}, version = 5)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ItenListaDao itemListaDao();
    public abstract ProdutoDAO produtoDao();
}



