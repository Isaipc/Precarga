package com.example.precarga.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PrecargaViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public PrecargaViewModel(){
        mText = new MutableLiveData<>();
        mText.setValue("'Precarga' text from ViewModel ");
    }

    public LiveData<String> getText(){ return mText; }
}
