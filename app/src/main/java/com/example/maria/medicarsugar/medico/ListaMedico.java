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
import com.software.shell.fab.ActionButton;

import java.util.ArrayList;
import java.util.List;

public class ListaMedico extends AppCompatActivity {


    private ListView listaViewMedico;
    private Medico medico;
    private MedicoAdapter medicoAdapter;
    private ActionButton novoMedico;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_medico);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }
}
