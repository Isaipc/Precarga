package com.example.precarga.data.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Reticula {

    @SerializedName("reticula")
    private List<PeriodoReticula> periodosReticula;

    public List<PeriodoReticula> getPeriodosReticula() {
        return periodosReticula;
    }

    public void setPeriodosReticula(List<PeriodoReticula> periodosReticula) {
        this.periodosReticula = periodosReticula;
    }
}
