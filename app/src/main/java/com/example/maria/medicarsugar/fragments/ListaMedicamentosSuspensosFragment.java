package com.example.maria.medicarsugar.fragments;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.maria.medicarsugar.R;
import com.example.maria.medicarsugar.adapter.MedicamentoAdapter;
import com.example.maria.medicarsugar.modelo.Medicamento;

public class ListaMedicamentosSuspensosFragment extends Fragment {

    Medicamento medicamento = new Medicamento();
    MedicamentoAdapter adapter;
    ListView listaView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista_medicamento, container, false);
        getActivity().setTitle("Medicamentos Suspensos");

        listaView = (ListView) view.findViewById(R.id.medicamento_suspensos_list_view);
        preencherMedicamentosSuspensos();

        return view;
    }

    //List<Note> notes = Note.findWithQuery(Note.class, "Select * from Note where name = ?", "satya");

    public void preencherMedicamentosSuspensos(){
        adapter = new MedicamentoAdapter(Medicamento.findWithQuery(Medicamento.class, "SELECT * FROM Medicamento " +
                "WHERE status = ? ", "false"), getActivity());
        listaView.setAdapter(adapter);


    }
}
