package com.example.precarga.data.models;

import com.google.gson.annotations.SerializedName;

public class UsuarioDetallesResponse {
    @SerializedName("nombre_carrera")
    private String nombreCarrera;

    @SerializedName("nomcorto_carrera")
    private String nomCortoCarrera;

    private int periodo;

    @SerializedName("nuevo_periodo")
    private int nuevoPeriodo;

    private double promedio;

    private int creditos;

    private String registro;

    private String inscrito;

    public String getNombreCarrera() {
        return nombreCarrera;
    }

    public void setNombreCarrera(String nombreCarrera) {
        this.nombreCarrera = nombreCarrera;
    }

    public String getNomCortoCarrera() {
        return nomCortoCarrera;
    }

    public void setNomCortoCarrera(String nomCortoCarrera) {
        this.nomCortoCarrera = nomCortoCarrera;
    }

    public int getPeriodo() {
        return periodo;
    }

    public void setPeriodo(int periodo) {
        this.periodo = periodo;
    }

    public int getNuevoPeriodo() {
        return nuevoPeriodo;
    }

    public void setNuevoPeriodo(int nuevoPeriodo) {
        this.nuevoPeriodo = nuevoPeriodo;
    }

    public double getPromedio() {
        return promedio;
    }

    public void setPromedio(double promedio) {
        this.promedio = promedio;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    public String getRegistro() {
        return registro;
    }

    public void setRegistro(String registro) {
        this.registro = registro;
    }

    public String getInscrito() {
        return inscrito;
    }

    public void setInscrito(String inscrito) {
        this.inscrito = inscrito;
    }
}
