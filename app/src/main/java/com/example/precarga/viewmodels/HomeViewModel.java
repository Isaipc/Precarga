package com.example.precarga.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mSemestre;
    private MutableLiveData<String> mPorcentaje;
    private MutableLiveData<String> mPromedio;
    private MutableLiveData<String> mEspeciales;
    private MutableLiveData<String> mRepeticion;
    private MutableLiveData<String> mCredAcum;
    private MutableLiveData<String> mFInscripcion;
    private MutableLiveData<String> mHInscripcion;
    private MutableLiveData<String> mLimiteCarga;

    public HomeViewModel() {
        mSemestre = new MutableLiveData<>();
        mSemestre.setValue("9°");
        mPromedio = new MutableLiveData<>();
        mPorcentaje = new MutableLiveData<>();
        mEspeciales = new MutableLiveData<>();
        mRepeticion = new MutableLiveData<>();
        mCredAcum = new MutableLiveData<>();
        mFInscripcion = new MutableLiveData<>();
        mHInscripcion = new MutableLiveData<>();
        mLimiteCarga = new MutableLiveData<>();
    }

    public LiveData<String> getSemestre() { return mSemestre; }
    public LiveData<String> getPromedio() { return mPromedio; }
    public LiveData<String> getPorcentaje() { return mPorcentaje; }
    public LiveData<String> getEspeciales() { return mEspeciales; }
    public LiveData<String> getRepeticion() { return mRepeticion; }
    public LiveData<String> getCredAcum() { return mCredAcum; }
    public LiveData<String> getFInscripcion() { return mFInscripcion; }
    public LiveData<String> getHInscripcion() { return mHInscripcion; }
    public LiveData<String> getLimiteCarga() { return mLimiteCarga; }
}