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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.precarga.adapter.MateriaAdapter;
import com.example.precarga.api.ApiAdapter;
import com.example.precarga.api.ApiService;
import com.example.precarga.data.SessionManager;
import com.example.precarga.data.models.ResponsePrecarga;
import com.example.precarga.databinding.FragmentPrecargaBinding;
import com.google.android.material.snackbar.Snackbar;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PrecargaFragment extends Fragment {

    private View mRoot;

    private FragmentPrecargaBinding binding;
    private PrecargaListener mListener;
    private SessionManager sessionManager;
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

        sessionManager = new SessionManager(requireContext());

        mListener = creditos -> {
            totalCreditos += creditos;
            binding.totalCredit.setText(String.valueOf(totalCreditos));
        };

        setupPrecarga();
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

    private void setupPrecarga() {
        final MateriaAdapter adapter = new MateriaAdapter();

        adapter.setPrecargaListener(mListener);

        binding.listMaterias.setAdapter(adapter);

        ApiService apiService = ApiAdapter.getApiService();
        String token = sessionManager.fetchAuthToken();

        apiService.obtenerPrecarga(token).enqueue(new Callback<ResponsePrecarga>() {
            @Override
            public void onResponse(Call<ResponsePrecarga> call, Response<ResponsePrecarga> response) {

                if (response.isSuccessful()) {
                    assert response.body() != null;

                    ResponsePrecarga materias = response.body();
                    adapter.submitList(materias.getMaterias());

                } else if (response.code() == 401) {
                    Snackbar.make(requireView(), response.errorBody().toString(), Snackbar.LENGTH_SHORT)
                            .show();
                    Utils.solicitarLogin(requireActivity());
                }
            }

            @Override
            public void onFailure(Call<ResponsePrecarga> call, Throwable t) {
                Snackbar.make(requireView(), getString(R.string.error), Snackbar.LENGTH_SHORT)
                        .show();
                Snackbar.make(requireView(), t.getMessage(), Snackbar.LENGTH_SHORT)
                        .show();
            }
        });
    }

    public interface PrecargaListener {
        void onClickMateria(int creditos);
    }
}