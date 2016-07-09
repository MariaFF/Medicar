package com.example.maria.medicarsugar.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.maria.medicarsugar.R;
import com.example.maria.medicarsugar.modelo.Medico;

import java.util.List;

/**
 * Created by maria on 05/05/2016.
 */
public class MedicoAdapter extends BaseAdapter {
    List<Medico> listaMedicos;
    Activity activity;


    public MedicoAdapter(List<Medico> listaMedicos, Activity activity) {
        this.listaMedicos = listaMedicos;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return listaMedicos.size();
    }

    @Override
    public Object getItem(int position) {
        return listaMedicos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listaMedicos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(activity).inflate(R.layout.medico_adapter, parent, false);

        Medico medico = listaMedicos.get(position);
        TextView tx_nome = (TextView) view.findViewById(R.id.medico_txNome);
        tx_nome.setText(medico.getNome());

        /*TextView tx_crm = (TextView) view.findViewById(R.id.medico_txCrm);
        tx_crm.setText(medico.getCrm());*/

        TextView tx_tel = (TextView) view.findViewById(R.id.medico_txTelefone);
        tx_tel.setText(medico.getTelefone1());
        return view;
    }
}
