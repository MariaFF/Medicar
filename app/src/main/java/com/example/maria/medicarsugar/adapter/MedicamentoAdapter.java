package com.example.maria.medicarsugar.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.maria.medicarsugar.R;
import com.example.maria.medicarsugar.modelo.Medicamento;

import java.util.List;

/**
 * Created by maria on 12/07/2016.
 */
public class MedicamentoAdapter extends BaseAdapter{

    private List<Medicamento> listaMedicamento;
    private Context context;

    public MedicamentoAdapter(List<Medicamento> listaMedicamento, Context context) {
        this.listaMedicamento = listaMedicamento;
        this.context = context;
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.medicamento_adapter, parent, false);
        Medicamento medicamento = listaMedicamento.get(position);

        TextView tvNome = (TextView) view.findViewById(R.id.medicamento_tv_nome);
        tvNome.setText(medicamento.getNome());
        //TextView tvQtdeTomar = (TextView) view.findViewById(R.id.medicamento_tv_qtde_tomar);
        //tvQtdeTomar.setText(medicamento.getQtdeTomar().toString());
        //TextView tvHoraTomar = (TextView) view.findViewById(R.id.medicamento_tv_hora_tomar);
        //tvHoraTomar.setText(medicamento.getHoraInicio());
        //TextView tvIntervalo = (TextView) view.findViewById(R.id.medicamento_tv_intervalo);
        //tvIntervalo.setText(medicamento.getIntervalo());

        return view;
    }
}
