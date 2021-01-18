package com.example.precarga.data.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReticulaResponse {

    @SerializedName("total_materias")
    private int totalMaterias;

    @SerializedName("total_creditos")
    private int totalCreditos;

    @SerializedName("materias")
    private List<Materia> materias;

    public int getTotalMaterias() {
        return totalMaterias;
    }

    public void setTotalMaterias(int totalMaterias) {
        this.totalMaterias = totalMaterias;
    }

    public int getTotalCreditos() {
        return totalCreditos;
    }

    public void setTotalCreditos(int totalCreditos) {
        this.totalCreditos = totalCreditos;
    }

    public List<Materia> getMaterias() {
        return materias;
    }

    public void setMaterias(List<Materia> materias) {
        this.materias = materias;
    }
}
