package com.example.maria.medicarsugar.config_caixa;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.maria.medicarsugar.R;
import com.example.maria.medicarsugar.adapter.MedicamentoAdapter;
import com.example.maria.medicarsugar.adapter.SpinnerMedicamento;
import com.example.maria.medicarsugar.adapter.SpinnerSom;
import com.example.maria.medicarsugar.modelo.Medicamento;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by maria on 13/09/2016.
 */
public class DialogConfigEspacoCaixa extends DialogFragment {

    private Medicamento medicamento;
    private List listaMedicamento = new ArrayList<>();
    private SpinnerMedicamento adapter;
    private SpinnerSom adapterSom;

    private List listaSom = new ArrayList();
    private Spinner spinnerMedicamento;
    private Spinner spinnerSom;
    private View view;



    private static final String LOG_TAG = "AudioRecordTest";
    private static String mFileName = null;

    private MediaRecorder mRecorder = null;
    private MediaPlayer mPlayer = null;
    boolean mStartRecording = true;
    boolean mStartPlaying = true;

    private ImageButton btMicrofone = null;
    private ImageButton btPlay = null;


    private Button a1;
    private ListView listaViewMedicamento;

    private LayoutCaixaFragment layoutCaixaFragment;


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_fragment_config, null);
        //medicamento = new Medicamento();

        //view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_layout_caixa, null);
        //a1 = (Button) view.findViewById(R.id.bt_a1);

        Log.i("DialogCaixa", "LayoutCaixa" +layoutCaixaFragment);


        btMicrofone = (ImageButton) view.findViewById(R.id.dialog_config_microfone);
        btMicrofone.setOnClickListener(gravarAudio());
        
        btPlay = (ImageButton) view.findViewById(R.id.dialog_config_play);
        btPlay.setOnClickListener(reproduzirAudio());


        listaViewMedicamento = (ListView) view.findViewById(R.id.medicamento_list_view);
        preencherEPersonalizarSpinnerMedicamento();
        spinnerMedicamento = (Spinner) view.findViewById(R.id.dialog_config_spinner_medicamento);
        spinnerMedicamento.setOnItemSelectedListener(medicamentoSelecionado());
        preencherEPersonalizarSpinnerSom();
        spinnerSom = (Spinner) view.findViewById(R.id.dialog_config_spinner_som);
        spinnerSom.setOnItemSelectedListener(reproduzirAudioSpinner());

        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity())
                .setTitle("Configurações")
                .setView(view)

                .setPositiveButton("Sim", new     DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                            medicamentoSelecionado();


                    }
                })
                .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mPlayer.stop();
                        dismiss();
                    }
                });

        Log.i("Dialog", "onCreateDialog");
        return alert.create();
    }

    private AdapterView.OnItemSelectedListener medicamentoSelecionado() {
        return new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                medicamento = (Medicamento) spinnerMedicamento.getItemAtPosition(position);

                //layoutCaixaFragment.getA1().setText(medicamento.getNome());




            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        };
    }



    /**
     *
     * Método que reproduz as músicas que estão pré-definidas no app
     */

    private AdapterView.OnItemSelectedListener reproduzirAudioSpinner() {
        return new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                TextView tv = (TextView) view;

                onPlay(mStartPlaying);
                switch (position){


                    case 0:

                        break;

                    case 1:
                        if(mStartPlaying) {
                            tv.setText("Faded");
                            mPlayer = MediaPlayer.create(getContext(), R.raw.faded);
                            mPlayer.start();

                        }

                        break;

                    case 2:
                        if (mStartPlaying) {
                            tv.setText("Cold Water");
                            mPlayer = MediaPlayer.create(getContext(), R.raw.cold_water);
                            mPlayer.start();
                        }

                        break;

                    case 3:
                        tv.setText("Don't let me down");
                        mPlayer = MediaPlayer.create(getContext(), R.raw.don_t_let_me_down);
                        mPlayer.start();

                        break;

                }
                mStartRecording = !mStartRecording;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {



            }
        };
    }


    private void preencherEPersonalizarSpinnerMedicamento() {


        new Thread(){
            public void run(){
                Log.i("Dialog", "Thread preencher");
                listaMedicamento = Medicamento.listAll(Medicamento.class);

                adapter = new SpinnerMedicamento(Medicamento.listAll(Medicamento.class), getActivity());
                spinnerMedicamento.setAdapter(adapter);

            }
        }.start();

    }


    private void preencherEPersonalizarSpinnerSom() {
        new Thread(){
            public void run(){


                    listaSom.add("Selecione o som");
                    listaSom.add(R.raw.faded);
                    listaSom.add(R.raw.cold_water);
                    listaSom.add(R.raw.don_t_let_me_down);


                ArrayAdapter spinnerAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, listaSom){
                    @Override
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
                        TextView tv = (TextView) view;


                        if(position == 0){
                            // Deixa o hint com a cor cinza ( efeito de desabilitado)
                            tv.setTextColor(Color.GRAY);

                        }else if(position == 1) {
                            tv.setTextColor(Color.BLACK);
                            tv.setText("Faded");


                        }else if(position == 2){
                            tv.setTextColor(Color.BLACK);
                            tv.setText("Cold Water");
                        }else if(position == 3){
                            tv.setTextColor(Color.BLACK);
                            tv.setText("Don't let me down");
                        }

                        return view;
                    }
                };
                spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerSom.setAdapter(spinnerAdapter);
            }
        }.start();

    }



    public DialogConfigEspacoCaixa() {
        mFileName = Environment.getExternalStorageDirectory().getAbsolutePath();
        mFileName += "/audiorecordtest.3gp";
    }

    /**
     *
     * Método que captura o áudio que a pessoa gravar
     */

    private View.OnClickListener gravarAudio() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRecord(mStartRecording);
                if (mStartRecording) {
                    btMicrofone.setImageResource(R.drawable.ic_stop_black);
                } else {
                    btMicrofone.setImageResource(R.drawable.microphone);
                }
                mStartRecording = !mStartRecording;
            }
        };
    }

    /**
     * Método que reproduz o áudio gravado
     *
     */

    private View.OnClickListener reproduzirAudio() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPlay(mStartPlaying);
                if (mStartPlaying) {
                    btPlay.setImageResource(R.drawable.ic_stop_black);
                } else {
                    btPlay.setImageResource(R.drawable.ic_play_black);
                }
                mStartPlaying = !mStartPlaying;
            }
        };
    }


    public void onRecord(boolean start) {
        if (start) {
            startRecording();
        } else {
            stopRecording();
        }
    }

    public void onPlay(boolean start) {
        if (start) {
            startPlaying();
        } else {
            stopPlaying();
        }
    }


    public void startPlaying() {
        mPlayer = new MediaPlayer();
        try {
            mPlayer.setDataSource(mFileName);
            mPlayer.prepare();
            mPlayer.start();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }
    }

    public void stopPlaying() {
        mPlayer.release();
        mPlayer = null;
    }

    public void startRecording() {
        mRecorder = new MediaRecorder();
        mRecorder.reset();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mRecorder.setOutputFile(mFileName);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try {
            mRecorder.prepare();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }

        mRecorder.start();
    }

    public void stopRecording() {
        mRecorder.stop();
        mRecorder.release();
        mRecorder = null;
    }


    @Override
    public void onPause() {
        super.onPause();
        if (mRecorder != null) {
            mRecorder.release();
            mRecorder = null;
        }

        if (mPlayer != null) {
            mPlayer.release();
            mPlayer = null;
        }
    }


}
