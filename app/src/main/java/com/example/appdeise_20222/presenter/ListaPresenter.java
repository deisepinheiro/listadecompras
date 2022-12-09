package com.example.appdeise_20222.presenter;

import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.appdeise_20222.model.ItemLista;
import com.example.appdeise_20222.model.Lista;
import com.example.appdeise_20222.model.ListaComItens;
import com.example.appdeise_20222.model.Produto;
import com.example.appdeise_20222.room.AppDatabase;
import com.example.appdeise_20222.view.ListaActivity;

import java.util.ArrayList;
import java.util.List;

public class ListaPresenter implements ListaPresenterContract.presenter{

    private ListaPresenterContract.view activity;
    private ListaComItens minhaLista;

    public ListaPresenter(ListaPresenterContract.view activity) {
            this.activity = activity;
            this.minhaLista = new ListaComItens();
    }

    @Override
    public void cadastroPrevioDeProdutos() {
        if(AppDatabase.getInstance(activity.getActivity()).produtoDao().getAll().isEmpty()){
            ArrayList<Produto> produtos = getProdutosBase();
            AppDatabase.getInstance(activity.getActivity()).produtoDao().insertAll(produtos);
        }
    }

    @Override
    public long getListaDeComprasbyId(long id_modelo) {
        AppDatabase.getInstance(activity.getActivity()).listaDao().updateSetStatusFalse();
        minhaLista = AppDatabase.getInstance(activity.getActivity()).listaDao().getListaComItensById(id_modelo);
        activity.setMyRecicleView(minhaLista);
        return minhaLista.lista.getId();
    }

    @Override
    public long getListaDeComprasAtual() {
        List<ListaComItens> listasAtivas = AppDatabase.getInstance(activity.getActivity()).listaDao().getListaComItensByStatus(true);
        if(listasAtivas.isEmpty()){
            Lista lista = new Lista("",true);
            AppDatabase.getInstance(activity.getActivity()).listaDao().insertLista(lista);
            listasAtivas = AppDatabase.getInstance(activity.getActivity()).listaDao().getListaComItensByStatus(true);
        }
        minhaLista =listasAtivas.get(0);
        activity.setMyRecicleView(listasAtivas.get(0));
        calculaTotaisDaLista();
        return listasAtivas.get(0).lista.getId();
    }

    @Override
    public void setDadosListaCategoria() {
        String categorias[] = {"","TODAS", "Padaria","Carnes", "Congelados", "Mercearia","Matinais","Frios e Laticínios", "Enlatados", "Limpeza", "Higiene",
                "Bebidas", "Hortifruti", "Utilidades Domésticas","Pet Shop"};
        activity.setSpinnerCategoria(categorias);
    }

    @Override
    public void setDadpsAutoCompleteProduto() {
        List<Produto> produtos = AppDatabase.getInstance(activity.getActivity()).produtoDao().getAll();
        activity.setAutoCompleteProdutos(produtos);
    }

    @Override
    public void addItemNaLista(ItemLista item) {
        minhaLista.itens.add(item);
        activity.setTextAutoComplete("");
        AppDatabase.getInstance(activity.getActivity()).listaDao().insertItemNaLista(minhaLista.lista,item);
        activity.setMyRecicleView(minhaLista);
        calculaTotaisDaLista();
    }

    @Override
    public void compartilhaLista() {
        String texto_lista = getListaInString(minhaLista);
        Intent sendIntent = new Intent();
        sendIntent.setAction(android.content.Intent.ACTION_SEND);
        sendIntent.putExtra(android.content.Intent.EXTRA_TEXT, texto_lista);
        sendIntent.setType("text/plain");

        if (sendIntent.resolveActivity(activity.getActivity().getPackageManager()) != null) {
            activity.getActivity().startActivity(sendIntent);
        }
    }

    @Override
    public void saveModeloDeLista(String titulo) {
        minhaLista.lista.setDescricao(titulo);
        long id_modelo = AppDatabase.getInstance(activity.getActivity()).listaDao().insertListaComItens(minhaLista);
        if(id_modelo > 0){
            activity.showMessage("Lista "+ titulo+" salva com sucesso!");
        }else{
            activity.showMessage("Houve erro a inserir na lista!");
        }
    }

