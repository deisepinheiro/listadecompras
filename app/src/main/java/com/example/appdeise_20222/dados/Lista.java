package com.example.appdeise_20222.dados;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.io.Serializable;


@Entity
public class Lista implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private long id;
    private String descricao;
    private boolean ativa;

    public Lista(long id, String descricao, boolean ativa) {
        this.id = id;
        this.descricao = descricao;
        this.ativa = ativa;
    }
    @Ignore
    public Lista(String descricao, boolean ativa) {
        this.descricao = descricao;
        this.ativa = ativa;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isAtiva() {
        return ativa;
    }

    public void setAtiva(boolean ativa) {
        this.ativa = ativa;
    }
}
