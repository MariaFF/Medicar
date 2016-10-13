package com.example.maria.medicarsugar.fragments;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.maria.medicarsugar.R;
import com.example.maria.medicarsugar.adapter.ReceitaAdapter;
import com.example.maria.medicarsugar.modelo.Receita;
import com.example.maria.medicarsugar.receita.NovaReceita;
import com.software.shell.fab.ActionButton;

import java.util.List;

/**
 * Created by maria on 20/07/2016.
 */
public class ListaReceitaFragment extends Fragment {


    private ReceitaAdapter receitaAdapter;
    private ListView listarReceitas;

    private Receita receita;
    private ActionButton fabReceita;
    //protected RecyclerView recyclerView;
    //private List<Receita> listaReceitas;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Criar a view, gerar uma view através de um XML usando o inflater

        View view = inflater.inflate(R.layout.fragment_lista_receita, container, false);


        fabReceita = (ActionButton) view.findViewById(R.id.bt_novo);
        fabReceita.setShowAnimation(ActionButton.Animations.ROLL_FROM_DOWN);
        fabReceita.setHideAnimation(ActionButton.Animations.ROLL_TO_DOWN);
        fabReceita.playShowAnimation();
        fabReceita.setOnClickListener(onItemClickNovaReceita());

        listarReceitas = (ListView) view.findViewById(R.id.receita_recycler_view);
        listarReceitas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                receita = (Receita) listarReceitas.getItemAtPosition(position);
                Intent intent = new Intent(getActivity(), NovaReceita.class);
                intent.putExtra("receita", receita);
                startActivity(intent);
            }
        });
        registerForContextMenu(listarReceitas);
        preencherListaReceita();

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        preencherListaReceita();
    }

    private View.OnClickListener onItemClickNovaReceita() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NovaReceita.class);
                startActivity(intent);
            }
        };
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        //RecyclerView.OnCreateContextMenuListener info = (RecyclerView.OnCreateContextMenuListener) menuInfo;
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        final Receita receita = (Receita) listarReceitas.getItemAtPosition(info.position);
        //getItemAtPosition(info.position);

        MenuItem itemExcluir = menu.add("Excluir");
        itemExcluir.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                receita.delete(receita);
                preencherListaReceita();
                return false;
            }
        });
    }


    private void preencherListaReceita() {
        receitaAdapter = new ReceitaAdapter(Receita.listAll(Receita.class), getActivity());
        listarReceitas.setAdapter(receitaAdapter);

    }



}
