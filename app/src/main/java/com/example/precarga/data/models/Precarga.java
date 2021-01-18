package com.example.precarga.data.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "precargas")
public class Precarga {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @NonNull
    public String alumnoControl;

    @NonNull
    public String periodo;

    @NonNull
    public String materiaClave;

    public int materiaCreditos;

    @NonNull
    public String grupo;

    public Precarga(@NonNull String alumnoControl, @NonNull String periodo, @NonNull String materiaClave, int materiaCreditos, @NonNull String grupo) {
        this.id = 101;
        this.alumnoControl = alumnoControl;
        this.periodo = periodo;
        this.materiaClave = materiaClave;
        this.materiaCreditos = materiaCreditos;
        this.grupo = grupo;
    }
}
