package com.example.precarga;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.precarga.adapter.MateriaAdapter;
import com.example.precarga.viewmodels.PrecargaViewModel;

public class PrecargaFragment extends Fragment {

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
        mViewModel = new ViewModelProvider(requireActivity()).get(PrecargaViewModel.class);

        setupMateriaList();
        return mRoot;
//        return binding.getRoot();
    }

    private void setupMateriaList() {
        final MateriaAdapter adapter = new MateriaAdapter();

        RecyclerView rvMaterias = mRoot.findViewById(R.id.list_materias);
        TextView tvTotalCredit = mRoot.findViewById(R.id.total_credit);

        rvMaterias.setAdapter(adapter);
//        binding.listMaterias.setAdapter(adapter);

        mViewModel.getAll().observe(requireActivity(), adapter::submitList);
        mViewModel.getTotalCredit().setValue(adapter.totalCredit);
        mViewModel.getTotalCredit().observe(requireActivity(), integer -> {
            tvTotalCredit.setText(String.valueOf(integer));
            Log.d("alv compa", "observer-getTotalCredit");
        });
//        binding.setPrecargaViewModel(viewModel);
//        binding.setLifecycleOwner(getActivity());
//        binding.executePendingBindings();
    }
}