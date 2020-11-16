package com.example.precarga.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MateriaDao {

    @Query("DELETE FROM materias")
    void deleteAll();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Materia materia);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAll(Materia ... materias);

    @Query("SELECT * FROM materias")
    LiveData<List<Materia>> getAll();

    @Query("SELECT * FROM materias")
    List<Materia> getMaterias();

    @Query("SELECT * FROM materias WHERE id = :id")
    LiveData<Materia> getById(int id);
}
