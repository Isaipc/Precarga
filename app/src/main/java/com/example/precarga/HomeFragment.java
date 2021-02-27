package com.example.precarga;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.precarga.api.ApiAdapter;
import com.example.precarga.api.ApiService;
import com.example.precarga.data.SessionManager;
import com.example.precarga.data.models.Alumno;
import com.google.android.material.snackbar.Snackbar;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private SessionManager sessionManager;

    public static final String URL = "DOC-URL";

    TextView nombre;
    TextView control;
    TextView semestre;
    TextView promedio;
    TextView creditos;
    TextView inscripcion;
    TextView especiales;
    TextView repites;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        sessionManager = new SessionManager(this.requireContext());

        nombre = root.findViewById(R.id.text_nombre);
        control = root.findViewById(R.id.text_control);
        semestre = root.findViewById(R.id.text_semestre);
        promedio = root.findViewById(R.id.text_promedio);
        creditos = root.findViewById(R.id.text_cred_acum);
        inscripcion = root.findViewById(R.id.text_f_inscripcion);
        especiales = root.findViewById(R.id.text_especiales);
        repites = root.findViewById(R.id.text_repites);

        obtenerDatos();

        Button btn_reticula = root.findViewById(R.id.btn_reticula);
        Button btn_precarga = root.findViewById(R.id.btn_precarga);

        btn_reticula.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), PdfViewActivity.class);
            intent.putExtra(URL, getString(R.string.reticula));
            startActivity(intent);
        });

        btn_precarga.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), PdfViewActivity.class);
            intent.putExtra(URL, getString(R.string.precarga));
            startActivity(intent);
        });
        return root;
    }

    private void obtenerDatos() {
        String token = sessionManager.fetchAuthToken();
        ApiService apiService = ApiAdapter.getApiService();

        apiService.obtenerDatosAlumno(token).enqueue(new Callback<Alumno>() {
            @Override
            public void onResponse(@NotNull Call<Alumno> call,
                                   @NotNull Response<Alumno> response) {

                if (response.isSuccessful()) {

                    Alumno usuario = response.body();

                    assert usuario != null;

                    nombre.setText(usuario.getNombre());
                    control.setText(usuario.getControl());
                    semestre.setText(String.valueOf(usuario.getPeriodo()));
                    promedio.setText(String.valueOf(usuario.getPromedio()));
                    creditos.setText(String.valueOf(usuario.getCreditos()));
                    inscripcion.setText(usuario.getInscrito());
                    especiales.setText(String.valueOf(usuario.getEspeciales()));
                    repites.setText(String.valueOf(usuario.getRepites()));

                } else if (response.code() == 401) {
                    Snackbar.make(requireView(), response.errorBody().toString(), Snackbar.LENGTH_SHORT)
                            .show();
                    Utils.solicitarLogin(requireActivity());
                }
            }

            @Override
            public void onFailure(@NotNull Call<Alumno> call, @NotNull Throwable t) {
                Snackbar.make(requireView(), getString(R.string.error), Snackbar.LENGTH_SHORT)
                        .show();
                Snackbar.make(requireView(), t.getMessage(), Snackbar.LENGTH_SHORT)
                        .show();
            }
        });
    }
}