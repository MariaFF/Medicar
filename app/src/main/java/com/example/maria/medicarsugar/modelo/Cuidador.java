package com.example.maria.medicarsugar.modelo;

import com.orm.SugarRecord;

import java.io.Serializable;

/**
 * Created by maria on 05/10/2016.
 */
public class Cuidador extends SugarRecord implements Serializable {

    private String nome;
    private String telefone;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
