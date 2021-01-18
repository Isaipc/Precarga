package com.example.precarga;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import com.example.precarga.api.ApiAdapter;
import com.example.precarga.api.ApiService;
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


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final TextInputLayout usernameLayout = findViewById(R.id.l_user);
        final TextInputLayout passwordLayout = findViewById(R.id.l_password);
        final TextInputEditText usernameEditText = findViewById(R.id.user);
        final TextInputEditText passwordEditText = findViewById(R.id.password);
        final Button loginButton = findViewById(R.id.login);
        final ProgressBar loadingProgressBar = findViewById(R.id.loading);

        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
//                loginViewModel.loginDataChanged(usernameEditText.getText().toString(),
//                        passwordEditText.getText().toString());
            }
        };

        usernameEditText.setOnFocusChangeListener((view, b) -> usernameLayout.setError(null));
        passwordEditText.setOnFocusChangeListener((view, b) -> passwordLayout.setError(null));
        usernameEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);

        loginButton.setOnClickListener(v -> {

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

            if(!valid)
                return;

            loadingProgressBar.setVisibility(View.VISIBLE);

            ApiService apiService = ApiAdapter.getApiService();

            LoginRequest loginRequest = new LoginRequest(user.toString(), password.toString());

            apiService.login(loginRequest).enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(@NotNull Call<LoginResponse> call, @NotNull Response<LoginResponse> response) {
                    if (response.code() == 200) {
                        loadingProgressBar.setVisibility(View.GONE);

                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        //Complete and destroy login activity once successful
                        finish();

                        setResult(Activity.RESULT_OK);
                    } else if (response.code() == 401) {
                        loadingProgressBar.setVisibility(View.GONE);

                        final View viewpos = findViewById(R.id.l_snackbar);
                        Snackbar.make(viewpos, R.string.login_unauthorized, Snackbar.LENGTH_LONG)
                                .show();
                    }
                }

                @Override
                public void onFailure(@NotNull Call<LoginResponse> call, @NotNull Throwable t) {
                    showLoginFailed(R.string.login_failed);
                }
            });
        });
    }


    private void showLoginFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }
}