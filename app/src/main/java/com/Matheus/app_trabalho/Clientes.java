package com.Matheus.app_trabalho;

import androidx.annotation.NonNull;

public class Clientes{

    public int id;
    public String nome;
    public String marcas;
    public String modelos;


    public Clientes() {

    }

    public Clientes(String nome, String marcas, String modelos) {
        this.nome = nome;
        this.marcas = marcas;
        this.modelos = modelos;
    }

    @NonNull
    @Override
    public String toString() {
        return  nome + "  -  " + marcas + "  -  " + modelos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMarcas() {
        return marcas;
    }

    public void setMarcas(String marcas) {
        this.marcas = marcas;
    }

    public String getModelos() {
        return modelos;
    }

    public void setModelos(String modelos) {
        this.modelos = modelos;
    }
}
