package com.example.boardandroid.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.boardandroid.repository.MainRepository;
import com.example.boardandroid.repository.model.request.GetNickNameRequest;
import com.example.boardandroid.repository.model.response.GetNickNameResponse;

public class MainViewModel extends ViewModel {
    MutableLiveData<GetNickNameResponse> mGetNickNameResultMutableData = new MutableLiveData<>();

    MainRepository mMainRepository;

    public MainViewModel() {
        mMainRepository = new MainRepository();
    }

    public void getNickName(int userId) {
        mMainRepository.getNickNameRemote(new GetNickNameRequest(userId), new MainRepository.IGetNickNameResponse() {
            @Override
            public void onResponse(GetNickNameResponse getNickNameResponse) {
                mGetNickNameResultMutableData.postValue(getNickNameResponse);
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

    public LiveData<GetNickNameResponse> getNickNameResponseLiveData() {
        return mGetNickNameResultMutableData;
    }
}
