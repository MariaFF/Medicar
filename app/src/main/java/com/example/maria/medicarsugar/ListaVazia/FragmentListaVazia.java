package com.example.maria.medicarsugar.ListaVazia;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.maria.medicarsugar.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentListaVazia extends Fragment {


    public FragmentListaVazia() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lista_vazia, container, false);
    }

}
