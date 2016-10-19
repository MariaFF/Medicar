package com.example.maria.medicarsugar.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.maria.medicarsugar.R;
import com.example.maria.medicarsugar.adapter.MedicamentoAdapter;
import com.example.maria.medicarsugar.adapter.MedicoAdapter;
import com.example.maria.medicarsugar.medicamento.NovoMedicamento;
import com.example.maria.medicarsugar.modelo.Medicamento;
import com.software.shell.fab.ActionButton;

import java.util.List;

/**
 * Created by maria on 24/07/2016.
 */
public class ListaMedicamentoFragment extends Fragment {

    private Medicamento medicamento;
    private MedicamentoAdapter medicamentoAdapter;
    private ListView listaViewMedicamento;

    private ActionButton fabNovoMedicamento;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista_medicamento, container, false);

        getActivity().setTitle("Lista de medicamentos");

        personalizarFAB(view);
        fabNovoMedicamento.setOnClickListener(onItemClickNovoMedicamento());

        listaViewMedicamento = (ListView) view.findViewById(R.id.medicamento_list_view);
        /*listaViewMedicamento.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                medicamento = (Medicamento) listaViewMedicamento.getItemAtPosition(position);
                Intent intent = new Intent(getActivity(), NovoMedicamento.class);
                intent.putExtra("medicamento", medicamento);
                startActivity(intent);
            }
        });
*/
        preencherListaMedicamento();

        setHasOptionsMenu(true);
        registerForContextMenu(listaViewMedicamento);

        return view;

    }

    private void personalizarFAB(View view) {
        fabNovoMedicamento = (ActionButton) view.findViewById(R.id.bt_novo);
        fabNovoMedicamento.setShowAnimation(ActionButton.Animations.ROLL_FROM_DOWN);
        fabNovoMedicamento.setHideAnimation(ActionButton.Animations.ROLL_TO_DOWN);
        fabNovoMedicamento.playShowAnimation();
        fabNovoMedicamento.playHideAnimation();

    }

    /*@Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        preencherListaMedicamento();
    }*/



    @Override
    public void onResume() {
        super.onResume();
        preencherListaMedicamento();
    }
    //Metodo da ação do FAB novo Medicamento
    private View.OnClickListener onItemClickNovoMedicamento() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NovoMedicamento.class);
                startActivity(intent);
            }
        };
    }

    private void preencherListaMedicamento(){
        //adapter = new MedicamentoAdapter(Medicamento.find(Medicamento.class, "status = ? ", "false"), getActivity());
        medicamentoAdapter = new MedicamentoAdapter(Medicamento.findWithQuery(Medicamento.class, "Select * from Medicamento " +
                "where status is ? ", "1"), getActivity());
        listaViewMedicamento.setAdapter(medicamentoAdapter);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        final AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        final Medicamento medicamento = (Medicamento) listaViewMedicamento.getItemAtPosition(info.position);

        //ALTERAR
        MenuItem itemAlterar = menu.add("Alterar");
        itemAlterar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Long idMedicamento = listaViewMedicamento.getItemIdAtPosition(info.position);
                Bundle bundle = new Bundle();
                bundle.putLong("idMedicamento", idMedicamento);

                Intent intentAlterar = new Intent(getActivity(), NovoMedicamento.class);
                intentAlterar.putExtras(bundle);
                startActivity(intentAlterar);
                return false;
            }
        });

        //SUSPENDER
        MenuItem itemSuspender = menu.add("Suspender");
        itemSuspender.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                medicamento.setStatus(false);
                medicamento.save();
                preencherListaMedicamento();
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
                builder.setMessage("Deseja excluir medicamento ?");
                builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        medicamento.delete(medicamento);
                        preencherListaMedicamento();
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



    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        MenuInflater menuInflater = getActivity().getMenuInflater();
        menuInflater.inflate(R.menu.menu_lista_medicamento, menu);

        MenuItem itemPesquisa = menu.findItem(R.id.menu_medicamento_pesquisar);
        /*SearchView pesquisar = (SearchView) itemPesquisa.getActionView();
        pesquisar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
*/
        super.onCreateOptionsMenu(menu, inflater);
    }


}
