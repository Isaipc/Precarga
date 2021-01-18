package com.example.precarga.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.precarga.data.models.Materia;

import java.util.List;

public class Repository {
    private MateriaDao mMateriaDao;
    private LiveData<List<Materia>> mMaterias;

    // Note that in order to unit test the WordRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    public Repository(Application application) {
        PrecargaRoomDatabase db = PrecargaRoomDatabase.getDatabase(application);
        mMateriaDao = db.materiaDao();
        mMaterias = mMateriaDao.getAll();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<Materia>> getAll() {
        return mMaterias;
    }

    public List<Materia> getMaterias(){
        return mMateriaDao.getMaterias();
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    public void insert(Materia materia) {
        PrecargaRoomDatabase.databaseWriteExecutor.execute(() -> mMateriaDao.insert(materia));
    }
}


