package com.utildev.examples.researchrxandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Observable<Task> taskObservable = Observable
                .fromIterable(DataSource.createTaskList())
                .subscribeOn(Schedulers.io())
                .filter(new Predicate<Task>() {
                    @Override
                    public boolean test(Task task) {
                        Log.d("aaa", "test: " + Thread.currentThread().getName());
                        Log.d("aaa", "test: " + task.getDescription());
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        return task.isComplete();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread());

        taskObservable.subscribe(new Observer<Task>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d("aaa", "onSubscribe: " + Thread.currentThread().getName());
            }

            @Override
            public void onNext(Task task) {
                Log.d("aaa", "onNext: " + Thread.currentThread().getName());
                Log.d("aaa", "onNext: " + task.getDescription());
            }

            @Override
            public void onError(Throwable e) {
                Log.d("aaa", "onError: " + e);
            }

            @Override
            public void onComplete() {
                Log.d("aaa", "onComplete: ");
            }
        });
    }
}
