package com.example.maria.medicarsugar.medicamento;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class NovoMedicamento extends AppCompatActivity{
    private Medicamento medicamento;
    private EditText campoNome;

    private RadioGroup radioRetomavel;
    private EditText campoPrazoRetormar;


    private TextView tvIntervalo1;
    private TextView tvIntervalo2;
    private TextView tvIntervalo3;

    EditText campoIntervalo1;
    EditText campoIntervalo2;
    EditText campoIntervalo3;

    boolean bCampoIntervalo1 = false;
    boolean bCampoIntervalo2 = false;
    boolean bCampoIntervalo3 = false;

    private Button btSalvar;

    private Spinner spinnerIntervalo;
    private List intervalos = new ArrayList();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_medicamento);

        medicamento = new Medicamento();
        this.campoNome = (EditText) findViewById(R.id.medicamento_nome);
        this.radioRetomavel = (RadioGroup) findViewById(R.id.medicamento_retomavel);
        this.campoPrazoRetormar= (EditText) findViewById(R.id.medicamento_prazo_retomar);

        tvIntervalo1 = (TextView) findViewById(R.id.tv_intervalo1);
        tvIntervalo2 = (TextView) findViewById(R.id.tv_intervalo2);
        tvIntervalo3 = (TextView) findViewById(R.id.tv_intervalo3);

        campoIntervalo1 = (EditText) findViewById(R.id.ed_intervalo1);
        campoIntervalo2 = (EditText) findViewById(R.id.ed_intervalo2);
        campoIntervalo3 = (EditText) findViewById(R.id.ed_intervalo3);

        campoIntervalo1.setOnClickListener(abrirDialogParaInformarHoraEQtde());
        campoIntervalo2.setOnClickListener(abrirDialogParaInformarHoraEQtde());
        campoIntervalo3.setOnClickListener(abrirDialogParaInformarHoraEQtde());

        tvIntervalo1.setVisibility(View.INVISIBLE);
        campoIntervalo1.setVisibility(View.INVISIBLE);
        tvIntervalo2.setVisibility(View.INVISIBLE);
        campoIntervalo2.setVisibility(View.INVISIBLE);
        tvIntervalo3.setVisibility(View.INVISIBLE);
        campoIntervalo3.setVisibility(View.INVISIBLE);

        spinnerIntervalo = (Spinner) findViewById(R.id.spinner_intervalo);
        spinnerIntervalo.setOnItemSelectedListener(onOpEscolhidaSpinner());
        criarIntervalosPreDefinidos();
        preencherEPersonalizarSpinner();


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
                    medicamento.save();
                    Toast.makeText(NovoMedicamento.this, "Alterado com sucesso", Toast.LENGTH_SHORT).show();
                }else{
                    medicamento.save();
                    Toast.makeText(NovoMedicamento.this, "Salvo com sucesso", Toast.LENGTH_SHORT).show();
                    //Intent intent = new Intent(getApplicationContext(), ListaMedicamentoFragment.class);
                    //startActivity(intent);
                }
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
        verificarRb();
        medicamento.setPrazoRetomar(campoPrazoRetormar.getText().toString());
        medicamento.setStatus(true);

        return medicamento;
    }

    public void preecheFormulario(Medicamento medicamento){
        campoNome.setText(medicamento.getNome());
        campoPrazoRetormar.setText(medicamento.getPrazoRetomar().toString());

    }

    public void verificarRb(){
        RadioButton rb_sim = (RadioButton) findViewById(R.id.medicamento_retomavel_sim);
        RadioButton rb_nao = (RadioButton) findViewById(R.id.medicamento_retomavel_nao);


        if(rb_sim.isChecked()) {
            medicamento.setRetomavel(true);
        }else if(rb_nao.isChecked()) {
            medicamento.setRetomavel(false);

        }else {
            radioRetomavel.requestFocus();
            rb_sim.setError("Por favor, selecione uma das opções");
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
