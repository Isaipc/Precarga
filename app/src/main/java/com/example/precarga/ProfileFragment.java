package com.example.precarga;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.precarga.api.ApiAdapter;
import com.example.precarga.api.ApiService;
import com.example.precarga.data.SessionManager;
import com.example.precarga.data.models.Mensaje;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment {

    SessionManager sessionManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_profile, container, false);

        sessionManager = new SessionManager(this.requireContext());

        MaterialButton btn = root.findViewById(R.id.btn_logout);

        btn.setOnClickListener(view -> logout());

        return root;
    }

    private void logout() {
        ApiService apiService = ApiAdapter.getApiService();
        apiService.logout(sessionManager.fetchAuthToken())
                .enqueue(new Callback<Mensaje>() {
                    @Override
                    public void onResponse(@NotNull Call<Mensaje> call,
                                           @NotNull Response<Mensaje> response) {
                        if (response.isSuccessful()) {
                            Utils.solicitarLogin(requireActivity());
                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call<Mensaje> call, @NotNull Throwable t) {
                        Snackbar.make(requireView(), getString(R.string.error), Snackbar.LENGTH_SHORT)
                                .show();
                        Snackbar.make(requireView(), t.getMessage(), Snackbar.LENGTH_SHORT)
                                .show();
                    }
                });
    }
}