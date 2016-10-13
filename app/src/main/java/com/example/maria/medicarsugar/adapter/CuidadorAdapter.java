package com.example.maria.medicarsugar.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.maria.medicarsugar.R;
import com.example.maria.medicarsugar.modelo.Cuidador;

import java.util.List;

/**
 * Created by maria on 06/10/2016.
 */
public class CuidadorAdapter extends BaseAdapter {
    private List<Cuidador> listaCuidador;
    private Activity activity;

    public CuidadorAdapter(List listaCuidador, Activity activity) {
        this.listaCuidador = listaCuidador;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return listaCuidador.size();
    }

    @Override
    public Object getItem(int position) {
        return listaCuidador.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listaCuidador.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(activity).inflate(R.layout.adapter_cuidador, parent, false);

        Cuidador cuidador = listaCuidador.get(position);

        TextView tx_nome = (TextView) view.findViewById(R.id.cuidador_tx_nome);
        tx_nome.setText(cuidador.getNome());
        TextView tx_telefone = (TextView) view.findViewById(R.id.cuidador_tx_telefone);
        tx_telefone.setText(cuidador.getTelefone());

        return view;
    }


}
