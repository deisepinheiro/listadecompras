package com.example.appdeise_20222.dados;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface ListaDao {

    @Query("SELECT * FROM Lista")
    List<Lista> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllList(List<Lista> listas);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertLista(Lista lista);

    @Delete
    void deleteLista(Lista lista);

    @Update
    void update(Lista lista);

    @Transaction
    @Query("SELECT * FROM Lista")
    public List<ListaComItens> getListaComItens();

    @Transaction
    @Query("SELECT * FROM Lista where lista.ativa = :ativo")
    public List<ListaComItens> getListaComItensByStatus(boolean ativo);

    @Transaction
    @Query("SELECT * FROM Lista where lista.ativa = :ativo and lista.descricao LIKE '%' || :search || '%'")
    public List<ListaComItens> getListaComItensbyStatusAndDescription(boolean ativo, String search);

    @Transaction
    public default void insertListaComItens(ListaComItens minhaLista) {

        Lista newLista = new Lista(minhaLista.lista.getDescricao(),false);
        final long listaID = insertLista(newLista);

        for (ItemLista item : minhaLista.itens) {
            item.setListaId(listaID);
            item.setCarrinho(false);
            item.setId(0);
            insertItem(item);
        }
    }

    public default void insertItemNaLista(Lista lista, ItemLista item) {
        item.setListaId(lista.getId());
        insertItem(item);
    }

    public default void updateItemNaLista(Lista lista, ItemLista item) {
        item.setListaId(lista.getId());
        updateItem(item);
    }

    @Insert
    void insertItem(ItemLista itemLista);
    @Update
    void updateItem(ItemLista itemLista);

    @Query("Update Lista set ativa = 'false' where lista.ativa = 'true'")
    void updateSetStatusFalse();

    @Transaction
    @Query("SELECT * FROM Lista where lista.id = :id_modelo limit 1")
    ListaComItens getListaComItensById(long id_modelo);


    @Transaction
    public default ListaComItens copiaListaComItens(ListaComItens original) {
        ListaComItens listaComItens = new ListaComItens();
        Lista newLista = new Lista(original.lista.getDescricao(),true);
        listaComItens.lista = newLista;
        listaComItens.itens = new ArrayList<>();
        final long listaID = insertLista(newLista);

        for (ItemLista item : original.itens) {
            item.setListaId(listaID);
            item.setCarrinho(false);
            item.setId(0);
            insertItem(item);
            listaComItens.itens.add(item);
        }
        return listaComItens;
    }
}
