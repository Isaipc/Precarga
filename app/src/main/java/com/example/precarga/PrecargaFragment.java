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
    private MateriaAdapter materiaAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_precarga, container, false);
        recyclerView = root.findViewById(R.id.list_materias);
        layoutManager = new LinearLayoutManager(getActivity());
        setupMateriaList(root);
        return root;
    }


    private void setupMateriaList(View parent){
        recyclerView = parent.findViewById(R.id.list_materias);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        List<Materia> materiaList = new ArrayList<>();

        materiaList.add(new Materia("SOFT.DE SIS.II", "03CAAC8", 4, "N"));
        materiaList.add(new Materia("BASES DATOS II", "03CAAC8", 4, "N"));
        materiaList.add(new Materia("REDES DE COM II", "03CAAC8", 4, "N"));
        materiaList.add(new Materia("PROGRAMACION", "03CAAC8", 4, "N"));
        materiaList.add(new Materia("TALLER INV. I", "03CAAC8", 4, "N"));
        materiaList.add(new Materia("SIST. PROG.", "03CAAC8", 4, "N"));

        materiaList.add(new Materia("GEST. PROY. SOFT.", "03CAAC8", 4, "N"));
        materiaList.add(new Materia("INT. ART.", "03CAAC8", 4, "N"));
        materiaList.add(new Materia("GEST. PROY. SOFT.", "03CAAC8", 4, "N"));
        materiaList.add(new Materia("GEST. PROY. SOFT.", "03CAAC8", 4, "N"));


        materiaAdapter = new MateriaAdapter(materiaList);
        recyclerView.setAdapter(materiaAdapter);

    }
}