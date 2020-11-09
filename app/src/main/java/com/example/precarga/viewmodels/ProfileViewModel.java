package com.example.precarga.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ProfileViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ProfileViewModel(){
        mText = new MutableLiveData<>();
        mText.setValue("Alv compa");
    }

    public LiveData<String> getText(){return mText;}
}