    private String getListaInString(ListaComItens minhaLista){

        StringBuilder texto = new StringBuilder();
        texto.append("Lista: "+minhaLista.lista.getDescricao() +"\n");
        for(ItemLista item : minhaLista.itens){
            texto.append(item.toString() + "\n");
        }
        return texto.toString();
    }


    public void calculaTotaisDaLista() {
        activity.setTotalListaAndCarrinho(minhaLista.getTotalLista(), minhaLista.getTotalCarrinhoLista());
    }

    @NonNull
    private ArrayList<Produto> getProdutosBase() {
        ArrayList<Produto> produtos = new ArrayList<>();
        produtos.add(new Produto("Feijão"));
        produtos.add(new Produto("Arroz"));
        produtos.add(new Produto("Azeite"));
        produtos.add(new Produto("Massa"));
        produtos.add(new Produto("Macarrão instantâneo"));
        produtos.add(new Produto("Lentilha"));
        produtos.add(new Produto("Sal"));
        produtos.add(new Produto("Extrato de tomate"));
        produtos.add(new Produto("Creme de leite"));
        produtos.add(new Produto("Carne bovina"));
        produtos.add(new Produto("Carne suina"));
        produtos.add(new Produto("Linguiça"));
        produtos.add(new Produto("Salsicha"));
        produtos.add(new Produto("Salsichão"));
        produtos.add(new Produto("Salame"));
        produtos.add(new Produto("Carne moida"));
        produtos.add(new Produto("Frango"));
        produtos.add(new Produto("Açucar"));
        produtos.add(new Produto("Achocolatado"));
        produtos.add(new Produto("Farinha de Trigo"));
        produtos.add(new Produto("Polvilho azedo"));
        produtos.add(new Produto("Polvilho doce"));
        produtos.add(new Produto("Ovos"));
        produtos.add(new Produto("Pão"));
        produtos.add(new Produto("Pão de forma"));
        produtos.add(new Produto("Pão francês"));
        produtos.add(new Produto("Manteiga"));
        produtos.add(new Produto("Margarina"));
        produtos.add(new Produto("Requeijão"));
        produtos.add(new Produto("Granola"));
        produtos.add(new Produto("Aveia"));
        produtos.add(new Produto("Leite"));
        produtos.add(new Produto("Leite em pó"));
        produtos.add(new Produto("Iogurte"));
        produtos.add(new Produto("Queijo"));
        produtos.add(new Produto("Presunto"));
        produtos.add(new Produto("Mortadela"));
        produtos.add(new Produto("Geleia"));
        produtos.add(new Produto("Papel toalha"));
        produtos.add(new Produto("Guardanapo"));
        produtos.add(new Produto("Papel filme"));
        produtos.add(new Produto("Papel laminado"));
        produtos.add(new Produto("Papel manteiga"));
        produtos.add(new Produto("Sabão em pó"));
        produtos.add(new Produto("Sabão liquido"));
        produtos.add(new Produto("Sabão em barra"));
        produtos.add(new Produto("Amaciante"));
        produtos.add(new Produto("Alvejante sem cloro"));
        produtos.add(new Produto("Água sanitária"));
        produtos.add(new Produto("Desinfetante"));
        produtos.add(new Produto("Multiuso"));
        produtos.add(new Produto("Detergente"));
        produtos.add(new Produto("Esponja louça"));
        produtos.add(new Produto("Sabonete"));
        produtos.add(new Produto("Shampoo"));
        produtos.add(new Produto("Condicionador"));
        produtos.add(new Produto("Creme de cabelo"));
        produtos.add(new Produto("Pasta de dente"));
        produtos.add(new Produto("Escova de dente"));
        produtos.add(new Produto("Papel higiênico"));
        produtos.add(new Produto("Desodorante"));
        produtos.add(new Produto("Aparelho de barbear"));
        produtos.add(new Produto("Enxaguante bucal"));
        produtos.add(new Produto("Absorvente"));
        produtos.add(new Produto("Protetor diário"));
        produtos.add(new Produto("Pedra sanitária"));
        return produtos;
    }
}
