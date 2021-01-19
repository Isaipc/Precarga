package com.example.precarga;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.precarga.adapter.MateriaAdapter;
import com.example.precarga.api.ApiAdapter;
import com.example.precarga.api.ApiService;
import com.example.precarga.data.SessionManager;
import com.example.precarga.data.models.Materia;
import com.example.precarga.data.models.ReticulaResponse;
import com.google.android.material.snackbar.Snackbar;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PrecargaFragment extends Fragment {

    //    private FragmentPrecargaBinding binding;
    private View mRoot;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        binding = DataBindingUtil.inflate(inflater,
//                R.layout.fragment_precarga,
//                container,
//                false);
        mRoot = inflater.inflate(R.layout.fragment_precarga, container, false);

        setupMateriaList();
        return mRoot;
//        return binding.getRoot();
    }

    private void setupMateriaList() {
        final MateriaAdapter adapter = new MateriaAdapter();

        RecyclerView rvMaterias = mRoot.findViewById(R.id.list_materias);
        TextView tvTotalCredit = mRoot.findViewById(R.id.total_credit);

        rvMaterias.setAdapter(adapter);

        ApiService apiService = ApiAdapter.getApiService();

        SessionManager sessionManager = new SessionManager(requireContext());
        String token = sessionManager.fetchAuthToken();

        apiService.solicitarPrecarga(token).enqueue(new Callback<ReticulaResponse>() {
            @Override
            public void onResponse(@NotNull Call<ReticulaResponse> call,
                                   @NotNull Response<ReticulaResponse> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    List<Materia> materias = response.body().getMaterias();

                    adapter.submitList(materias);
                }
            }

            @Override
            public void onFailure(@NotNull Call<ReticulaResponse> call, @NotNull Throwable t) {
                Snackbar.make(requireView(), getString(R.string.error), Snackbar.LENGTH_LONG)
                        .show();
            }
        });
//        binding.listMaterias.setAdapter(adapter);
//        binding.setPrecargaViewModel(viewModel);
//        binding.setLifecycleOwner(getActivity());
//        binding.executePendingBindings();
    }
}