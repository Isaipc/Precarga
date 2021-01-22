package com.example.precarga.data.models;

import java.util.List;

public class PeriodoReticula {

    private int periodo;
    private List<Materia> materias;

    public int getPeriodo() {
        return periodo;
    }

    public void setPeriodo(int periodo) {
        this.periodo = periodo;
    }

    public List<Materia> getMaterias() {
        return materias;
    }

    public void setMaterias(List<Materia> materias) {
        this.materias = materias;
    }
}
