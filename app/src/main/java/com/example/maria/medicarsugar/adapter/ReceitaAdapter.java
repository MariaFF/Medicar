package com.example.maria.medicarsugar.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.maria.medicarsugar.R;
import com.example.maria.medicarsugar.modelo.Receita;

import java.util.List;

/**
 * Created by maria on 10/07/2016.
 */
public class ReceitaAdapter extends BaseAdapter {
    private List<Receita> listaReceita;
    private Activity activity;

    public ReceitaAdapter(List<Receita> listaReceita, Activity activity) {
        this.listaReceita = listaReceita;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return listaReceita.size();
    }

    @Override
    public Object getItem(int position) {
        return listaReceita.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listaReceita.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(activity).inflate(R.layout.receita_adapter, parent, false);

        Receita receita = listaReceita.get(position);

        TextView tx_dtReceita = (TextView) view.findViewById(R.id.receita_tx_dtReceita);
        tx_dtReceita.setText(receita.getDtReceita());

        TextView tx_dtValidade = (TextView) view.findViewById(R.id.receita_dt_validade);
        tx_dtValidade.setText(receita.getDtValidadeReceita());

        return view;
    }
}
