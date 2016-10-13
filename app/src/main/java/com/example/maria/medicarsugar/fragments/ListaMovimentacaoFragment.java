package com.example.maria.medicarsugar.fragments;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import com.example.maria.medicarsugar.R;
import com.example.maria.medicarsugar.adapter.MovimentacaoAdapter;
import com.example.maria.medicarsugar.modelo.Movimentacao;

public class ListaMovimentacaoFragment extends Fragment {

    Movimentacao movimentacao;
    private Switch switchSuspender;
    private MovimentacaoAdapter movimentacaoAdapter;
    private ListView listaMovimentacao;
    private boolean switchStatus;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista_movimentacao, container, false);
        getActivity().setTitle("Lembretes");

        movimentacao = new Movimentacao();
        listaMovimentacao = (ListView) view.findViewById(R.id.list_view_movimentacao);

        switchSuspender = (Switch) view.findViewById(R.id.movimentacao_suspender_horario);
        /*switchSuspender.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    movimentacao.setStatus(true);
                    Toast.makeText(getContext(), "Clicou em sim", Toast.LENGTH_SHORT ).show();
                }else
                    movimentacao.setStatus(false);

            }
        });*/



        return view;
    }

    /*private CompoundButton.OnCheckedChangeListener onCheckSuspender() {
        return new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    movimentacao.setStatus(true);
                else
                    movimentacao.setStatus(false);
            }
        };
    }*/


    public void preencherListaNaoSuspensos(){
        movimentacaoAdapter = new MovimentacaoAdapter(Movimentacao.find(Movimentacao.class, "status = true" +movimentacao.getStatus()), getActivity());
        listaMovimentacao.setAdapter(movimentacaoAdapter);

    }
}
