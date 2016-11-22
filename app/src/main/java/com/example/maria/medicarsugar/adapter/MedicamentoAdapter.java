package com.example.maria.medicarsugar.adapter;

import android.content.Context;
import android.test.suitebuilder.TestMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.maria.medicarsugar.R;
import com.example.maria.medicarsugar.medicamento.NovoMedicamento;
import com.example.maria.medicarsugar.modelo.Medicamento;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by maria on 12/07/2016.
 */
public class MedicamentoAdapter extends BaseAdapter{

    private List<Medicamento> listaMedicamento;
    private Context context;

    //private TextView tvQtdeDias = null;

    private TextView tvDose1;
    private TextView tvDose2;
    private TextView tvDose3;
    private TextView tvDose4;

    private TextView tvHorario1;
    private TextView tvHorario2;
    private TextView tvHorario3;
    private TextView tvHorario4;
    private Medicamento medicamento;

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
        medicamento = listaMedicamento.get(position);

        //Double qtdeDias = Double.valueOf(medicamento.getQtdeRestante());
        //Log.i("adapter", "qtde " + qtdeDias);

        tvDose1 = (TextView) view.findViewById(R.id.medicamento_tv_dose1);
        tvDose2 = (TextView) view.findViewById(R.id.medicamento_tv_dose2);
        tvDose3 = (TextView) view.findViewById(R.id.medicamento_tv_dose3);
        tvDose4 = (TextView) view.findViewById(R.id.medicamento_tv_dose4);

        tvHorario1 = (TextView) view.findViewById(R.id.medicamento_tv_hora_tomar1);
        tvHorario2 = (TextView) view.findViewById(R.id.medicamento_tv_hora_tomar2);
        tvHorario3 = (TextView) view.findViewById(R.id.medicamento_tv_hora_tomar3);
        tvHorario4 = (TextView) view.findViewById(R.id.medicamento_tv_hora_tomar4);

        tvHorario1.setVisibility(View.INVISIBLE);
        tvDose1.setVisibility(View.INVISIBLE);
        tvHorario2.setVisibility(View.INVISIBLE);
        tvDose2.setVisibility(View.INVISIBLE);
        tvHorario3.setVisibility(View.INVISIBLE);
        tvDose3.setVisibility(View.INVISIBLE);
        tvHorario4.setVisibility(View.INVISIBLE);
        tvDose4.setVisibility(View.INVISIBLE);


        TextView tvNome = (TextView) view.findViewById(R.id.medicamento_tv_nome);
        tvNome.setText(medicamento.getNome());

        TextView tvDt = (TextView) view.findViewById(R.id.medicamento_tv_dtInicial);
        //tvDt.setText(medicamento.getDtInicio().toString());
        TextView tvQtdeDias = (TextView) view.findViewById(R.id.medicamento_tv_qtdeDias);

        String qtdeRestanteDias = null;
        Double qtdeMedTomadoDia = null;



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

            //controle de dias
            qtdeRestanteDias = format.format(medicamento.getQtdeTotal() / medicamento.getDose1());
            tvQtdeDias.setText("Duração em dias : " + qtdeRestanteDias);

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
            //controle de dias
            qtdeMedTomadoDia = medicamento.getDose1() + medicamento.getDose2();
            qtdeRestanteDias = format.format(medicamento.getQtdeTotal() / qtdeMedTomadoDia);
            tvQtdeDias.setText("Duração em dias : " + qtdeRestanteDias);

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
            //CONTROLE EM DIAS
            qtdeMedTomadoDia = medicamento.getDose1() + medicamento.getDose2() + medicamento.getDose3();
            qtdeRestanteDias = format.format(medicamento.getQtdeTotal() / qtdeMedTomadoDia);
            tvQtdeDias.setText("Duração em dias : " + qtdeRestanteDias);

        }if(medicamento.getIntervalo4() != null){
            tvHorario4.setVisibility(View.VISIBLE);
            tvDose4.setVisibility(View.VISIBLE);

            Date intervalo4 = medicamento.getIntervalo4();
            SimpleDateFormat timeFormatIntervalo4 = new SimpleDateFormat("HH:mm", Locale.getDefault());
            final String horaIntervalo4 = timeFormatIntervalo4.format(intervalo4);

            DecimalFormat format = new DecimalFormat("0.#");
            String sDose4 = format.format(medicamento.getDose4());
            tvHorario4.setText("Hora: " + horaIntervalo4);
            tvDose4.setText("Dose: " + sDose4);
            //CONTROLE EM DIAS
            qtdeMedTomadoDia = medicamento.getDose1() + medicamento.getDose2() + medicamento.getDose3() + medicamento.getDose4();
            qtdeRestanteDias = format.format(medicamento.getQtdeTotal() / qtdeMedTomadoDia);
            tvQtdeDias.setText("Duração em dias : " + qtdeRestanteDias);

        }



        return view;
    }
}
