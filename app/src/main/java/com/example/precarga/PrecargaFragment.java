package com.example.precarga;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.precarga.adapter.MateriaAdapter;
import com.example.precarga.data.Materia;
import com.example.precarga.viewmodels.PrecargaViewModel;

import java.util.ArrayList;
import java.util.List;

public class PrecargaFragment extends Fragment {

    private PrecargaViewModel precargaViewModel;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_precarga, container, false);
        recyclerView = root.findViewById(R.id.list_materias);
        layoutManager = new LinearLayoutManager(getActivity());

        //mAdapter = new MateriaAdapter(myDataset);
        //recyclerView.setAdapter(mAdapter);

        return root;
    }


    private void setupMateriaList(View parent){
        RecyclerView recyclerView = parent.findViewById(R.id.list_materias);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        List<Materia> materiaList = new ArrayList<>();

        materiaList.add(new Materia("SOFT.DE SIS.II", "03CAAC8", 4, "N"));
        materiaList.add(new Materia("BASES DATOS II", "03CAAC8", 4, "N"));
        materiaList.add(new Materia("REDES DE COM II", "03CAAC8", 4, "N"));
        materiaList.add(new Materia("PROGRAMACION", "03CAAC8", 4, "N"));
        materiaList.add(new Materia("TALLER INV. I", "03CAAC8", 4, "N"));
        materiaList.add(new Materia("SIST. PROG.", "03CAAC8", 4, "N"));
        materiaList.add(new Materia("GEST. PROY. SOFT.", "03CAAC8", 4, "N"));

        MateriaAdapter materiaAdapter = new MateriaAdapter(materiaList);
        recyclerView.setAdapter(materiaAdapter);

    }
}