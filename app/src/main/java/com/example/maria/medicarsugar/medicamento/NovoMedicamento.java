package com.example.maria.medicarsugar.medicamento;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
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

import com.example.maria.medicarsugar.R;
import com.example.maria.medicarsugar.modelo.Medicamento;
import com.example.maria.medicarsugar.util.Mask;
import com.mikepenz.iconics.utils.Utils;


import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class NovoMedicamento extends AppCompatActivity{
    private Medicamento medicamento;

    private EditText campoNome;
    private EditText campoDtInicio;
    private EditText campoQtdeTotal;

    private EditText campoDtVencimento;

    private RadioGroup radioRetomavel;
    private EditText campoPrazoRetormar;

    private RadioButton rb_sim;
    private RadioButton rb_nao;

    private TextView tvIntervalo1;
    private TextView tvIntervalo2;
    private TextView tvIntervalo3;

    //EDIT
    EditText campoIntervalo1;
    EditText campoIntervalo2;
    EditText campoIntervalo3;

    private Spinner spinnerIntervalo;
    private List intervalos = new ArrayList();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_medicamento);

        medicamento = new Medicamento();
        recuperarComponentes();

        radioRetomavel.check(R.id.medicamento_retomavel_sim);

        campoIntervalo1.setOnClickListener(abrirDialogParaInformarHoraEQtde());
        campoIntervalo2.setOnClickListener(abrirDialogParaInformarHoraEQtde());
        campoIntervalo3.setOnClickListener(abrirDialogParaInformarHoraEQtde());



        setarVisibilidadeCampos(View.INVISIBLE);

        spinnerIntervalo = (Spinner) findViewById(R.id.spinner_intervalo);
        spinnerIntervalo.setOnItemSelectedListener(onOpEscolhidaSpinner());
        criarIntervalosPreDefinidos();
        preencherEPersonalizarSpinner();

        if(getIntent().hasExtra("idMedicamento")){
            Bundle bundle = getIntent().getExtras();
            Long id = bundle.getLong("idMedicamento");
            medicamento = (Medicamento) Medicamento.findById(Medicamento.class, id);
            preencheFormularioMedicamento(medicamento);
        }

    //FINAL DO ONCREATE
    }

    private void preencheFormularioMedicamento(Medicamento medicamento) {
        campoNome.setText(medicamento.getNome().toString());
        verificarCampoPreenchidoParaPreencherForm(medicamento);


    }

    /**
     *
     * @param medicamento
     * Método responssavel por ver qual intervalo está preenchido e assim tornar o campo visivel
     * e setar as informações de hora e dose daquele intervalo
     */
    public void verificarCampoPreenchidoParaPreencherForm(Medicamento medicamento) {
        if(medicamento.getIntervalo1() != null) {
            campoIntervalo1.setVisibility(View.VISIBLE);

            Date intervalo1 = medicamento.getIntervalo1();

            SimpleDateFormat timeFormatIntervalo1 = new SimpleDateFormat("HH:mm", Locale.getDefault());
            final String horaIntervalo1 = timeFormatIntervalo1.format(intervalo1);

            DecimalFormat format = new DecimalFormat("0.#");
            String sDose1 = format.format(medicamento.getDose1());
            campoIntervalo1.setText("Hora: " + horaIntervalo1 + " - " + "Dose: " + sDose1);
        }
        if(medicamento.getIntervalo2() != null){
            campoIntervalo2.setVisibility(View.VISIBLE);

            Date intervalo2 = medicamento.getIntervalo2();

            SimpleDateFormat timeFormatIntervalo2 = new SimpleDateFormat("HH:mm", Locale.getDefault());
            final String horaIntervalo2 = timeFormatIntervalo2.format(intervalo2);

            DecimalFormat format = new DecimalFormat("0.#");
            String sDose2 = format.format(medicamento.getDose2());
            campoIntervalo2.setText("Hora: " + horaIntervalo2 + " - " + "Dose: " + sDose2);
        }
        if(medicamento.getIntervalo3() != null){
            campoIntervalo3.setVisibility(View.VISIBLE);

            Date intervalo3 = medicamento.getIntervalo3();

            SimpleDateFormat timeFormatIntervalo3 = new SimpleDateFormat("HH:mm", Locale.getDefault());
            final String horaIntervalo3 = timeFormatIntervalo3.format(intervalo3);

            DecimalFormat format = new DecimalFormat("0.#");
            String sDose3 = format.format(medicamento.getDose3());
            campoIntervalo3.setText("Hora: " + horaIntervalo3 + " - " + "Dose: " + sDose3);
        }
    }

    /**
     *
     * @param invisible
     * Campos para informar a quantidade iniciam invisiveis
     */
    private void setarVisibilidadeCampos(int invisible) {
        tvIntervalo1.setVisibility(invisible);
        campoIntervalo1.setVisibility(invisible);
        tvIntervalo2.setVisibility(invisible);
        campoIntervalo2.setVisibility(invisible);
        tvIntervalo3.setVisibility(invisible);
        campoIntervalo3.setVisibility(invisible);
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
        this.campoPrazoRetormar= (EditText) findViewById(R.id.medicamento_prazo_retomar);

        rb_sim = (RadioButton) findViewById(R.id.medicamento_retomavel_sim);
        rb_nao = (RadioButton) findViewById(R.id.medicamento_retomavel_nao);

        tvIntervalo1 = (TextView) findViewById(R.id.tv_intervalo1);
        tvIntervalo2 = (TextView) findViewById(R.id.tv_intervalo2);
        tvIntervalo3 = (TextView) findViewById(R.id.tv_intervalo3);

        campoIntervalo1 = (EditText) findViewById(R.id.ed_intervalo1);
        campoIntervalo2 = (EditText) findViewById(R.id.ed_intervalo2);
        campoIntervalo3 = (EditText) findViewById(R.id.ed_intervalo3);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_medicamento, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){

            case(R.id.menu_medicamento_salvar):
                pegaMedicamento();
                if(medicamento.getId() != null){
                    if(!TextUtils.isEmpty(campoNome.getText().toString())) {
                        medicamento.save();
                        Log.i("NovoMedicamento", "Radio Bt: " + medicamento.getRetomavel());
                        Toast.makeText(NovoMedicamento.this, "Alterado", Toast.LENGTH_SHORT).show();
                        finish();

                    }else {
                        campoNome.requestFocus();
                        campoNome.setError("Por favor, preencha o campo nome");

                    }
                // SALVAR
                }else{
                    if(!TextUtils.isEmpty(campoNome.getText().toString())) {
                        medicamento.save();
                        Log.i("NovoMedicamento", "Radio Bt: " + medicamento.getRetomavel());
                        Toast.makeText(NovoMedicamento.this, "Salvo", Toast.LENGTH_SHORT).show();
                        finish();

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

                if (itemString.equals("Uma vez ao dia")) {
                    tvIntervalo2.setVisibility(View.INVISIBLE);
                    campoIntervalo2.setVisibility(View.INVISIBLE);
                    tvIntervalo3.setVisibility(View.INVISIBLE);
                    campoIntervalo3.setVisibility(View.INVISIBLE);
                    tvIntervalo1.setVisibility(View.VISIBLE);
                    campoIntervalo1.setVisibility(View.VISIBLE);

                    //campoIntervalo1.setFocusable(true);
                    //bCampoIntervalo1 = true;

                } else if (itemString.equals("Duas vezes ao dia")) {
                    tvIntervalo1.setVisibility(View.VISIBLE);
                    campoIntervalo1.setVisibility(View.VISIBLE);

                    tvIntervalo2.setVisibility(View.VISIBLE);
                    campoIntervalo2.setVisibility(View.VISIBLE);

                    tvIntervalo3.setVisibility(View.INVISIBLE);
                    campoIntervalo3.setVisibility(View.INVISIBLE);

                } else if (itemString.equals("Três vezes ao dia")) {
                    tvIntervalo1.setVisibility(View.VISIBLE);
                    campoIntervalo1.setVisibility(View.VISIBLE);

                    tvIntervalo2.setVisibility(View.VISIBLE);
                    campoIntervalo2.setVisibility(View.VISIBLE);

                    tvIntervalo3.setVisibility(View.VISIBLE);
                    campoIntervalo3.setVisibility(View.VISIBLE);

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
    private void preencherEPersonalizarSpinner() {
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

        intervalos.add("Intervalos");
        intervalos.add("A cada 12 horas");
        intervalos.add("A cada 8 horas");
        intervalos.add("A cada 6 horas");
    }



    public Medicamento pegaMedicamento(){
        medicamento.setNome(campoNome.getText().toString());
        verificarRadioButton();
        medicamento.setPrazoRetomar(campoPrazoRetormar.getText().toString());
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


    public void verificarRb() {

        if (rb_sim.isChecked()) {
            if (campoPrazoRetormar.getText().toString().isEmpty()) {
                campoPrazoRetormar.requestFocus();
                campoPrazoRetormar.setError("Por favor, informe o prazo para retormar o medicamento");
            } else
                medicamento.setRetomavel(true);
        } else if (rb_nao.isChecked()) {

            medicamento.setRetomavel(false);
        }
    }
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
