package com.example.precarga.data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "materias")
public class Materia {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @NonNull
    public String nombre;

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
