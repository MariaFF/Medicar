package com.example.maria.medicarsugar.modelo;

import com.orm.SugarRecord;

/**
 * Created by maria on 14/07/2016.
 */
public class Movimentacao extends SugarRecord {

    private String dtTomado;
    private String hora;
    private Double qtdeTomada;
    private Boolean tomado;

    private Medicamento medicamento;

    public Movimentacao() {
    }

    public Movimentacao(String dtTomado, String hora, Double qtdeTomada, Boolean tomado) {
        this.dtTomado = dtTomado;
        this.hora = hora;
        this.qtdeTomada = qtdeTomada;
        this.tomado = tomado;
    }

    public String getDtTomado() {
        return dtTomado;
    }

    public void setDtTomado(String dtTomado) {
        this.dtTomado = dtTomado;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public Double getQtdeTomada() {
        return qtdeTomada;
    }

    public void setQtdeTomada(Double qtdeTomada) {
        this.qtdeTomada = qtdeTomada;
    }

    public Boolean getTomado() {
        return tomado;
    }

    public void setTomado(Boolean tomado) {
        this.tomado = tomado;
    }
}
