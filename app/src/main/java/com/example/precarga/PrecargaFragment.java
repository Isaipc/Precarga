package com.example.precarga;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.precarga.adapter.MateriaAdapter;
import com.example.precarga.api.ApiAdapter;
import com.example.precarga.api.ApiService;
import com.example.precarga.data.SessionManager;
import com.example.precarga.data.models.Materia;
import com.example.precarga.data.models.ReticulaResponse;
import com.example.precarga.databinding.FragmentPrecargaBinding;
import com.google.android.material.snackbar.Snackbar;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PrecargaFragment extends Fragment {

    private View mRoot;

    private FragmentPrecargaBinding binding;
    private PrecargaListener mListener;
    private int totalCreditos;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPrecargaBinding.inflate(inflater, container, false);

        mRoot = binding.getRoot();


        mListener = creditos -> {
            totalCreditos += creditos;
            binding.totalCredit.setText(String.valueOf(totalCreditos));
        };

        setupMateriaList();
        return mRoot;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.precarga_toolbar_menu, menu);
    }

    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        super.onPrepareOptionsMenu(menu);

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.accept_action:
                AlertDialog dialog = new AlertDialog.Builder(requireContext())
                        .setTitle("Guardar precarga")
                        .setMessage("Desea guardar los cambios en la precarga")
                        .setPositiveButton("Aceptar",
                                (dialogInterface, i) -> Snackbar.make(requireView(), "Guardando ...", Snackbar.LENGTH_SHORT)
                                        .show())
                        .setNegativeButton("Cancelar",
                                (dialogInterface, i) -> Snackbar.make(requireView(), "Cancelado", Snackbar.LENGTH_SHORT)
                                        .show())
                        .create();
                dialog.show();
                return true;
            case R.id.cancel_action:

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setupMateriaList() {
        final MateriaAdapter adapter = new MateriaAdapter();
        adapter.setPrecargaListener(mListener);

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
    }

    public interface PrecargaListener {
        void onClickMateria(int creditos);
    }
}