package com.example.maria.medicarsugar.medico;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.maria.medicarsugar.R;
import com.example.maria.medicarsugar.fragments.ListaMedicoFragment;
import com.example.maria.medicarsugar.modelo.Medico;
import com.example.maria.medicarsugar.receita.NovaReceita;

import java.util.ArrayList;
import java.util.List;

public class NovoMedico extends AppCompatActivity {

    private Medico medico;
    private EditText campoNome;
    private EditText campoTelefone1;
    private EditText campoTelefone2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_medico);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.setTitle("Novo Médico");
        this.campoNome = (EditText) findViewById(R.id.medico_edt_nome);
        this.campoTelefone1 = (EditText) findViewById(R.id.medico_edt_telefone1);
        this.campoTelefone2 = (EditText) findViewById(R.id.medico_edt_telefone2);
        medico = new Medico();

        if(getIntent().hasExtra("idMedico")){
            Bundle bundle = getIntent().getExtras();
            Long id = bundle.getLong("idMedico");
            medico = (Medico) Medico.findById(Medico.class, id);
            preencheFormulario(medico);
        }

        /*Intent intent = getIntent();
        Medico medico = (Medico) intent.getSerializableExtra("medico");
        if(medico != null){
            preencheFormulario(medico);
        }*/

    }

    public Medico pegaMedico(){
        medico.setNome(campoNome.getText().toString());
        medico.setTelefone1(campoTelefone1.getText().toString());
        medico.setTelefone2(campoTelefone2.getText().toString());
        return medico;
    }

    public void preencheFormulario(Medico medico){
        campoNome.setText(medico.getNome());
        campoTelefone1.setText(medico.getTelefone1());
        campoTelefone2.setText(medico.getTelefone2());
        this.medico = medico;
    }


    /**
     *
     * @param item
     * Esse método salva e faz a verificação de campos nulos, é ele que insere os itens de salvar e cancelar na toolbar
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_medico_salvar:
                medico = pegaMedico();
                if(medico.getId() != null){
                    if (!TextUtils.isEmpty(campoNome.getText().toString())){
                        if (!TextUtils.isEmpty(campoTelefone1.getText())) {

                            medico.save();
                            Toast.makeText(this, "Alterado com sucesso", Toast.LENGTH_SHORT).show();

                            /*Intent intent = new Intent(getApplicationContext(), ListaMedico.class);
                            startActivity(intent);*/
                            finish();


                        }else {
                            //seta o foco para o campo nome
                            campoTelefone1.requestFocus();
                            campoTelefone1.setError("Por favor, preencha o campo Telefone");
                        }
                    }else{
                        campoNome.requestFocus();
                        campoNome.setError("Por favor, preecha o campo Nome");

                    }

                    //SALVAR
                }else {

                    if (!TextUtils.isEmpty(campoNome.getText().toString())){
                        if(!TextUtils.isEmpty(campoTelefone1.getText())){

                        medico.save();
                        Toast.makeText(this, "SALVO com sucesso", Toast.LENGTH_SHORT).show();
                        /*Intent intent = new Intent(getApplicationContext(), ListaMedico.class);
                        startActivity(intent);*/
                        finish();

                        }else {
                            //seta o foco para o campo nome
                            campoTelefone1.requestFocus();
                            campoTelefone1.setError("Por favor, preencha o campo Telefone");
                        }
                    }else{
                        campoNome.requestFocus();
                        campoNome.setError("Por favor, preecha o campo Nome");

                    }
                }
                break;
            case R.id.menu_medico_cancelar:
                finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        //inflar o xml e colocar no parametro menu
        menuInflater.inflate(R.menu.menu_medico, menu);
        return super.onCreateOptionsMenu(menu);
    }


    /*public void inserirMedico(View view){
         //Medico medico = new Medico();
        if(medico.getId() == null) {

            if (!TextUtils.isEmpty(ed_nome.getText().toString())) {

                medico.setNome(ed_nome.getText().toString());
                medico.setTelefone1(ed_telefone1.getText().toString());
                medico.setTelefone2(ed_telefone2.getText().toString());
                //medico.setStatus(true);
                medico.save();
                Toast.makeText(this, "Salvo com sucesso", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(), ListaMedico.class);
                startActivity(intent);
                finish();
            } else {
                ed_nome.requestFocus();
                ed_nome.setError("Por favor preecha o campo nome");
            }
        }

    }*/


    public void cancelarMedico(View view){
        finish();
    }

    public void preencherListaMedico(Medico medico){
        campoNome.setText(medico.getNome());
        campoTelefone1.setText(medico.getTelefone1());
        campoTelefone2.setText(medico.getTelefone2());
        this.medico = medico;
    }


}
