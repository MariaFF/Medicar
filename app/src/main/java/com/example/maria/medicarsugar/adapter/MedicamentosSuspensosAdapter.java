package com.example.maria.medicarsugar.adapter;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.maria.medicarsugar.R;
import com.example.maria.medicarsugar.modelo.Medicamento;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MedicamentosSuspensosAdapter extends BaseAdapter{

    List<Medicamento> listaMedicamentosSuspensos;
    Activity activity;


    private TextView tvDose1;
    private TextView tvDose2;
    private TextView tvDose3;

    private TextView tvHorario1;
    private TextView tvHorario2;
    private TextView tvHorario3;

    public MedicamentosSuspensosAdapter(Activity activity, List<Medicamento> listaMedicamentosSuspensos) {
        this.activity = activity;
        this.listaMedicamentosSuspensos = listaMedicamentosSuspensos;
    }

    @Override
    public int getCount() {
        return listaMedicamentosSuspensos.size();
    }

    @Override
    public Object getItem(int position) {
        return listaMedicamentosSuspensos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listaMedicamentosSuspensos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(activity).inflate(R.layout.medicamentos_suspensos_adapter, parent, false);
        Medicamento medicamento = listaMedicamentosSuspensos.get(position);


        tvDose1 = (TextView) view.findViewById(R.id.medicamento_tv_dose1);
        tvDose2 = (TextView) view.findViewById(R.id.medicamento_tv_dose2);
        tvDose3 = (TextView) view.findViewById(R.id.medicamento_tv_dose3);

        tvHorario1 = (TextView) view.findViewById(R.id.medicamento_tv_hora_tomar1);
        tvHorario2 = (TextView) view.findViewById(R.id.medicamento_tv_hora_tomar2);
        tvHorario3 = (TextView) view.findViewById(R.id.medicamento_tv_hora_tomar3);

        tvHorario1.setVisibility(View.INVISIBLE);
        tvDose1.setVisibility(View.INVISIBLE);
        tvHorario2.setVisibility(View.INVISIBLE);
        tvDose2.setVisibility(View.INVISIBLE);
        tvHorario3.setVisibility(View.INVISIBLE);
        tvDose3.setVisibility(View.INVISIBLE);


        TextView tvNome = (TextView) view.findViewById(R.id.medicamento_tv_nome);
        tvNome.setText(medicamento.getNome());


        if(medicamento.getIntervalo1() != null) {
            tvHorario1.setVisibility(View.VISIBLE);
            tvDose1.setVisibility(View.VISIBLE);

            Date intervalo1 = medicamento.getIntervalo1();

            SimpleDateFormat timeFormatIntervalo1 = new SimpleDateFormat("HH:mm", Locale.getDefault());
            final String horaIntervalo1 = timeFormatIntervalo1.format(intervalo1);

            DecimalFormat format = new DecimalFormat("0.#");
            String sDose1 = format.format(medicamento.getDose1());
            tvHorario1.setText("Hora: " + horaIntervalo1);
            tvDose1.setText("Dose: " + sDose1);
        }
        if(medicamento.getIntervalo2() != null){
            tvHorario2.setVisibility(View.VISIBLE);
            tvDose2.setVisibility(View.VISIBLE);

            Date intervalo2 = medicamento.getIntervalo2();
            SimpleDateFormat timeFormatIntervalo2 = new SimpleDateFormat("HH:mm", Locale.getDefault());
            final String horaIntervalo2 = timeFormatIntervalo2.format(intervalo2);

            DecimalFormat format = new DecimalFormat("0.#");
            String sDose2 = format.format(medicamento.getDose2());
            tvHorario2.setText("Hora: " + horaIntervalo2);
            tvDose2.setText("Dose: " + sDose2);
        }
        if(medicamento.getIntervalo3() != null){
            tvHorario3.setVisibility(View.VISIBLE);
            tvDose3.setVisibility(View.VISIBLE);

            Date intervalo3 = medicamento.getIntervalo3();
            SimpleDateFormat timeFormatIntervalo3 = new SimpleDateFormat("HH:mm", Locale.getDefault());
            final String horaIntervalo3 = timeFormatIntervalo3.format(intervalo3);

            DecimalFormat format = new DecimalFormat("0.#");
            String sDose3 = format.format(medicamento.getDose3());
            tvHorario3.setText("Hora: " + horaIntervalo3);
            tvDose3.setText("Dose: " + sDose3);
        }



        return view;
    }
}
