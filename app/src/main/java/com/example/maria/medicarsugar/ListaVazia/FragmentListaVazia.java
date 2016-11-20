package com.example.maria.medicarsugar.ListaVazia;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.maria.medicarsugar.R;
import com.example.maria.medicarsugar.adapter.MedicamentoAdapter;
import com.example.maria.medicarsugar.medicamento.NovoMedicamento;
import com.example.maria.medicarsugar.modelo.Medicamento;
import com.software.shell.fab.ActionButton;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentListaVazia extends Fragment {

    private ImageView frameVazio;
    private ActionButton fab;


    public FragmentListaVazia() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista_vazia, container, false);

        frameVazio = (ImageView) view.findViewById(R.id.frame_vazio);
        fab = (ActionButton) view.findViewById(R.id.bt_novo);


        fab.setOnClickListener(onNovoMedicamento());






        return view;
    }

    private View.OnClickListener onNovoMedicamento() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NovoMedicamento.class);
                startActivity(intent);
            }
        };
    }



}
