package com.example.maria.medicarsugar.modelo;

import com.orm.SugarRecord;

import java.util.Date;

/**
 * Created by maria on 05/05/2016.
 */
public class Receita extends SugarRecord {

    private Date dtReceita;
    private Date dtValidadeReceita;
    private Boolean status;

    public Receita(){

    }

    public Receita(Date dtReceita, Date dtValidadeReceita, Boolean status) {
        this.dtReceita = dtReceita;
        this.dtValidadeReceita = dtValidadeReceita;
        this.status = status;
    }

    public Date getDtReceita() {
        return dtReceita;
    }

    public void setDtReceita(Date dtReceita) {
        this.dtReceita = dtReceita;
    }

    public Date getDtValidadeReceita() {
        return dtValidadeReceita;
    }

    public void setDtValidadeReceita(Date dtValidadeReceita) {
        this.dtValidadeReceita = dtValidadeReceita;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
