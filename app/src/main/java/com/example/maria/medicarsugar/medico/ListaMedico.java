package com.example.maria.medicarsugar.medico;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;

import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.support.v7.widget.SearchView;
import android.widget.Toast;

import com.example.maria.medicarsugar.R;
import com.example.maria.medicarsugar.adapter.MedicoAdapter;
import com.example.maria.medicarsugar.modelo.Medico;

import java.util.ArrayList;
import java.util.List;

public class ListaMedico extends AppCompatActivity {

    private List<Medico> listaMedicos = new ArrayList<>();
    private ListView listaViewMedico;
    Medico medico;
    private MedicoAdapter medicoAdapter;
    private NovoMedico novoMedico;
    private Bundle bundle = new Bundle();
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_medico);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listaViewMedico = (ListView) findViewById(R.id.medico_listView);
        listaViewMedico.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                medico = (Medico) listaViewMedico.getItemAtPosition(position);
                Intent intent = new Intent(ListaMedico.this, NovoMedico.class);
                intent.putExtra("medico", medico);
                startActivity(intent);
            }
        });

        preencherLista();

        Button novoMedico = (Button) findViewById(R.id.bt_novo);
        novoMedico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListaMedico.this, NovoMedico.class);
                startActivity(intent);
            }
        });

        registerForContextMenu(listaViewMedico);

    }

    @Override
    public void onResume(){
        super.onResume();
        preencherLista();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        final Medico medico = (Medico) listaViewMedico.getItemAtPosition(info.position);

        MenuItem itemLigar = menu.add("Ligar");
        itemLigar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(ActivityCompat.checkSelfPermission(ListaMedico.this, Manifest.permission.CALL_PHONE)
                        != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(ListaMedico.this, new String[]{Manifest.permission.CALL_PHONE}, 1);
                }else{
                    Intent intentLigar = new Intent(Intent.ACTION_CALL);
                    intentLigar.setData(Uri.parse("tel: " +medico.getTelefone1()));
                    startActivity(intentLigar);
                }
                return false;
            }
        });

        MenuItem itemExcluir = menu.add("Excluir");
        itemExcluir.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                medico.delete(medico);
                preencherLista();
                return false;
            }
        });
    }

    /*@Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        //Medico medico = (Medico) medicoAdapter.getItem(position);
        Long idItem = medicoAdapter.getItemId(position);
        bundle = new Bundle();
        //chamando o método putLong para armazenar um par de id's, sendo o primeiro uma String e o segundo um Long
        bundle.putLong("medico", idItem);

        Intent intent = new Intent(this, NovoMedico.class);
        intent.putExtras(bundle);
        startActivity(intent);

    }*/

    /*public void pesquisarMedico(View view){


        //SEARCH
        this.searchView = (SearchView) findViewById(R.id.searchViewMedico);
        Intent myIntent = getIntent();
        if(Intent.ACTION_SEARCH.equals(myIntent.getAction())){
            String query = myIntent.getStringExtra(SearchManager.QUERY).

            medicoAdapter = new MedicoAdapter (Medico.findWithQuery(Medico.class, "SELECT * FROM Medico WHERE nome LIKE '"+query+"' "), this);
            listaViewMedico = (ListView) findViewById(R.id.medico_listView);
            listaViewMedico.setAdapter(medicoAdapter);


        }
    }*/



    public void preencherLista() {
        //medicoAdapter = new MedicoAdapter(Medico.findWithQuery(Medico.class, "SELECT * FROM Medico WHERE status = true"), this);
        medicoAdapter = new MedicoAdapter(Medico.listAll(Medico.class), this);
        listaViewMedico = (ListView) findViewById(R.id.medico_listView);
        listaViewMedico.setAdapter(medicoAdapter);

    }

    /*public void voltarMed(View view){
        Intent intent = new Intent(getApplicationContext(), Listas.class);
        startActivity(intent);
    }*/


}
