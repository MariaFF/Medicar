package com.example.maria.medicarsugar.medicamento;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.example.maria.medicarsugar.R;
import com.example.maria.medicarsugar.modelo.Medicamento;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DialogIntervaloMedicamento extends DialogFragment {

    private Medicamento medicamento;
    private Calendar calendar = Calendar.getInstance();

    private EditText campoHoraIntervaloDialog;
    private EditText campoDose;
    private NovoMedicamento novoMedicamento;

    private Date intervalo1;
    private Date intervalo2;
    private Date intervalo3;

    private Date hora1;
    private Date hora2;
    private Date hora3;


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_intervalo_medicamento, null);

        campoHoraIntervaloDialog = (EditText) view.findViewById(R.id.timePicker_dialog_hora_medicamento);
        campoDose = (EditText) view.findViewById(R.id.quantidade_dialog);
        campoHoraIntervaloDialog.setOnClickListener(chamarDialogHora());

        AlertDialog.Builder alertIntervalo = new AlertDialog.Builder(getActivity())
                .setTitle("Informe a hora e a quantidade de medicamentos")
                .setView(view)

                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if(novoMedicamento.campoIntervalo1.isFocused()){


                            getMedicamento().setDose1(Double.valueOf(campoDose.getText().toString()));
                            //String sDose1 = String.valueOf(getMedicamento().getDose1());
                            String sDose1;
                            getMedicamento().setIntervalo1(hora1);

                            intervalo1 = medicamento.getIntervalo1();
                            //Log.i("dialog", "Intervalo1 " +medicamento.getIntervalo1());
                            SimpleDateFormat timeFormatIntervalo = new SimpleDateFormat("HH:mm", Locale.getDefault());
                            final String horaIntervalo1 = timeFormatIntervalo.format(intervalo1);
                            //main.campoIntervalo1.setText(horaIntervalo1);

                            DecimalFormat format = new DecimalFormat("0.#");
                            sDose1 = format.format(getMedicamento().getDose1());
                            novoMedicamento.campoIntervalo1.setText("Hora: " + horaIntervalo1 + " - " + "Dose: " + sDose1);


                        }if(novoMedicamento.campoIntervalo2.isFocused()){

                            getMedicamento().setDose2(Double.valueOf(campoDose.getText().toString()));
                            getMedicamento().setIntervalo2(hora2);
                            String sDose2;
                            intervalo2 = medicamento.getIntervalo2();

                            Log.i("dialog", "Intervalo2 " +medicamento.getIntervalo2());
                            SimpleDateFormat timeFormatIntervalo = new SimpleDateFormat("HH:mm", Locale.getDefault());
                            final String horaIntervalo2 = timeFormatIntervalo.format(intervalo2);

                            DecimalFormat format = new DecimalFormat("0.#");
                            sDose2 = format.format(getMedicamento().getDose2());
                            novoMedicamento.campoIntervalo2.setText("Hora: " + horaIntervalo2 + " - " + "Dose: " + sDose2);



                        }if(novoMedicamento.campoIntervalo3.isFocused()){

                            getMedicamento().setDose3(Double.valueOf(campoDose.getText().toString()));
                            medicamento.setIntervalo3(hora3);
                            String sDose3;
                            intervalo3 = medicamento.getIntervalo3();

                            Log.i("dialog", "Intervalo3 " +medicamento.getIntervalo3());
                            SimpleDateFormat timeFormatIntervalo = new SimpleDateFormat("HH:mm", Locale.getDefault());
                            final String horaIntervalo3 = timeFormatIntervalo.format(intervalo3);

                            DecimalFormat format = new DecimalFormat("0.#");
                            sDose3 = format.format(getMedicamento().getDose3());
                            novoMedicamento.campoIntervalo3.setText("Hora: " + horaIntervalo3 + " - " + "Dose: " + sDose3);

                        }



                    }

                })
                .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dismiss();
                    }
                });

        //FIM DO ONCREATE DIALOG
        return alertIntervalo.create();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            novoMedicamento = (NovoMedicamento) activity;
            //Log.i("dialog", "Main"+main);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     *
     * Esse método é chamado no dialog para informar hora e quantidade, é ele quem chama o TimePicker
     */
    private View.OnClickListener chamarDialogHora() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirRelogioSelecionarHora();
            }
        };
    }


    /**
     * Chama o relégio e seta a hora e minuto atual
     */
    private void abrirRelogioSelecionarHora() {
        //pegando a hora do atual sistema
        int hora = calendar.get(Calendar.HOUR_OF_DAY);
        //pegando a hora do atual sistema
        int minuto = calendar.get(Calendar.MINUTE);

        //Cria o Relogio
        TimePickerDialog timePickerDialog = new TimePickerDialog(
                getActivity(),
                new SelecionaDataHora(),
                hora, minuto,
                true
        );
        timePickerDialog.show();
    }


    public class SelecionaDataHora  implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
/*

            calendar.set(year, monthOfYear, dayOfMonth);

            data = calendar.getTime();
            medicamento.setDataInicio(data);
            //
            DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);
            dataInicio = medicamento.getDataInicio();

            //String dataInicio = dateFormat.format(data);
            //edDataInicio.setText(dataInicio.toString());
*/

        }

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

            calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
            calendar.set(Calendar.MINUTE, minute);


            if(novoMedicamento.campoIntervalo1.isFocused()){
                //pegando a hora que a pessoa selecionou do primeiro intervalo
                hora1 = calendar.getTime();
                medicamento.setIntervalo1(hora1);
                intervalo1 = medicamento.getIntervalo1();

                SimpleDateFormat timeFormatIntervalo1 = new SimpleDateFormat("HH:mm", Locale.getDefault());
                final String horaIntervalo1 = timeFormatIntervalo1.format(intervalo1);
                campoHoraIntervaloDialog.setText(horaIntervalo1);


                Log.i("dialog", "primeiro if setHora");

            }if(novoMedicamento.campoIntervalo2.isFocused()){
                //pegando a hora que a pessoa selecionou do segundo intervalo
                hora2 = calendar.getTime();
                medicamento.setIntervalo2(hora2);
                intervalo2 = medicamento.getIntervalo2();

                SimpleDateFormat timeFormatIntervalo2 = new SimpleDateFormat("HH:mm", Locale.getDefault());
                final String horaIntervalo2 = timeFormatIntervalo2.format(intervalo2);
                campoHoraIntervaloDialog.setText(horaIntervalo2);
                Log.i("dialog", "segundo else if setHora");
            }if(novoMedicamento.campoIntervalo3.isFocused()){
                //pegando a hora que a pessoa selecionou do terceiro intervalo
                hora3 = calendar.getTime();
                medicamento.setIntervalo3(hora3);
                intervalo3 = medicamento.getIntervalo3();

                SimpleDateFormat timeFormatIntervalo3 = new SimpleDateFormat("HH:mm", Locale.getDefault());
                final String horaIntervalo3 = timeFormatIntervalo3.format(intervalo3);
                campoHoraIntervaloDialog.setText(horaIntervalo3);
                Log.i("dialog", "terceiro else if setHora");
            }




            /*Date hora = calendar.getTime();
            medicamento.setIntervalo(hora);
            intervalo = medicamento.getIntervalo();

            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
            String horaInicio = timeFormat.format(hora);

            //esse é o campo que vai mostrar a hora escolhida
            campoHoraIntervalos.setText(horaInicio);*/
        }
    }








    public Medicamento getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(Medicamento medicamento) {
        this.medicamento = medicamento;
    }
}
