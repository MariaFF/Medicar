package com.example.maria.medicarsugar.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.maria.medicarsugar.R;
import com.example.maria.medicarsugar.modelo.Medicamento;

import java.util.List;

public class SpinnerMedicamento extends BaseAdapter {
    List<Medicamento> listaMedicamento;
    Activity activity;

    public SpinnerMedicamento(List<Medicamento> listaMedicamento, Activity activity) {
        this.listaMedicamento = listaMedicamento;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return listaMedicamento.size();
    }

    @Override
    public Object getItem(int position) {
        return listaMedicamento.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listaMedicamento.get(position).getId();
    }

    /*@Override
    public boolean isEnabled(int position){

        if(position == 0){

            // Disabilita a primeira posição (hint)
            return false;

        } else {
            return true;
        }
    }

    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {

        View view = super.getDropDownView(position, convertView, parent);

        TextView tv = (TextView) view.findViewById(R.id.tv_spinner_medicamento_nome);

        if(position == 0){

            // Deixa o hint com a cor cinza ( efeito de desabilitado)
            tv.setText("Selecione o medicamento");
            tv.setTextColor(Color.GRAY);

        }else {
            tv.setTextColor(Color.BLACK);

        }

        return view;
    }*/

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(activity).inflate(R.layout.spinner_medicamento, parent, false);
        Medicamento medicamento = listaMedicamento.get(position);


        TextView tvNomeMedicamento = (TextView) view.findViewById(R.id.tv_spinner_medicamento_nome);
        tvNomeMedicamento.setText(medicamento.getNome());
        return view;
    }
}
