package com.example.precarga.data.models;

import com.google.gson.annotations.SerializedName;

public class Materia {
    private int id;
    private String nombre;
    @SerializedName("nombre_corto")
    private String nombreCorto;
    private String clave;
    private String grupo;
    private int periodo;
    private int creditos;
    private int teoricas;
    private int practicas;
    private String tipo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreCorto() {
        return nombreCorto;
    }

    public void setNombreCorto(String nombreCorto) {
        this.nombreCorto = nombreCorto;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public int getPeriodo() {
        return periodo;
    }

    public void setPeriodo(int periodo) {
        this.periodo = periodo;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    public int getTeoricas() {
        return teoricas;
    }

    public void setTeoricas(int teoricas) {
        this.teoricas = teoricas;
    }

    public int getPracticas() {
        return practicas;
    }

    public void setPracticas(int practicas) {
        this.practicas = practicas;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
