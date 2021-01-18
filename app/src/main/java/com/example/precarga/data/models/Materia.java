package com.example.precarga.data.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "materias")
public class Materia {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @NonNull
    public String nombre;

    @SerializedName("nombre_corto")
    @NonNull
    public String nombreCorto;

    @NonNull
    public String clave;

    public int creditos;

    public int teoricas;

    public int practicas;

    public String tipo;

    public Materia(
            @NonNull String clave,
            @NonNull String nombre,
            @NonNull String nombreCorto,
            int creditos,
            int teoricas,
            int practicas) {
        this.id = 0;
        this.nombre = nombre;
        this.nombreCorto = nombreCorto;
        this.clave = clave;
        this.creditos = creditos;
        this.teoricas = teoricas;
        this.practicas = practicas;
        this.tipo = "N";
    }
}
