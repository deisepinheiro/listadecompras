package com.example.appdeise_20222.room;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.appdeise_20222.model.ItemLista;
import com.example.appdeise_20222.model.Lista;
import com.example.appdeise_20222.model.Produto;

@Database(entities = {ItemLista.class, Produto.class, Lista.class}, version = 12)
public abstract class AppDatabase extends RoomDatabase {
    protected static final String DB_NAME = "lista_compras";
    public static volatile AppDatabase instance;

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = create(context);
        }
        return instance;
    }
    protected AppDatabase() {};

    private static AppDatabase create(final Context context) {
        return Room.databaseBuilder(
                context,
                AppDatabase.class,
                DB_NAME)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
    }

    public abstract ItenListaDao itemListaDao();
    public abstract ProdutoDAO produtoDao();
    public abstract ListaDao listaDao();

}



