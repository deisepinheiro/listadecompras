package com.example.appdeise_20222.model;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.ArrayList;
import java.util.List;

public class ListaComItens {

    @Embedded
    public Lista lista;
    @Relation(
            parentColumn = "id",
            entityColumn = "lista_id"
    )
    public List<ItemLista> itens;

    public ListaComItens(){
        lista = new Lista("",true);
        itens = new ArrayList();
    }

    public Double getTotalLista(){
        Double total = 0d;
        for (int i = 0; i < itens.size(); i++) {
            total += itens.get(i).getSubtotal();
        }
        return total;
    }


    public Double getTotalCarrinhoLista(){
        Double total_carrinho = 0d;
        for (int i = 0; i < itens.size(); i++) {
            if (itens.get(i).getCarrinho()) {
                total_carrinho += itens.get(i).getSubtotal();
            }
        }
        return total_carrinho;
    }

}
