package com.example.maria.medicarsugar.receita;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.maria.medicarsugar.R;
import com.example.maria.medicarsugar.modelo.Medico;
import com.example.maria.medicarsugar.modelo.Receita;

import java.util.Date;

public class NovaReceita extends AppCompatActivity {
    private Receita receita;
    private EditText campoDtReceita;
    private EditText campoValidadeReceita;
    private TextView txMedico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_receita);

        this.campoDtReceita = (EditText) findViewById(R.id.receita_dt_receita);
        this.campoValidadeReceita = (EditText) findViewById(R.id.receita_dt_validade);
        this.txMedico = (TextView) findViewById(R.id.receita_medico);
        receita = new Receita();

        Intent intent = getIntent();
        Medico medico = (Medico) intent.getSerializableExtra("medico");
        if(medico != null){
            txMedico.setText(medico.getNome());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_receita, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_receita_salvar:
                if(receita.getId() != null) {
                    receita.save();
                    Toast.makeText(this, "ALTERADO com sucesso", Toast.LENGTH_SHORT).show();
                }else{

                    receita.setDtReceita(campoDtReceita.getText().toString());
                    receita.setDtValidadeReceita(campoValidadeReceita.getText().toString());
                    receita.setStatus(true);
                    receita.save();
                    Toast.makeText(this, "SALVO com sucesso", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), ListaReceita.class);
                    startActivity(intent);
                }
            break;
        }

        return super.onOptionsItemSelected(item);
    }
}
