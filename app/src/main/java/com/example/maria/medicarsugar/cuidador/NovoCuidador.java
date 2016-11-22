package com.example.maria.medicarsugar.cuidador;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.maria.medicarsugar.R;
import com.example.maria.medicarsugar.modelo.Cuidador;
import com.example.maria.medicarsugar.modelo.Medico;
import com.example.maria.medicarsugar.util.Mask;

public class NovoCuidador extends AppCompatActivity {

    private Cuidador cuidador;
    private EditText campoNome;
    private EditText campoTelefone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_cuidador);
        setTitle("Novo cuidador");

        cuidador = new Cuidador();

        campoNome = (EditText) findViewById(R.id.cuidador_ed_nome);
        campoTelefone = (EditText) findViewById(R.id.cuidador_ed_telefone);
        campoTelefone.addTextChangedListener(Mask.insert("(##)####-#####", campoTelefone));

        if(getIntent().hasExtra("idCuidador")){
            Bundle bundle = getIntent().getExtras();
            Long id = bundle.getLong("idCuidador");
            cuidador = (Cuidador) Cuidador.findById(Cuidador.class, id);
            preencheFormulario(cuidador);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        //inflar o xml e colocar no parametro menu
        menuInflater.inflate(R.menu.menu_cuidador, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_cuidador_salvar:
                cuidador = pegaCuidador();
                //ALTERAR
                if(cuidador.getId() != null){
                    if (!TextUtils.isEmpty(campoNome.getText().toString())){
                        if(!TextUtils.isEmpty(campoTelefone.getText())) {
                            cuidador.save();
                            Toast.makeText(this, "Alterado", Toast.LENGTH_LONG).show();
                            finish();
                        }else{
                            campoTelefone.requestFocus();
                            campoTelefone.setError("Por favor, preencha campo telefone");
                        }
                    }else {
                        campoNome.requestFocus();
                        campoNome.setError("Por favor, preencha campo nome");
                    }
                    //SALVAR
                }else{
                    if(!TextUtils.isEmpty(campoNome.getText().toString())){
                        if(!TextUtils.isEmpty(campoTelefone.getText())) {
                            cuidador.save();
                            Toast.makeText(this, "Salvo", Toast.LENGTH_LONG).show();
                            finish();
                        }else{
                            campoTelefone.requestFocus();
                            campoTelefone.setError("Por favor, preencha o campo telefone");
                        }
                    }else {
                        campoNome.requestFocus();
                        campoNome.setError("Por favor, preencha campo nome");
                    }
                }
            break;

            case R.id.menu_cuidador_cancelar:
                finish();


        }

        return super.onOptionsItemSelected(item);
    }

    private Cuidador pegaCuidador() {
        cuidador.setNome(campoNome.getText().toString());
        cuidador.setTelefone(campoTelefone.getText().toString());
        return cuidador;
    }

    private void preencheFormulario(Cuidador cuidador) {
        campoNome.setText(cuidador.getNome());
        campoTelefone.setText(cuidador.getTelefone());
        this.cuidador = cuidador;
    }
}
