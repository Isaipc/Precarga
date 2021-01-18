package com.example.precarga.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.precarga.data.models.Precarga;

import java.util.List;

@Dao
public interface PrecargaDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Precarga precarga);

    @Query("SELECT * FROM precargas WHERE alumnoControl = :control")
    LiveData<List<Precarga>> getPrecargaByControl(String control);
}
