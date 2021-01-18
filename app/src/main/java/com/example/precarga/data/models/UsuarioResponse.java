package com.example.precarga.data.models;

import com.google.gson.annotations.SerializedName;

public class UsuarioResponse {

    private String id;
    private String nombre;
    
    @SerializedName("login")
    private String usuario;

    @SerializedName("detalles")
    private UsuarioDetallesResponse detalles;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public UsuarioDetallesResponse getDetalles() {
        return detalles;
    }

    public void setDetalles(UsuarioDetallesResponse detalles) {
        this.detalles = detalles;
    }
}
