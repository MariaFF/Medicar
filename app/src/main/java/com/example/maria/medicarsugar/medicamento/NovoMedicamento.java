package com.example.maria.medicarsugar.medicamento;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.maria.medicarsugar.R;
import com.example.maria.medicarsugar.modelo.Medicamento;

public class NovoMedicamento extends AppCompatActivity {
    private Medicamento medicamento;
    private EditText campoNome;
    private EditText campoDtVencimento;
    private EditText campoQtdeTotal;
    private EditText campoQtdeTomar;
    private EditText campodtInicio;
    private EditText campodtTermino;
    private EditText campoHoraInicio;
    private RadioGroup radioRetomavel;
    private EditText campoPrazoRetormar;
    private EditText campoIntervalo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_medicamento);

        this.campoNome = (EditText) findViewById(R.id.medicamento_nome);
        this.campoDtVencimento = (EditText) findViewById(R.id.medicamento_vencimento);
        this.campoQtdeTotal = (EditText) findViewById(R.id.medicamento_qtde_total);
        this.campoQtdeTomar = (EditText) findViewById(R.id.medicamento_qtde_tomar);
        this.campodtInicio = (EditText) findViewById(R.id.medicamento_dt_inicial);
        this.campodtTermino = (EditText) findViewById(R.id.medicamento_dt_final);
        this.campoHoraInicio = (EditText) findViewById(R.id.medicamento_hora_inicial);
        this.radioRetomavel = (RadioGroup) findViewById(R.id.medicamento_retomavel);
        this.campoPrazoRetormar= (EditText) findViewById(R.id.medicamento_prazo_retomar);
        this.campoIntervalo = (EditText) findViewById(R.id.medicamento_intervalo);

        medicamento = new Medicamento();
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
                    Intent intent = new Intent(getApplicationContext(), ListaMedicamento.class);
                    startActivity(intent);
                }
            finish();
             break;

        }

        return super.onOptionsItemSelected(item);
    }


    public Medicamento pegaMedicamento(){
        medicamento.setNome(campoNome.getText().toString());
        medicamento.setDtVencimento(campoDtVencimento.getText().toString());
        medicamento.setQtdeTotal(Integer.valueOf(campoQtdeTotal.getText().toString()));
        medicamento.setQtdeTomar(Double.valueOf((campoQtdeTomar.getText().toString())));
        medicamento.setDtInicio(campodtInicio.getText().toString());
        medicamento.setDtTermino(campodtTermino.getText().toString());
        medicamento.setHoraInicio(Integer.valueOf(campoHoraInicio.getText().toString()));
        verificarRb();
        medicamento.setPrazoRetomar(Double.valueOf(campoPrazoRetormar.getText().toString()));
        medicamento.setIntervalo(Integer.valueOf(campoIntervalo.getText().toString()));
        medicamento.setStatus(true);

        return medicamento;
    }

    public void preecheFormulario(Medicamento medicamento){
        campoNome.setText(medicamento.getNome());
        campoDtVencimento.setText(medicamento.getDtVencimento());
        campoQtdeTotal.setText(medicamento.getQtdeTotal());
        campoQtdeTomar.setText(medicamento.getQtdeTomar().toString());
        campodtInicio.setText(medicamento.getDtInicio());
        campodtTermino.setText(medicamento.getDtTermino());
        campoHoraInicio.setText(medicamento.getHoraInicio());
        campoPrazoRetormar.setText(medicamento.getPrazoRetomar().toString());
        campoIntervalo.setText(medicamento.getIntervalo());

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


}
