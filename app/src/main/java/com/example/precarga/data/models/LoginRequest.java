package com.example.precarga.data.models;

import com.google.gson.annotations.SerializedName;

public class LoginRequest {

    @SerializedName("login")
    private String usuario;

    private String password;

    public LoginRequest(String usuario, String password) {
        this.usuario = usuario;
        this.password = password;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
