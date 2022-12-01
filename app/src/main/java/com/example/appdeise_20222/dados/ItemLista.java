package com.example.appdeise_20222.dados;

public class ItemLista {

    private String descricao;
    private String categoria;
    private Integer quantidade;
    private Double preco;
    private Double subtotal;

    public ItemLista(String descricao, String categoria) {
        this.descricao = descricao;
        this.categoria = categoria;
        this.quantidade = 1;
        this.preco = 0d; // = 0.0
    }

    public Double getSubtotal() {
        return preco * quantidade;
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
}
