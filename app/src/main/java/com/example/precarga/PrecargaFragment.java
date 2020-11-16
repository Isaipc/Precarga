package com.example.precarga;

import android.os.Bundle;

import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
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
import com.example.precarga.databinding.FragmentPrecargaBinding;
import com.example.precarga.viewmodels.PrecargaViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Observable;
import java.util.Observer;
import java.util.zip.Inflater;

public class PrecargaFragment extends Fragment{

    private FragmentPrecargaBinding binding;
    private View mRoot;
    private PrecargaViewModel mViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        binding = DataBindingUtil.inflate(inflater,
//                R.layout.fragment_precarga,
//                container,
//                false);
        mRoot = inflater.inflate(R.layout.fragment_precarga, container, false);
        setupMateriaList();

//        return binding.getRoot();
        return mRoot;
    }

    private void setupMateriaList() {
        final MateriaAdapter adapter = new MateriaAdapter();

        RecyclerView rvMaterias = mRoot.findViewById(R.id.list_materias);
        rvMaterias.setAdapter(adapter);
//        binding.listMaterias.setAdapter(adapter);

        mViewModel = new ViewModelProvider(requireActivity()).get(PrecargaViewModel.class);
        mViewModel.getAll().observe(requireActivity(), adapter::submitList);
//        binding.setPrecargaViewModel(viewModel);
//        binding.setLifecycleOwner(getActivity());
//        binding.executePendingBindings();
   }
}