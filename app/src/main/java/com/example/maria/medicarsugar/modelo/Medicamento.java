package com.example.maria.medicarsugar.modelo;

import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
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

    private String dtInicio;
    private String dtTermino;
    private String dtVencimento;

    private Boolean retomavel;
    private String prazoRetomar;

    private Date intervalo1;
    private Date intervalo2;
    private Date intervalo3;
    private Date intervalo4;

    private Double qtdeTotal;
    private String qtdeRestante;

    private Double dose1;
    private Double dose2;
    private Double dose3;
    private Double dose4;

    private boolean status;



    public Medicamento(){

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public Double getQtdeTotal() {

        return qtdeTotal;
    }

    public void setQtdeTotal(Double qtdeTotal) {
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

    public Date getIntervalo4() {
        return intervalo4;
    }

    public void setIntervalo4(Date intervalo4) {
        this.intervalo4 = intervalo4;
    }

    public Double getDose4() {
        return dose4;
    }

    public void setDose4(Double dose4) {
        this.dose4 = dose4;
    }

    public boolean isStatus() {
        return status;
    }

    public String getQtdeRestante() {
        return qtdeRestante;
    }

    public void setQtdeRestante(String qtdeRestante) {
        this.qtdeRestante = qtdeRestante;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
