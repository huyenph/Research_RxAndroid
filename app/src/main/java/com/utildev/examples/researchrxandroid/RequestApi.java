package com.utildev.examples.researchrxandroid;

import com.utildev.examples.researchrxandroid.model.Comment;
import com.utildev.examples.researchrxandroid.model.Post;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RequestApi {
    @GET("todos/1")
    Observable<ResponseBody> makeObservableQuery();

    @GET("todos/1")
    Flowable<ResponseBody> makeReactiveQuery();

    @GET("todos/1")
    Call<ResponseBody> makeObservableQuery1();

    @GET("posts")
    Observable<List<Post>> getPosts();

    @GET("posts/{id}")
    Observable<Post> getPost(@Path("id") int id);

    @GET("posts/{id}/comments")
    Observable<List<Comment>> getComment(@Path("id") int id);
}
