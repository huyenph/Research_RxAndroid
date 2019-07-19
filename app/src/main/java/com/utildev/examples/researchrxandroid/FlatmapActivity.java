package com.utildev.examples.researchrxandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.utildev.examples.researchrxandroid.model.Comment;
import com.utildev.examples.researchrxandroid.model.Post;
import com.utildev.examples.researchrxandroid.utils.ServiceGenerator;

import java.util.List;
import java.util.Random;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class FlatmapActivity extends AppCompatActivity implements RecyclerAdapter.OnPostClickListener {
    private static final String TAG = "aaa";

    //ui
    private RecyclerView recyclerView;

    // vars
    private CompositeDisposable disposables = new CompositeDisposable();
    private RecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flatmap);
        initRecyclerView();

        getPostObservable()
                .subscribeOn(Schedulers.io())
                .switchMap(new Function<Post, ObservableSource<Post>>() {
                    @Override
                    public ObservableSource<Post> apply(Post post) throws Exception {
                        return getCommentObservable(post);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Post>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposables.add(d);
                    }

                    @Override
                    public void onNext(Post post) {
                        updatePost(post);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: ");
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    private Observable<Post> getPostObservable() {
        return ServiceGenerator.getRequestApi().getPosts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Function<List<Post>, ObservableSource<Post>>() {
                    @Override
                    public ObservableSource<Post> apply(List<Post> posts) throws Exception {
                        adapter.setPosts(posts);
                        return Observable.fromIterable(posts);
                    }
                });
    }

    private void updatePost(final Post p) {
        Observable.fromIterable(adapter.getPosts())
                .filter(new Predicate<Post>() {
                    @Override
                    public boolean test(Post post) throws Exception {
                        return post.getId() == p.getId();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Post>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposables.add(d);
                    }

                    @Override
                    public void onNext(Post post) {
                        Log.d(TAG, "onNext: updating post: " + post.getId() + ", thread: " + Thread.currentThread().getName());
                        adapter.updatePost(post);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: ", e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private Observable<Post> getCommentObservable(final Post p) {
        return ServiceGenerator.getRequestApi().getComment(p.getId())
                .map(new Function<List<Comment>, Post>() {
                    @Override
                    public Post apply(List<Comment> comments) throws Exception {
                        int delay = ((new Random()).nextInt(5) + 1) * 1000; // sleep thread for x ms
                        Thread.sleep(delay);
                        Log.d(TAG, "apply: sleeping thread " + Thread.currentThread().getName() + " for " + delay + "ms");
                        p.setComments(comments);
                        return p;
                    }
                })
                .subscribeOn(Schedulers.io());
    }

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.rv);
        adapter = new RecyclerAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onPostClick(int position) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposables.clear();
    }


}
