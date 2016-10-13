package com.example.maria.medicarsugar.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.maria.medicarsugar.R;
import com.example.maria.medicarsugar.modelo.Medicamento;
import com.example.maria.medicarsugar.modelo.Movimentacao;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by maria on 04/09/2016.
 */
public class MovimentacaoAdapter extends BaseAdapter {
    
    private List<Movimentacao> listaMovimentacao;
    private Activity activity;

    public MovimentacaoAdapter(List<Movimentacao> listaMovimentacao, Activity activity) {
        this.listaMovimentacao = listaMovimentacao;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return listaMovimentacao.size();
    }

    @Override
    public Object getItem(int position) {
        return listaMovimentacao.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listaMovimentacao.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(activity).inflate(R.layout.movimentacao_adapter, parent, false);

        Movimentacao movimentacao = listaMovimentacao.get(position);
        Medicamento medicamento = new Medicamento();
        TextView tvNomeMedicamento = (TextView) view.findViewById(R.id.movimentacao_nome_remedio);
        tvNomeMedicamento.setText(medicamento.getNome());


        //--------------------------------------------
        /*TextView tvHora = (TextView) view.findViewById(R.id.movimentacao_hora);
        Calendar horaInicio = medicamento.getHoraInicio();

        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        String hora = timeFormat.format(horaInicio);

        tvHora.setText(hora.toString());
        //--------------------------------------------

        TextView tvDose = (TextView) view.findViewById(R.id.movimentacao_dose);
        tvDose.setText(medicamento.getQtdeTomar());*/
        return view;
    }
}
