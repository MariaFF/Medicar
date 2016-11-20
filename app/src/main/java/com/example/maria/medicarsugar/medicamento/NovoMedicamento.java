package com.example.maria.medicarsugar.medicamento;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.maria.medicarsugar.ListaVazia.FragmentListaVazia;
import com.example.maria.medicarsugar.MainActivity;
import com.example.maria.medicarsugar.R;
import com.example.maria.medicarsugar.adapter.SpinnerMedicamento;
import com.example.maria.medicarsugar.adapter.SpinnerMedico;
import com.example.maria.medicarsugar.fragments.ListaMedicamentoFragment;
import com.example.maria.medicarsugar.modelo.Medicamento;
import com.example.maria.medicarsugar.modelo.Medico;
import com.example.maria.medicarsugar.util.Mask;
import com.mikepenz.iconics.utils.Utils;


import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class NovoMedicamento extends AppCompatActivity {
    private Medicamento medicamento;

    private EditText campoNome;
    private EditText campoDtInicio;
    private EditText campoQtdeTotal;

    private EditText campoDtVencimento;

    private RadioGroup radioRetomavel;
    private Spinner spinnerPrazoRetormar;
    private List prazos = new ArrayList();

    private RadioButton rb_sim;
    private RadioButton rb_nao;

    private TextView tvIntervalo1;
    private TextView tvIntervalo2;
    private TextView tvIntervalo3;
    private TextView tvIntervalo4;

    //EDIT
    EditText campoIntervalo1;
    EditText campoIntervalo2;
    EditText campoIntervalo3;
    EditText campoIntervalo4;

    private Spinner spinnerIntervalo;
    private List intervalos = new ArrayList();

    private List<Medicamento> listarMedicamentos;
    private SpinnerMedico spinnerMedicoAdapter;
    private Spinner spinnerMedico;

    private MainActivity main;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_medicamento);

        //preencherMedicoQueReceitouMedicamento();
        setTitle("Nome medicamento");
        medicamento = new Medicamento();
        recuperarComponentes();

        Log.i("med", "valor da main " + main);

        radioRetomavel.check(R.id.medicamento_retomavel_sim);
        criarPrazos();
        preencherSpinnerPrazoRetomar();
        spinnerPrazoRetormar.setOnItemSelectedListener(onOpEscolhidaSpinnerPrazos());

        campoIntervalo1.setOnClickListener(abrirDialogParaInformarHoraEQtde());
        campoIntervalo2.setOnClickListener(abrirDialogParaInformarHoraEQtde());
        campoIntervalo3.setOnClickListener(abrirDialogParaInformarHoraEQtde());
        campoIntervalo4.setOnClickListener(abrirDialogParaInformarHoraEQtde());

        setarVisibilidadeCampos(View.INVISIBLE);

        spinnerIntervalo = (Spinner) findViewById(R.id.spinner_intervalo);
        spinnerIntervalo.setOnItemSelectedListener(onOpEscolhidaSpinner());
        criarIntervalosPreDefinidos();
        preencherEPersonalizarSpinnerIntervalos();

        if (getIntent().hasExtra("idMedicamento")) {
            Bundle bundle = getIntent().getExtras();
            Long id = bundle.getLong("idMedicamento");
            medicamento = (Medicamento) Medicamento.findById(Medicamento.class, id);
            preencheFormularioMedicamento(medicamento);
        }

        listarMedicamentos = Medicamento.findWithQuery(Medicamento.class, "Select * from Medicamento " +
                "where status is ? ", "1");

        //FINAL DO ONCREATE
    }


    private void preencheFormularioMedicamento(Medicamento medicamento) {
        campoNome.setText(medicamento.getNome().toString());
        verificarCampoPreenchidoParaPreencherForm(medicamento);


    }

    /**
     * @param medicamento Método responssavel por ver qual intervalo está preenchido e assim tornar o campo visivel
     *                    e setar as informações de hora e dose daquele intervalo
     */
    public void verificarCampoPreenchidoParaPreencherForm(Medicamento medicamento) {
        if (medicamento.getIntervalo1() != null) {
            campoIntervalo1.setVisibility(View.VISIBLE);

            Date intervalo1 = medicamento.getIntervalo1();

            SimpleDateFormat timeFormatIntervalo1 = new SimpleDateFormat("HH:mm", Locale.getDefault());
            final String horaIntervalo1 = timeFormatIntervalo1.format(intervalo1);

            DecimalFormat format = new DecimalFormat("0.#");
            String sDose1 = format.format(medicamento.getDose1());
            campoIntervalo1.setText("Hora: " + horaIntervalo1 + " - " + "Dose: " + sDose1);
        }
        if (medicamento.getIntervalo2() != null) {
            campoIntervalo2.setVisibility(View.VISIBLE);

            Date intervalo2 = medicamento.getIntervalo2();

            SimpleDateFormat timeFormatIntervalo2 = new SimpleDateFormat("HH:mm", Locale.getDefault());
            final String horaIntervalo2 = timeFormatIntervalo2.format(intervalo2);

            DecimalFormat format = new DecimalFormat("0.#");
            String sDose2 = format.format(medicamento.getDose2());
            campoIntervalo2.setText("Hora: " + horaIntervalo2 + " - " + "Dose: " + sDose2);
        }
        if (medicamento.getIntervalo3() != null) {
            campoIntervalo3.setVisibility(View.VISIBLE);

            Date intervalo3 = medicamento.getIntervalo3();

            SimpleDateFormat timeFormatIntervalo3 = new SimpleDateFormat("HH:mm", Locale.getDefault());
            final String horaIntervalo3 = timeFormatIntervalo3.format(intervalo3);

            DecimalFormat format = new DecimalFormat("0.#");
            String sDose3 = format.format(medicamento.getDose3());
            campoIntervalo3.setText("Hora: " + horaIntervalo3 + " - " + "Dose: " + sDose3);
        }
        if (medicamento.getIntervalo4() != null) {
            campoIntervalo4.setVisibility(View.VISIBLE);
            Date intervalo4 = medicamento.getIntervalo4();

            SimpleDateFormat timeFormatIntervalo4 = new SimpleDateFormat("HH:mm", Locale.getDefault());
            final String horaIntervalo4 = timeFormatIntervalo4.format(intervalo4);

            DecimalFormat format = new DecimalFormat("0.#");
            String sDose4 = format.format(medicamento.getDose4());
            campoIntervalo4.setText("Hora: " + horaIntervalo4 + " - " + "Dose: " + sDose4);

        }
    }

    /**
     * @param invisible Campos para informar a quantidade iniciam invisiveis
     */
    private void setarVisibilidadeCampos(int invisible) {
        tvIntervalo1.setVisibility(invisible);
        campoIntervalo1.setVisibility(invisible);
        tvIntervalo2.setVisibility(invisible);
        campoIntervalo2.setVisibility(invisible);
        tvIntervalo3.setVisibility(invisible);
        campoIntervalo3.setVisibility(invisible);
        tvIntervalo4.setVisibility(invisible);
        campoIntervalo4.setVisibility(invisible);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_medicamento, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void trocaTela() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction tx = fragmentManager.beginTransaction();

        if (listarMedicamentos.size() == 0) {
            Log.i("main", "3 if case");
            tx.replace(R.id.main_frame, new FragmentListaVazia());
            tx.commit();

        } else {
            Log.i("main", "3 else case");
            tx.replace(R.id.main_frame, new ListaMedicamentoFragment());
            tx.commit();
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){

            case(R.id.menu_medicamento_salvar):
                pegaMedicamento();
                if(medicamento.getId() != null){
                    if(!TextUtils.isEmpty(campoNome.getText().toString())) {
                        if(!medicamento.getPrazoRetomar().equals("Selecione o prazo")) {
                            medicamento.save();
                            Log.i("NovoMedicamento", "Radio Bt: " + medicamento.getRetomavel());
                            Toast.makeText(NovoMedicamento.this, "Alterado", Toast.LENGTH_SHORT).show();
                            finish();
                        }else {
                            TextView errorText = (TextView)spinnerPrazoRetormar.getSelectedView();
                            errorText.setError("");
                            errorText.setTextColor(Color.RED);//just to highlight that this is an error
                            errorText.setText("Por favor, selecione uma opção");//changes the selected item text to this
                        }

                    }else {
                        campoNome.requestFocus();
                        campoNome.setError("Por favor, preencha o campo nome");

                    }
                // SALVAR
                }else{
                    if(!TextUtils.isEmpty(campoNome.getText().toString())) {
                        if(!medicamento.getPrazoRetomar().equals("Selecione o prazo")) {
                            estimarDuracaoMedicamento();
                            medicamento.save();
                            Log.i("main", "Qtde depois do save : " + medicamento.getQtdeRestante());
                            Toast.makeText(NovoMedicamento.this, "Salvo", Toast.LENGTH_SHORT).show();
                            finish();
                            //trocaTela();
                            /*
                            FragmentManager fragmentManager = getSupportFragmentManager();
                            FragmentTransaction tx = fragmentManager.beginTransaction();
                            tx.replace(R.id.frame_lista_med_vazia, new ListaMedicamentoFragment());
                            tx.commit();*/
                        }else{
                            TextView errorText = (TextView)spinnerPrazoRetormar.getSelectedView();
                            errorText.setError("");
                            errorText.setTextColor(Color.RED);//just to highlight that this is an error
                            errorText.setText("Por favor, selecione uma opção");//changes the selected item text to this
                        }


                    }else {
                        campoNome.requestFocus();
                        campoNome.setError("Por favor, preencha o campo nome");
                    }
                }


            break;

            case R.id.menu_medicamento_cancelar:
                finish();
            break;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     *
     * Método que é chamado de acordo com o campo criado dinamicamente, esse dialog chama outro dialogHora/dialogQtde a partir
     * de um EditText
     */
    private View.OnClickListener abrirDialogParaInformarHoraEQtde() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                DialogIntervaloMedicamento dialogIntervalo = new DialogIntervaloMedicamento();
                dialogIntervalo.setMedicamento(medicamento);
                dialogIntervalo.show(fragmentTransaction, "dialog");
            }
        };
    }

    /**
     * Listener do Spinner, de acordo com a opção escolhida no spinner a quantidade de campos será criada
     * **/
    private AdapterView.OnItemSelectedListener onOpEscolhidaSpinner() {
        return new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String itemString = parent.getItemAtPosition(position).toString();

                Calendar c = Calendar.getInstance();
                SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
                String horaAtual = timeFormat.format(c.getTime());
                String horaSomada = timeFormat.format(c.getTime());

                if (itemString.equals("Uma vez ao dia")) {
                    tvIntervalo2.setVisibility(View.INVISIBLE);
                    campoIntervalo2.setVisibility(View.INVISIBLE);
                    tvIntervalo3.setVisibility(View.INVISIBLE);
                    campoIntervalo3.setVisibility(View.INVISIBLE);
                    tvIntervalo1.setVisibility(View.VISIBLE);
                    campoIntervalo1.setVisibility(View.VISIBLE);
                    //Calendar c = Calendar.getInstance();
                    //SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());

                    campoIntervalo1.setText(horaAtual);

                } else if (itemString.equals("Duas vezes ao dia")) {
                    tvIntervalo1.setVisibility(View.VISIBLE);
                    campoIntervalo1.setVisibility(View.VISIBLE);

                    tvIntervalo2.setVisibility(View.VISIBLE);
                    campoIntervalo2.setVisibility(View.VISIBLE);

                    tvIntervalo3.setVisibility(View.INVISIBLE);
                    campoIntervalo3.setVisibility(View.INVISIBLE);


                    campoIntervalo1.setText(horaAtual);
                    c.add(Calendar.HOUR_OF_DAY, 12);
                    horaSomada = timeFormat.format(c.getTime());
                    campoIntervalo2.setText(horaSomada);


                } else if (itemString.equals("Três vezes ao dia")) {
                    tvIntervalo1.setVisibility(View.VISIBLE);
                    campoIntervalo1.setVisibility(View.VISIBLE);

                    tvIntervalo2.setVisibility(View.VISIBLE);
                    campoIntervalo2.setVisibility(View.VISIBLE);

                    tvIntervalo3.setVisibility(View.VISIBLE);
                    campoIntervalo3.setVisibility(View.VISIBLE);

                    campoIntervalo1.setText(horaAtual);
                    c.add(Calendar.HOUR_OF_DAY, 8);
                    horaSomada = timeFormat.format(c.getTime() );
                    campoIntervalo2.setText(horaSomada);

                    c.add(Calendar.HOUR_OF_DAY, 8);
                    horaSomada = timeFormat.format(c.getTime() );
                    campoIntervalo3.setText(horaSomada);

                }else if(itemString.equals("Quatro vezes ao dia")){
                    tvIntervalo4.setVisibility(View.VISIBLE);
                    campoIntervalo4.setVisibility(View.VISIBLE);

                    tvIntervalo1.setVisibility(View.VISIBLE);
                    campoIntervalo1.setVisibility(View.VISIBLE);

                    tvIntervalo2.setVisibility(View.VISIBLE);
                    campoIntervalo2.setVisibility(View.VISIBLE);

                    tvIntervalo3.setVisibility(View.VISIBLE);
                    campoIntervalo3.setVisibility(View.VISIBLE);


                    campoIntervalo1.setText(horaAtual);

                    c.add(Calendar.HOUR_OF_DAY, 6);
                    horaSomada = timeFormat.format(c.getTime() );
                    campoIntervalo2.setText(horaSomada);

                    c.add(Calendar.HOUR_OF_DAY, 6);
                    horaSomada = timeFormat.format(c.getTime() );
                    campoIntervalo3.setText(horaSomada);

                    c.add(Calendar.HOUR_OF_DAY, 6);
                    horaSomada = timeFormat.format(c.getTime() );
                    campoIntervalo4.setText(horaSomada);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
    }

    /**
     * Preenche o spinner de intervalos e usa um layout pronto do android para personaliza-lo
     */
    private void preencherEPersonalizarSpinnerIntervalos() {
        ArrayAdapter spinnerAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, intervalos);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerIntervalo.setAdapter(spinnerAdapter);
    }

    /**
     * Criar SpinnerIntervalo da frequencia dos medicamentos, essas informações preenche o spinner
     * **/
    private void criarIntervalosPreDefinidos() {
        intervalos.add("Frequencia");
        intervalos.add("Uma vez ao dia");
        intervalos.add("Duas vezes ao dia");
        intervalos.add("Três vezes ao dia");
        intervalos.add("Quatro vezes ao dia");

    }


    private void preencherSpinnerPrazoRetomar() {
        ArrayAdapter spinner = new ArrayAdapter(this, android.R.layout.simple_spinner_item, prazos){
            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {

                View view = super.getDropDownView(position, convertView, parent);

                TextView tv = (TextView) view;

                if (position == 0) {

                    // Deixa o hint com a cor cinza ( efeito de desabilitado)
                    tv.setText("Selecione o prazo");
                    tv.setTextColor(Color.GRAY);

                } else {
                    tv.setTextColor(Color.BLACK);

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


        };
        spinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPrazoRetormar.setAdapter(spinner);

    }

    private void criarPrazos() {
        prazos.add("Selecione o prazo");
        prazos.add("Não há prazo");
        prazos.add("5 minutos");
        prazos.add("10 minutos");
        prazos.add("15 minutos");
        prazos.add("20 minutos");
    }

    private AdapterView.OnItemSelectedListener onOpEscolhidaSpinnerPrazos() {
        return new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                medicamento.setPrazoRetomar(parent.getItemAtPosition(position).toString());
                Log.i("NovoMed", "Prazo Spinner" +medicamento.getPrazoRetomar());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
    }


    /**
     * Recuperando todos os componentes da activity
     */
    private void recuperarComponentes() {
        this.campoNome = (EditText) findViewById(R.id.medicamento_nome);
        campoDtInicio = (EditText) findViewById(R.id.medicamento_ed_dt_inicial);
        campoDtInicio.addTextChangedListener(Mask.insert("##/##/####", campoDtInicio));

        campoDtVencimento = (EditText) findViewById(R.id.medicamento_ed_dt_vencimento);
        campoDtVencimento.addTextChangedListener(Mask.insert("##/##/####", campoDtVencimento));

        campoQtdeTotal = (EditText) findViewById(R.id.medicamento_qtde_total);

        this.radioRetomavel = (RadioGroup) findViewById(R.id.medicamento_retomavel);
        this.spinnerPrazoRetormar= (Spinner) findViewById(R.id.medicamento_spinner_prazo_retormar);

        rb_sim = (RadioButton) findViewById(R.id.medicamento_retomavel_sim);
        rb_nao = (RadioButton) findViewById(R.id.medicamento_retomavel_nao);

        tvIntervalo1 = (TextView) findViewById(R.id.tv_intervalo1);
        tvIntervalo2 = (TextView) findViewById(R.id.tv_intervalo2);
        tvIntervalo3 = (TextView) findViewById(R.id.tv_intervalo3);
        tvIntervalo4 = (TextView) findViewById(R.id.tv_intervalo4);

        campoIntervalo1 = (EditText) findViewById(R.id.ed_intervalo1);
        campoIntervalo2 = (EditText) findViewById(R.id.ed_intervalo2);
        campoIntervalo3 = (EditText) findViewById(R.id.ed_intervalo3);
        campoIntervalo4 = (EditText) findViewById(R.id.ed_intervalo4);

        //spinnerMedico = (Spinner) findViewById(R.id.spinner_medico);

    }


    public void preencherMedicoQueReceitouMedicamento(){


        new Thread() {
            public void run() {
                //List<Medico> listaMedico = Medico.listAll(Medico.class);
                spinnerMedicoAdapter = new SpinnerMedico(Medico.listAll(Medico.class), getParent());
                spinnerMedico.setAdapter(spinnerMedicoAdapter);
            }
        }.start();
    }

    public Medicamento pegaMedicamento(){
        medicamento.setNome(campoNome.getText().toString());
        verificarRadioButton();
        medicamento.setQtdeTotal(Double.valueOf(campoQtdeTotal.getText().toString()));
        //medicamento.setPrazoRetomar(spinnerPrazoRetormar.getText().toString());
        medicamento.setStatus(true);

        return medicamento;
    }

    public void verificarRadioButton(){
        switch (radioRetomavel.getCheckedRadioButtonId()){
            case R.id.medicamento_retomavel_sim:
                medicamento.setRetomavel(true);
                break;
            case R.id.medicamento_retomavel_nao:
                medicamento.setRetomavel(false);
                break;
        }
    }




    public void estimarDuracaoMedicamento() {

        new Thread() {
            public void run() {

                Double qtdeRestanteDias = null;
                //quantidade total de comprimidos
                //Double qtdeTotal = medicamento.getQtdeTotal();

                Double qtdeMedTomadoDia = null;

                //qtdeTomadaDia = 1 + 1 + 1;
                //3 é a qtde tomada por dia

                //qtdeTotal / qtdeTomadaDia = Quantos dias vai durar

                //se ele tomar 4 doses, ta tirando todas as 4 doses da qtdeTotal,
                if (medicamento.getDose4() != null) {
                    qtdeMedTomadoDia = medicamento.getDose1() + medicamento.getDose2() + medicamento.getDose3() + medicamento.getDose4();
                    qtdeRestanteDias = medicamento.getQtdeTotal() / qtdeMedTomadoDia;
                    medicamento.setQtdeRestante(String.valueOf(qtdeRestanteDias));
                } else if (medicamento.getDose3() != null) {
                    qtdeMedTomadoDia = medicamento.getDose1() + medicamento.getDose2() + medicamento.getDose3();
                    qtdeRestanteDias = medicamento.getQtdeTotal() / qtdeMedTomadoDia;
                    medicamento.setQtdeRestante(String.valueOf(qtdeRestanteDias));
                } else if (medicamento.getDose2() != null) {
                    qtdeMedTomadoDia = medicamento.getDose1() + medicamento.getDose2();
                    qtdeRestanteDias = medicamento.getQtdeTotal() / qtdeMedTomadoDia;
                    medicamento.setQtdeRestante(String.valueOf(qtdeRestanteDias));
                    Log.i("main", "Qtde restante em dias" +qtdeRestanteDias);
                } else if (medicamento.getDose1() != null) {
                    qtdeMedTomadoDia = medicamento.getDose1();
                    Log.i("main", "Dose1" + medicamento.getDose1());
                    Log.i("main", "Qtde total" + medicamento.getQtdeTotal());
                    qtdeRestanteDias = medicamento.getQtdeTotal() / medicamento.getDose1();
                    medicamento.setQtdeRestante(String.valueOf(qtdeRestanteDias));
                    Log.i("main", "qtde restante" + qtdeRestanteDias);
                }


            }
        }.start();

    }


    //}
/*
    private void iniciarData(){
        if (ano == 0){
            Calendar c = Calendar.getInstance();
            ano = c.get(Calendar.YEAR);
            mes = c.get(Calendar.MONTH);
            dia = c.get(Calendar.DAY_OF_MONTH);
            hora = c.get(Calendar.HOUR_OF_DAY);
            minuto = c.get(Calendar.MINUTE);
        }
    }
*/



}
