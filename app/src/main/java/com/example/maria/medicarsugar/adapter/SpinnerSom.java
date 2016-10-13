package com.example.maria.medicarsugar.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.maria.medicarsugar.R;
import com.example.maria.medicarsugar.modelo.ConfiguracaoCaixa;
import com.example.maria.medicarsugar.modelo.Medicamento;

import java.util.ArrayList;
import java.util.List;

public class SpinnerSom extends ArrayAdapter{

    private List listaSom = new ArrayList();
    Activity activity;

    ImageButton btPlay;

    public SpinnerSom(Context context, int resource, List listaSom) {
        super(context, resource);
        this.listaSom = listaSom;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(activity).inflate(R.layout.spinner_som, parent, false);

        TextView tvNomeMusica = (TextView) view.findViewById(R.id.spinner_som_tv_musica);
        btPlay = (ImageButton) view.findViewById(R.id.spinner_som_bt_play);


        if(position == 0){
            // Deixa o hint com a cor cinza ( efeito de desabilitado)
            tvNomeMusica.setTextColor(Color.GRAY);
            tvNomeMusica.setText("Selecione a música");

        }else if(position == 1) {
            tvNomeMusica.setTextColor(Color.BLACK);
            tvNomeMusica.setText("Faded");
            //btTeste.setImageResource(R.drawable.ic_play_black);
        }else if(position == 2){
            tvNomeMusica.setTextColor(Color.BLACK);
            tvNomeMusica.setText("Cold Water");
        }else if(position == 3){
            tvNomeMusica.setTextColor(Color.BLACK);
            tvNomeMusica.setText("Don't let me down");
        }

        return view;
    }




    @Override
    public boolean isEnabled(int position){

        if(position == 0){
            // Disabilita a primeira posição (hint)
            return false;

        } else {
            return true;
        }
    }

}

