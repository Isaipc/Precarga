package com.example.precarga;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.precarga.api.ApiAdapter;
import com.example.precarga.api.ApiService;
import com.example.precarga.data.SessionManager;
import com.example.precarga.data.models.MensajeResponse;
import com.google.android.material.button.MaterialButton;

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
                .enqueue(new Callback<MensajeResponse>() {
                    @Override
                    public void onResponse(Call<MensajeResponse> call, Response<MensajeResponse> response) {
                        if (response.isSuccessful()) {
                            cerrar();
                        }
                    }

                    @Override
                    public void onFailure(Call<MensajeResponse> call, Throwable t) {

                    }
                });
    }

    private void cerrar() {
        sessionManager.saveAuthToken(null);

        startActivity(new Intent(this.requireContext(), LoginActivity.class));
        this.requireActivity().finish();

        requireActivity().setResult(Activity.RESULT_OK);
    }
}