package com.example.precarga.data;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.precarga.data.models.Materia;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Materia.class}, version = 1, exportSchema = false)
public abstract class PrecargaRoomDatabase extends RoomDatabase {

    public abstract MateriaDao materiaDao();

    private static volatile PrecargaRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static PrecargaRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (PrecargaRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            PrecargaRoomDatabase.class, "precarga_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            databaseWriteExecutor.execute(() -> {
                MateriaDao dao = INSTANCE.materiaDao();
                dao.deleteAll();
                dao.insert(new Materia("03CAAC8", "SOFTWARE DE SISTEMAS II", "SOFT. DE SIS.II", 8, 4, 0));
                dao.insert(new Materia("03CABC8", "SISTEMAS ABIERTOS", "SIST ABIERTOS", 10, 4, 2));
                dao.insert(new Materia("03CAEC6", "BASES DE DATOS II", "BASES DATOS II", 0, 4, 2));
                dao.insert(new Materia("03CAFC7", "REDES DE COMPUTADORAS II", "REDES DE COM II", 12, 4, 4));
                dao.insert(new Materia("03CAFC8", "BASES DE DATOS DISTRIBUIDAS", "BASES DE D.DIST", 10, 4, 2));
                dao.insert(new Materia("03CAFC9", "SISTEMAS OPERATIVOS UNIX", "SIST OPER UNIX", 10, 4, 2));
                dao.insert(new Materia("05EGE5", "RELACIONES PUBLICAS", "REL. PUB.", 8, 3, 0));
                dao.insert(new Materia("ACB9309", "PROGRAMACION", "PROGRAMACION", 8, 4, 0));
                dao.insert(new Materia("ACB9311", "METODOS NUMERICOS", "MET.NUMERICOS", 8, 4, 0));
                Log.d("####", "rows inserted!");

            });

        }
    };

}
