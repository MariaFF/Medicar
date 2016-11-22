package com.example.maria.medicarsugar.fragments;


import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
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

        MenuItem itemLigar = menu.add("Ligar");
        itemLigar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE)
                        != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE}, 1);
                }else{
                    Intent intentLigar = new Intent(Intent.ACTION_CALL);
                    intentLigar.setData(Uri.parse("tel: " +cuidador.getTelefone()));
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
                final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setIcon(R.drawable.alert_box );
                builder.setTitle("Confirmação");
                builder.setMessage("Deseja excluir cuidador ?");
                builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        cuidador.delete(cuidador);
                        preencherLista();
                    }
                    });
                    builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

                    AlertDialog dialog = builder.create();
                    dialog.show();
                    return false;

            }
        });
    }

    private void preencherLista() {
        cuidadorAdapter = new CuidadorAdapter(Cuidador.listAll(Cuidador.class), getActivity());
        listViewCuidador.setAdapter(cuidadorAdapter);
    }
}
