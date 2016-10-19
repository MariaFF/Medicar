package com.example.maria.medicarsugar.fragments;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.maria.medicarsugar.R;
import com.example.maria.medicarsugar.adapter.MedicamentoAdapter;
import com.example.maria.medicarsugar.adapter.MedicamentosSuspensosAdapter;
import com.example.maria.medicarsugar.modelo.Medicamento;

public class ListaMedicamentosSuspensosFragment extends Fragment {

    Medicamento medicamento;
    MedicamentosSuspensosAdapter adapter;
    ListView listaView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista_medicamentos_suspensos, container, false);


        getActivity().setTitle("Medicamentos Suspensos");

        listaView = (ListView) view.findViewById(R.id.medicamento_suspensos_list_view);
        preencherMedicamentosSuspensos();

        registerForContextMenu(listaView);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        preencherMedicamentosSuspensos();
        Log.i("MedSuspenso", "Chamou onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        preencherMedicamentosSuspensos();
        Log.i("MedSuspenso", "Chamou onResume");
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        final AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        final Medicamento medicamento = (Medicamento) listaView.getItemAtPosition(info.position);


        //Ativar
        MenuItem itemSuspender = menu.add("Ativar");
        itemSuspender.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                medicamento.setStatus(true);
                medicamento.save();
                preencherMedicamentosSuspensos();
                return false;

            }
        });
    }

    public void preencherMedicamentosSuspensos(){
        adapter = new MedicamentosSuspensosAdapter(getActivity(), Medicamento.findWithQuery(Medicamento.class, "SELECT * FROM Medicamento " +
                "WHERE status is ? ", "0"));
        listaView.setAdapter(adapter);

    }
}
