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
import com.example.precarga.data.models.UsuarioDetallesResponse;
import com.example.precarga.data.models.UsuarioResponse;
import com.example.precarga.viewmodels.HomeViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

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

        sessionManager = new SessionManager(this.requireContext());

        View root = inflater.inflate(R.layout.fragment_home, container, false);

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
            public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
                if (response.isSuccessful()) {
                    UsuarioResponse usuario = response.body();
                    UsuarioDetallesResponse usuarioDetalles = usuario.getDetalles();

                    nombre.setText(usuario.getNombre());
                    control.setText(usuario.getNombre());
                    semestre.setText(usuarioDetalles.getNuevoPeriodo());
                    promedio.setText((int) usuarioDetalles.getPromedio());
                    creditos.setText(usuarioDetalles.getCreditos());
                    inscripcion.setText(usuarioDetalles.getInscrito());
                }
            }

            @Override
            public void onFailure(Call<UsuarioResponse> call, Throwable t) {

            }
        });
    }
}