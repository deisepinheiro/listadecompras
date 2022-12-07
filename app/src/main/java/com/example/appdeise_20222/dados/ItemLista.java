package com.example.appdeise_20222.dados;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Locale;

@Entity
public class ItemLista {

    private String descricao;
    private String categoria;
    private Integer quantidade;
    private Double preco;
    private Double subtotal;
    private Boolean carrinho;

    @PrimaryKey(autoGenerate = true)
    public int id;

    public ItemLista(String descricao, String categoria) {
        this.descricao = descricao;
        this.categoria = categoria;
        this.quantidade = 1;
        this.carrinho = false;
        this.preco = 0d; // = 0.0
    }

    @Override
    public String toString() {
        return " descricao='" + descricao + '\'' +
                ", categoria='" + categoria + '\'' +
                ", quantidade=" + quantidade +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getSubtotal() {
        return preco * quantidade;
    }

    public String getSubtotalFormatado() {
        Double valor = preco * quantidade;
        String valor_formatado =  String.format(Locale.GERMAN, "%,.2f", valor);
        return valor_formatado;
    }



    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public String getPrecoBinding() {
        return (preco > 0 )? preco.toString() : "";
    }

    public void setPrecoBinding(Double preco) {
        this.preco = preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public void addQuantidade(){
        this.quantidade++;
    }

    public void diminuiQuantidade(){
        this.quantidade--;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Integer getQuantidade() {
        return this.quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Boolean getCarrinho() {
        return carrinho;
    }

    public void setCarrinho(Boolean carrinho) {
        this.carrinho = carrinho;
    }
}
