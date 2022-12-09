package com.example.appdeise_20222.model;

public class User {
    private String login;
    private String senha;
    private String nome;

    public User(String email, String senha, String nome) {
        this.login = email;
        this.senha = senha;
        this.nome = nome;
    }
    public User(String email, String senha) {
        this.login = email;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
