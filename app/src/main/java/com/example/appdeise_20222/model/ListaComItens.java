package com.example.appdeise_20222.model;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class ListaComItens {

    @Embedded
    public Lista lista;
    @Relation(
            parentColumn = "id",
            entityColumn = "lista_id"
    )
    public List<ItemLista> itens;

}
