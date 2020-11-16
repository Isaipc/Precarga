package com.example.precarga;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.precarga.adapter.MateriaAdapter;
import com.example.precarga.viewmodels.PrecargaViewModel;

public class PrecargaFragment extends Fragment{

//    private FragmentPrecargaBinding binding;
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