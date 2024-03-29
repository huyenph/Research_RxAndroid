package com.utildev.examples.researchrxandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;

import com.utildev.examples.researchrxandroid.model.Post;
import com.utildev.examples.researchrxandroid.utils.ServiceGenerator;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

public class SwitchMapActivity extends AppCompatActivity implements RecyclerAdapter.OnPostClickListener {
    private static final String TAG = "aaa";

    //ui
    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    // vars
    private CompositeDisposable disposables = new CompositeDisposable();
    private RecyclerAdapter adapter;
    private PublishSubject<Post> publishSubject = PublishSubject.create();
    private static final int PERIOD = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch_map);
        initRecyclerView();
        retrievePosts();
    }

    private void retrievePosts() {
        ServiceGenerator.getRequestApi().getPosts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Post>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposables.add(d);
                    }

                    @Override
                    public void onNext(List<Post> posts) {
                        adapter.setPosts(posts);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void initSwitchMap() {
        publishSubject
                // apply switchmap operator so only one Observable can be used at a time.
                // it clears the previous one
                .switchMap(new Function<Post, ObservableSource<Post>>() {
                    @Override
                    public ObservableSource<Post> apply(final Post post) throws Exception {
                        return Observable
                                // simulate slow network speed with interval + takeWhile + filter operators
                                .interval(PERIOD, TimeUnit.MILLISECONDS)
                                .subscribeOn(AndroidSchedulers.mainThread())
                                .takeWhile(new Predicate<Long>() { // stop the process if more than 5 seconds passes
                                    @Override
                                    public boolean test(Long aLong) throws Exception {
                                        Log.d(TAG, "test: takeWhile " + Thread.currentThread().getName() + ", aLong: " + aLong);
                                        progressBar.setMax(3000 - PERIOD);
                                        progressBar.setProgress((int) ((aLong * PERIOD) + PERIOD));
                                        return aLong <= (3000 / PERIOD);
                                    }
                                })
                                .filter(new Predicate<Long>() {
                                    @Override
                                    public boolean test(Long aLong) throws Exception {
                                        Log.d(TAG, "test: filter " + aLong);
                                        return aLong >= (3000 / PERIOD);
                                    }
                                })
                                // flatmap to convert Long from the interval operator into a Observable<Post>
                                .subscribeOn(Schedulers.io())
                                .flatMap(new Function<Long, ObservableSource<Post>>() {
                                    @Override
                                    public ObservableSource<Post> apply(Long aLong) throws Exception {
                                        return ServiceGenerator.getRequestApi().getPost(post.getId());
                                    }
                                });
                    }
                })
                .subscribe(new Observer<Post>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposables.add(d);
                    }

                    @Override
                    public void onNext(Post post) {
                        Log.d(TAG, "onNext: done.");
                        navViewPostActivity(post);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.rv);
        progressBar = findViewById(R.id.progress_bar);
        adapter = new RecyclerAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void navViewPostActivity(Post post){
        Intent intent = new Intent(this, ViewPostActivity.class);
        intent.putExtra("post", post);
        startActivity(intent);
    }

    @Override
    public void onPostClick(int position) {
        Log.d(TAG, "onPostClick: ");
        // submit the selected post object to be queried
        publishSubject.onNext(adapter.getPosts().get(position));
    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setProgress(0);
        initSwitchMap();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposables.clear();
    }
}
