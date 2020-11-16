package com.example.precarga.viewmodels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.precarga.data.Materia;
import com.example.precarga.data.Repository;

import java.util.ArrayList;
import java.util.List;

public class PrecargaViewModel extends AndroidViewModel {

    private final LiveData<List<Materia>> mMaterias;

    private Repository mRepository;

    public PrecargaViewModel(@NonNull Application app) {
        super(app);
        mRepository = new Repository(app);
        mMaterias = mRepository.getAll();
    }

    public LiveData<List<Materia>> getAll() {
        return mMaterias;
    }
}
