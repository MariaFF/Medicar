package com.example.maria.medicarsugar.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.maria.medicarsugar.R;
import com.example.maria.medicarsugar.adapter.CuidadorAdapter;
import com.example.maria.medicarsugar.cuidador.NovoCuidador;
import com.example.maria.medicarsugar.modelo.Cuidador;
import com.software.shell.fab.ActionButton;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListaCuidadorFragment extends Fragment {

    private Cuidador cuidador;
    private CuidadorAdapter cuidadorAdapter;
    private ListView listViewCuidador;

    private ActionButton fab;


    public ListaCuidadorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista_cuidador, container, false);

        getActivity().setTitle("Lista de Cuidadores");

        fab = (ActionButton) view.findViewById(R.id.bt_novo);
        fab.setShowAnimation(ActionButton.Animations.ROLL_FROM_DOWN);
        fab.setHideAnimation(ActionButton.Animations.ROLL_TO_DOWN);
        fab.playShowAnimation();
        fab.setOnClickListener(onItemClickNovoCuidador());

        listViewCuidador = (ListView) view.findViewById(R.id.cuidador_list_view);
        listViewCuidador.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cuidador = (Cuidador) listViewCuidador.getItemAtPosition(position);
                Intent intent = new Intent(getActivity(), NovoCuidador.class);
                intent.putExtra("cuidador", cuidador);
                startActivity(intent);
            }
        });
        registerForContextMenu(listViewCuidador);
        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        preencherLista();
    }



    @Override
    public void onResume() {
        super.onResume();
        preencherLista();
    }

    private View.OnClickListener onItemClickNovoCuidador() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NovoCuidador.class);
                startActivity(intent);
            }
        };
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        final AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        final Cuidador cuidador = (Cuidador) listViewCuidador.getItemAtPosition(info.position);

        //ALTERAR
        MenuItem itemAlterar = menu.add("Alterar");
        itemAlterar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Long idCuidador = listViewCuidador.getItemIdAtPosition(info.position);
                Bundle bundle = new Bundle();
                bundle.putLong("idCuidador", idCuidador);

                Intent intentAlterar = new Intent(getActivity(), NovoCuidador.class);
                intentAlterar.putExtras(bundle);
                startActivity(intentAlterar);
                return false;
            }
        });

        //EXCLUIR
        MenuItem itemExcluir = menu.add("Excluir");
        itemExcluir.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                cuidador.delete(cuidador);
                preencherLista();
                return false;
            }
        });
    }

    private void preencherLista() {
        cuidadorAdapter = new CuidadorAdapter(Cuidador.listAll(Cuidador.class), getActivity());
        listViewCuidador.setAdapter(cuidadorAdapter);
    }
}
