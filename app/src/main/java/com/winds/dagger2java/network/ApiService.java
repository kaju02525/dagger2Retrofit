package com.winds.dagger2java.network;

import com.winds.dagger2java.model.ApiResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiService {

    @GET("/demos/marvel")
    Observable<List<ApiResponse>> getResponse();
}
