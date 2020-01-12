package com.winds.dagger2java.mvvm;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.winds.dagger2java.App;
import com.winds.dagger2java.model.ApiResponse;
import com.winds.dagger2java.network.ApiService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivityViewModel extends ViewModel {

    private ApiService apiService = App.getInstance().getAppComponent().provideApiService();
    private Gson gsonBuilder = App.getInstance().getAppComponent().provideGson();
    private MutableLiveData<String> liveData = new MutableLiveData<>();

    public MutableLiveData<String> getData() {
        return liveData;
    }


    public void setData(String s) {
        initApiCall();
    }

    private void initApiCall() {
        apiService.getResponse()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<ApiResponse>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<ApiResponse> apiResponses) {
                        Map<String, String> map = new HashMap<>();
                        for (ApiResponse r: apiResponses) {
                            map.put("Ram", r.getImageurl());
                        }
                        String json = gsonBuilder.toJson(map, HashMap.class);
                        liveData.setValue(json);

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


              /*  .enqueue(new Callback<List<ApiResponse>>() {
            @Override
            public void onResponse(Call<List<ApiResponse>> call, Response<List<ApiResponse>> response) {
                Map<String, String> map = new HashMap<>();
                map.put("Ram",response.message());
                String json = gsonBuilder.toJson(map, HashMap.class);
                liveData.setValue(json);
            }

            @Override
            public void onFailure(Call<List<ApiResponse>> call, Throwable t) {

            }*/

    }


}
