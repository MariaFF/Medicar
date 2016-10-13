package com.example.maria.medicarsugar.fragments;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.maria.medicarsugar.R;
import com.example.maria.medicarsugar.adapter.MedicoAdapter;
import com.example.maria.medicarsugar.medico.NovoMedico;
import com.example.maria.medicarsugar.modelo.Medico;
import com.software.shell.fab.ActionButton;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListaMedicoFragment extends Fragment{

    private Medico medico;
    private ActionButton fabNovoMedico;
    private MedicoAdapter medicoAdapter;
    protected ListView listaViewMedico;
    private List<Medico> listaMedico;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //criar a view que representa o fragment
        View view = inflater.inflate(R.layout.fragment_lista_medico, container, false);
        getActivity().setTitle("Lista de Médicos");



        fabNovoMedico = (ActionButton) view.findViewById(R.id.bt_novo);
        fabNovoMedico.setShowAnimation(ActionButton.Animations.ROLL_FROM_DOWN);
        fabNovoMedico.setHideAnimation(ActionButton.Animations.ROLL_TO_DOWN);
        fabNovoMedico.playShowAnimation();
        fabNovoMedico.setOnClickListener(onItemClickNovoMedico());


        //Metodo para abrir a tela de cadastro para alterar o registro
        listaViewMedico = (ListView) view.findViewById(R.id.medico_listview);
        listaViewMedico.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                medico = (Medico) listaViewMedico.getItemAtPosition(position);
                Intent intent = new Intent(getActivity(), NovoMedico.class);
                intent.putExtra("medico", medico);
                startActivity(intent);
            }
        });
        registerForContextMenu(listaViewMedico);
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

    //Metodo do FAB novo medico
    private View.OnClickListener onItemClickNovoMedico() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NovoMedico.class);
                startActivity(intent);

            }
        };
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        final AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        final Medico medico = (Medico) listaViewMedico.getItemAtPosition(info.position);
        //getItemAtPosition(info.position);

        MenuItem itemLigar = menu.add("Ligar");
        itemLigar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE)
                        != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE}, 1);
                }else{
                    Intent intentLigar = new Intent(Intent.ACTION_CALL);
                    intentLigar.setData(Uri.parse("tel: " +medico.getTelefone1()));
                    startActivity(intentLigar);
                }
                return false;
            }
        });

        //ALTERAR
        MenuItem itemAlterar = menu.add("Alterar");
        itemAlterar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Long idMedico = listaViewMedico.getItemIdAtPosition(info.position);
                Bundle bundle = new Bundle();
                bundle.putLong("idMedico", idMedico);

                Intent intentAlterar = new Intent(getActivity(), NovoMedico.class);
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
                medico.delete(medico);
                preencherLista();
                return false;
            }
        });
    }

    public void preencherLista() {
        //medicoAdapter = new MedicoAdapter(Medico.findWithQuery(Medico.class, "SELECT * FROM Medico WHERE status = true"), this);
        medicoAdapter = new MedicoAdapter(Medico.listAll(Medico.class), getActivity());
        listaViewMedico.setAdapter(medicoAdapter);

    }


}
