package com.example.maria.medicarsugar.config_caixa;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.maria.medicarsugar.R;

public class LayoutCaixaFragment extends Fragment {

    private TextView a1;
    private TextView a2;
    private TextView a3;
    private TextView a4;
    private TextView b1;
    private TextView b2;
    private TextView b3;
    private TextView b4;
    private TextView c1;
    private TextView c2;
    private TextView c3;
    private TextView d1;
    private TextView d2;





    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout_caixa, container, false);
        getActivity().setTitle("Configurar caixa");

        recuperarComponentes(view);
        configurarEspacoCaixa();


        return view;
    }

    /**
     * esse metodo vai de acordo com o botão selecionado abrir um DIALOG para escolher:
     *      -- medicamento
     *      -- som ou audio
     *      --volume
     */
    public void configurarEspacoCaixa(){

        a1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Config", "Clicou A1");
                abrirDialogConfigEspacoCaixa();
                a1.setBackgroundColor(0x009999FF);
            }
        });

        a2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Config", "Clicou A2");
                abrirDialogConfigEspacoCaixa();
                a2.setBackgroundColor(0x009999FF);
            }
        });

        a3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Config", "Clicou A3");
                abrirDialogConfigEspacoCaixa();
                a3.setBackgroundColor(0x009999FF);
            }
        });

        a4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Config", "Clicou A4");
                abrirDialogConfigEspacoCaixa();
                a4.setBackgroundColor(0x009999FF);
            }
        });


    }

    public void abrirDialogConfigEspacoCaixa(){
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        DialogConfigEspacoCaixa dialogConfig = new DialogConfigEspacoCaixa();
        dialogConfig.show(fragmentTransaction, "dialog");
    }





    //recuperar a lista de medicamentos do banco, colocar em uma lista e quando adicionado esse medicamento
    //em um botão retira-lo dessa lista que vai preencher o Spinner

    /**
     * método que recupera os componentes da tela
     */
    public void recuperarComponentes(View view) {
        a1 = (TextView) view.findViewById(R.id.bt_a1);
        a2 = (TextView) view.findViewById(R.id.bt_a2);
        a3 = (TextView) view.findViewById(R.id.bt_a3);
        a4 = (TextView) view.findViewById(R.id.bt_a4);

        b1 = (TextView) view.findViewById(R.id.bt_b1);
        b2 = (TextView) view.findViewById(R.id.bt_b2);
        b3 = (TextView) view.findViewById(R.id.bt_b3);
        b4 = (TextView) view.findViewById(R.id.bt_b4);

        c1 = (TextView) view.findViewById(R.id.bt_c1);
        c2 = (TextView) view.findViewById(R.id.bt_c2);
        c3 = (TextView) view.findViewById(R.id.bt_c3);

        d1 = (TextView) view.findViewById(R.id.bt_d1);
        d2 = (TextView) view.findViewById(R.id.bt_d2);

    }

    public TextView getA1() {
        return a1;
    }

    public TextView getA2() {
        return a2;
    }

    public TextView getA3() {
        return a3;
    }

    public TextView getA4() {
        return a4;
    }

    public TextView getB1() {
        return b1;
    }

    public TextView getB2() {
        return b2;
    }

    public TextView getB3() {
        return b3;
    }

    public TextView getB4() {
        return b4;
    }

    public TextView getC1() {
        return c1;
    }

    public TextView getC2() {
        return c2;
    }

    public TextView getC3() {
        return c3;
    }

    public TextView getD1() {
        return d1;
    }

    public TextView getD2() {
        return d2;
    }
}
