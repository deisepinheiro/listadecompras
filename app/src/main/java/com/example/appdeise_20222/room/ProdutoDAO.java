package com.example.appdeise_20222.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.appdeise_20222.model.Produto;

import java.util.List;
@Dao
public interface ProdutoDAO {

    @Query("SELECT * FROM Produto")
    List<Produto> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Produto produto);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Produto> produtos);

    @Delete
    void delete(Produto produto);

    @Update
    void update(Produto produto);

}

