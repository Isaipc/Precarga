package com.example.precarga;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import com.example.precarga.api.ApiAdapter;
import com.example.precarga.api.ApiService;
import com.example.precarga.data.SessionManager;
import com.example.precarga.data.models.LoginRequest;
import com.example.precarga.data.models.LoginResponse;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    SessionManager sessionManager;

    TextInputLayout usernameLayout;
    TextInputLayout passwordLayout;
    TextInputEditText usernameEditText;
    TextInputEditText passwordEditText;
    Button loginButton;
    ProgressBar loadingProgressBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sessionManager = new SessionManager(this);

        if (sessionManager.fetchAuthToken() != null)
            acceder();

        usernameLayout = findViewById(R.id.l_user);
        passwordLayout = findViewById(R.id.l_password);
        usernameEditText = findViewById(R.id.user);
        passwordEditText = findViewById(R.id.password);
        loginButton = findViewById(R.id.login);
        loadingProgressBar = findViewById(R.id.loading);

        usernameEditText.setOnFocusChangeListener((view, b) -> usernameLayout.setError(null));
        passwordEditText.setOnFocusChangeListener((view, b) -> passwordLayout.setError(null));

        loginButton.setOnClickListener(v -> {
            if (!validar())
                return;
            loadingProgressBar.setVisibility(View.VISIBLE);
            login();
        });
    }

    private void login() {
        ApiService apiService = ApiAdapter.getApiService();

        LoginRequest loginRequest = new LoginRequest(
                usernameEditText.getEditableText().toString(),
                passwordEditText.getEditableText().toString());

        apiService.login(loginRequest).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(@NotNull Call<LoginResponse> call,
                                   @NotNull Response<LoginResponse> response) {
                loadingProgressBar.setVisibility(View.GONE);

                if (response.code() == 200) {
                    assert response.body() != null;

                    String token = response.body().getAccessToken();
                    String tokenType = response.body().getTokenType();
                    guardarSession(token, tokenType);
                    acceder();
                } else if (response.code() == 401)
                    mostrarLoginFallido(R.string.login_unauthorized);
            }

            @Override
            public void onFailure(@NotNull Call<LoginResponse> call, @NotNull Throwable t) {
                mostrarLoginFallido(R.string.login_failed);
                loadingProgressBar.setVisibility(View.GONE);
            }
        });
    }

    private boolean validar() {
        Editable user = usernameEditText.getEditableText();
        Editable password = passwordEditText.getEditableText();
        boolean valid = true;

        if (user.toString().isEmpty()) {
            usernameLayout.setError(getString(R.string.invalid_username));
            valid = false;
        }
        if (password.toString().isEmpty()) {
            passwordLayout.setError(getString(R.string.invalid_password));
            valid = false;
        }
        return valid;
    }

    private void acceder() {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));

        //Complete and destroy login activity once successful
        finish();
        setResult(Activity.RESULT_OK);
    }

    private void guardarSession(String token, String tokenType) {
        sessionManager.saveAuthToken(tokenType + " " + token);
    }

    private void mostrarLoginFallido(@StringRes Integer errorString) {
        final View viewpos = findViewById(R.id.l_snackbar);
        Snackbar.make(viewpos, getString(errorString), Snackbar.LENGTH_LONG)
                .show();
    }
}