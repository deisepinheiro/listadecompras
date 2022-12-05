package com.example.appdeise_20222.dados;


import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {ItemLista.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ItenListaDao itemListaDao();
}



