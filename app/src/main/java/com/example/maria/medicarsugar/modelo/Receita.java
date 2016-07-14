package com.example.maria.medicarsugar.modelo;

import com.orm.SugarRecord;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by maria on 05/05/2016.
 */
public class Receita extends SugarRecord implements Serializable {

    private String dtReceita;
    private String dtValidadeReceita;
    private Boolean status;
    private Medico medico;

    public Receita(){

    }

    public Receita(String dtReceita, String dtValidadeReceita, Boolean status) {
        this.dtReceita = dtReceita;
        this.dtValidadeReceita = dtValidadeReceita;
        this.status = status;
    }

    public String getDtReceita() {
        return dtReceita;
    }

    public void setDtReceita(String dtReceita) {
        this.dtReceita = dtReceita;
    }

    public String getDtValidadeReceita() {
        return dtValidadeReceita;
    }

    public void setDtValidadeReceita(String dtValidadeReceita) {
        this.dtValidadeReceita = dtValidadeReceita;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
