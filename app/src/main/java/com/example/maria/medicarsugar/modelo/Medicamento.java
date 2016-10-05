package com.example.maria.medicarsugar.modelo;

import android.content.Intent;
import android.widget.RadioGroup;

import com.orm.SugarRecord;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by maria on 10/07/2016.
 */
public class Medicamento extends SugarRecord implements Serializable {

    private String nome;

    private Calendar dtInicio;
    private String dtTermino;
    private String dtVencimento;

    private Boolean retomavel;
    private String prazoRetomar;

    private Date intervalo1;
    private Date intervalo2;
    private Date intervalo3;

    private Integer qtdeTotal;

    private Double dose1;
    private Double dose2;
    private Double dose3;

    private Boolean status;



    public Medicamento(){

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Calendar getDtInicio() {
        return dtInicio;
    }

    public void setDtInicio(Calendar dtInicio) {
        this.dtInicio = dtInicio;
    }

    public String getDtTermino() {
        return dtTermino;
    }

    public void setDtTermino(String dtTermino) {
        this.dtTermino = dtTermino;
    }

    public String getDtVencimento() {
        return dtVencimento;
    }

    public void setDtVencimento(String dtVencimento) {
        this.dtVencimento = dtVencimento;
    }

    public Boolean getRetomavel() {
        return retomavel;
    }

    public void setRetomavel(Boolean retomavel) {
        this.retomavel = retomavel;
    }

    public String getPrazoRetomar() {
        return prazoRetomar;
    }

    public void setPrazoRetomar(String prazoRetomar) {
        this.prazoRetomar = prazoRetomar;
    }

    public Date getIntervalo1() {
        return intervalo1;
    }

    public void setIntervalo1(Date intervalo1) {
        this.intervalo1 = intervalo1;
    }

    public Date getIntervalo2() {
        return intervalo2;
    }

    public void setIntervalo2(Date intervalo2) {
        this.intervalo2 = intervalo2;
    }

    public Date getIntervalo3() {
        return intervalo3;
    }

    public void setIntervalo3(Date intervalo3) {
        this.intervalo3 = intervalo3;
    }

    public Integer getQtdeTotal() {
        return qtdeTotal;
    }

    public void setQtdeTotal(Integer qtdeTotal) {
        this.qtdeTotal = qtdeTotal;
    }

    public Double getDose1() {
        return dose1;
    }

    public void setDose1(Double dose1) {
        this.dose1 = dose1;
    }

    public Double getDose2() {
        return dose2;
    }

    public void setDose2(Double dose2) {
        this.dose2 = dose2;
    }

    public Double getDose3() {
        return dose3;
    }

    public void setDose3(Double dose3) {
        this.dose3 = dose3;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
