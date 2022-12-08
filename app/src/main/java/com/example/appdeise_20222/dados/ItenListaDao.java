package com.example.appdeise_20222.dados;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ItenListaDao {

    @Query("SELECT * FROM ItemLista")
    List<ItemLista> getAll();

    @Insert
    void insertAllList(List<ItemLista> itens);

    //@Insert
    //void insertItem(ItemLista item);

    @Delete
    void delete(ItemLista user);

    @Update
    void update(ItemLista user);

}
