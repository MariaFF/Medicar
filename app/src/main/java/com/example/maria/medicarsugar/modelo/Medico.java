package com.example.maria.medicarsugar.modelo;

import com.orm.SugarRecord;

import java.io.Serializable;

/**
 * Created by maria on 04/05/2016.
 */
public class Medico extends SugarRecord implements Serializable{


    private String nome;
    private String telefone1;
    private String telefone2;
    //private Boolean status;

    public Medico(){

    }

    public Medico(String nome, String telefone1, String telefone2) {
        this.nome = nome;
        this.telefone1 = telefone1;
        this.telefone2 = telefone2;

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone1() {
        return telefone1;
    }

    public void setTelefone1(String telefone1) {
        this.telefone1 = telefone1;
    }

    public String getTelefone2() {
        return telefone2;
    }

    public void setTelefone2(String telefone2) {
        this.telefone2 = telefone2;
    }

}
