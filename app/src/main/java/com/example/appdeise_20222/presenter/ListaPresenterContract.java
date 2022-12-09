package com.example.appdeise_20222.presenter;

import android.app.Activity;

import com.example.appdeise_20222.model.ItemLista;
import com.example.appdeise_20222.model.ListaComItens;
import com.example.appdeise_20222.model.Produto;

import java.util.List;

public class ListaPresenterContract {

    public interface view {
        Activity getActivity();
        void setMyRecicleView(ListaComItens minhaLista);
        void setSpinnerCategoria(String[] categorias);
        void setAutoCompleteProdutos(List<Produto> produtos);
        void setTextAutoComplete(String s);
        void setTotalListaAndCarrinho(Double totalLista, Double totalCarrinho);
        void showMessage(String msg);
    }

    public interface presenter {
        void cadastroPrevioDeProdutos();
        long getListaDeComprasbyId(long id_modelo);
        long getListaDeComprasAtual();
        void setDadosListaCategoria();
        void setDadpsAutoCompleteProduto();
        void addItemNaLista(ItemLista item);
        void compartilhaLista();
        void calculaTotaisDaLista();
        void saveModeloDeLista(String toString);
    }
}
