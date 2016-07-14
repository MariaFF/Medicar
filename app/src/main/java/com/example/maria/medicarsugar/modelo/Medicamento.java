package com.example.maria.medicarsugar.modelo;

import android.content.Intent;
import android.widget.RadioGroup;

import com.orm.SugarRecord;

import java.util.Date;

/**
 * Created by maria on 10/07/2016.
 */
public class Medicamento extends SugarRecord {

    private String nome;
    private String dtVencimento;
    private Integer qtdeTotal;
    private Double qtdeTomar;
    private String dtInicio;
    private String dtTermino;
    private Integer horaInicio;
    private Boolean retomavel;
    private Double prazoRetomar;
    private Integer intervalo;
    private Boolean status;

    private Receita receita;

    public Medicamento(){

    }

    public Medicamento(String nome, String dtVencimento, Integer qtdeTotal, Double qtdeTomar, String dtInicio,
                       String dtTermino, Integer horaInicio, Boolean retomavel, Double prazoRetomar, Integer intervalo, Boolean status) {
        this.nome = nome;
        this.dtVencimento = dtVencimento;
        this.qtdeTotal = qtdeTotal;
        this.qtdeTomar = qtdeTomar;
        this.dtInicio = dtInicio;
        this.dtTermino = dtTermino;
        this.horaInicio = horaInicio;
        this.retomavel = retomavel;
        this.prazoRetomar = prazoRetomar;
        this.intervalo = intervalo;
        this.status = status;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDtVencimento() {
        return dtVencimento;
    }

    public void setDtVencimento(String dtVencimento) {
        this.dtVencimento = dtVencimento;
    }

    public Integer getQtdeTotal() {
        return qtdeTotal;
    }

    public void setQtdeTotal(Integer qtdeTotal) {
        this.qtdeTotal = qtdeTotal;
    }

    public Double getQtdeTomar() {
        return qtdeTomar;
    }

    public void setQtdeTomar(Double qtdeTomar) {
        this.qtdeTomar = qtdeTomar;
    }

    public String getDtInicio() {
        return dtInicio;
    }

    public void setDtInicio(String dtInicio) {
        this.dtInicio = dtInicio;
    }

    public String getDtTermino() {
        return dtTermino;
    }

    public void setDtTermino(String dtTermino) {
        this.dtTermino = dtTermino;
    }

    public Integer getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Integer horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Boolean getRetomavel() {
        return retomavel;
    }

    public void setRetomavel(Boolean retomavel) {
        this.retomavel = retomavel;
    }

    public Double getPrazoRetomar() {
        return prazoRetomar;
    }

    public void setPrazoRetomar(Double prazoRetomar) {
        this.prazoRetomar = prazoRetomar;
    }

    public Integer getIntervalo() {
        return intervalo;
    }

    public void setIntervalo(Integer intervalo) {
        this.intervalo = intervalo;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }



}
