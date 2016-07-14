package com.example.maria.medicarsugar.receita;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.maria.medicarsugar.R;
import com.example.maria.medicarsugar.adapter.ReceitaAdapter;
import com.example.maria.medicarsugar.medico.ListaMedico;
import com.example.maria.medicarsugar.modelo.Receita;

import java.util.List;

public class ListaReceita extends AppCompatActivity {

    private ListView listarReceitas;
    private ReceitaAdapter receitaAdapter;
    private Receita receita;
    private Button btNovaReceita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_receita);
        btNovaReceita = (Button) findViewById(R.id.bt_novo);
        listarReceitas = (ListView) findViewById(R.id.receita_list_view);
        listarReceitas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                receita = (Receita) listarReceitas.getItemAtPosition(position);
                Intent intent = new Intent(ListaReceita.this, NovaReceita.class);
                intent.putExtra("receita", receita);
                startActivity(intent);
            }
        });

        preencherListaReceita();
        btNovaReceita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListaReceita.this, NovaReceita.class);
                startActivity(intent);
            }
        });
    }

    private void preencherListaReceita() {
        receitaAdapter = new ReceitaAdapter(Receita.listAll(Receita.class), this);
        listarReceitas.setAdapter(receitaAdapter);

    }
}
