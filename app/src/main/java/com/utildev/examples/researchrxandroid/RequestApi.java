package com.utildev.examples.researchrxandroid;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface RequestApi {
    @GET("todos/1")
    Observable<ResponseBody> makeObservableQuery();

    @GET("todos/1")
    Call<ResponseBody> makeObservableQuery1();
}