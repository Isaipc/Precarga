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
import com.example.precarga.data.models.UsuarioResponse;

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

        apiService.obtenerDatosUsuario(token).enqueue(new Callback<UsuarioResponse>() {
            @Override
            public void onResponse(@NotNull Call<UsuarioResponse> call,
                                   @NotNull Response<UsuarioResponse> response) {

                if (response.isSuccessful()) {

                    UsuarioResponse usuario = response.body();

                    assert usuario != null;

                    nombre.setText(usuario.getNombre());
                    control.setText(usuario.getControl());
                    semestre.setText(String.valueOf(usuario.getPeriodo()));
                    promedio.setText(String.valueOf(usuario.getPromedio()));
                    creditos.setText(String.valueOf(usuario.getCreditos()));
                    inscripcion.setText(usuario.getInscrito());
                }
            }

            @Override
            public void onFailure(@NotNull Call<UsuarioResponse> call, @NotNull Throwable t) {

            }
        });
    }
}