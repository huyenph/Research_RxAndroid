package com.utildev.examples.researchrxandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import org.reactivestreams.Subscription;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "aaa";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Creating Observable
//        Observable<Task> taskObservable = Observable
//                .fromIterable(DataSource.createTaskList())
//                .subscribeOn(Schedulers.io())
//                .filter(new Predicate<Task>() {
//                    @Override
//                    public boolean test(Task task) {
//                        Log.d("aaa", "test: " + Thread.currentThread().getName());
//                        Log.d("aaa", "test: " + task.getDescription());
//                        try {
//                            Thread.sleep(1000);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                        return task.isComplete();
//                    }
//                })
//                .observeOn(AndroidSchedulers.mainThread());

//        taskObservable.subscribe(new Observer<Task>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//                Log.d("aaa", "onSubscribe: " + Thread.currentThread().getName());
//            }
//
//            @Override
//            public void onNext(Task task) {
//                Log.d("aaa", "onNext: " + Thread.currentThread().getName());
//                Log.d("aaa", "onNext: " + task.getDescription());
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                Log.d("aaa", "onError: " + e);
//            }
//
//            @Override
//            public void onComplete() {
//                Log.d("aaa", "onComplete: ");
//            }
//        });

        // Flowable
//        Flowable.range(0, 1000000)
//                .onBackpressureBuffer()
//                .observeOn(Schedulers.computation())
//                .subscribe(new FlowableSubscriber<Integer>() {
//                    @Override
//                    public void onSubscribe(Subscription s) {
//
//                    }
//
//                    @Override
//                    public void onNext(Integer integer) {
//                        Log.d("aaa", "onNext: " + integer);
//                    }
//
//                    @Override
//                    public void onError(Throwable t) {
//                        Log.d("aaa", "onError: " + t);
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });

//        final Task task = new Task("create operator", false, 3);
//        Observable<Task> singleTaskObservable = Observable
//                .create((ObservableOnSubscribe<Task>) emitter -> {
//                    for (Task t: DataSource.createTaskList()) {
//                        if (!emitter.isDisposed()) {
//                            emitter.onNext(t);
//                        }
//                    }
//                    if (!emitter.isDisposed()) {
//                        emitter.onComplete();
//                    }
//                })
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());

//        Observable<Task> singleTaskObservable = Observable
//                .create(new ObservableOnSubscribe<Task>() {
//                    @Override
//                    public void subscribe(ObservableEmitter<Task> emitter) throws Exception {
//                        Log.d(TAG, "subscribeCreate: " + Thread.currentThread().getName());
//                        for (Task t: DataSource.createTaskList()) {
//                            if (!emitter.isDisposed()) {
//                                emitter.onNext(t);
//                            }
//                        }
//                        if (!emitter.isDisposed()) {
//                            emitter.onNext(task);
//                            emitter.onComplete();
//                        }
//                    }
//                })
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());

//        singleTaskObservable.subscribe(new Observer<Task>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//                Log.d(TAG, "onSubscribe: " + Thread.currentThread().getName());
//            }
//
//            @Override
//            public void onNext(Task task) {
//                Log.d(TAG, "onNext: " + task.getDescription());
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                Log.d(TAG, "onError: ");
//            }
//
//            @Override
//            public void onComplete() {
//                Log.d(TAG, "onComplete: ");
//            }
//        });

//        Observable.just("first", "second", "third", "fourth", "fifth", "sixth",
//                "seventh", "eighth", "ninth", "tenth")
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<String>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(String s) {
//                        Log.d(TAG, "onNext: " + s);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });

//        Observable<Task> observable = Observable
//                .range(0, DataSource.createTaskList().size())
//                .subscribeOn(Schedulers.io())
//                .map(new Function<Integer, Task>() {
//                    @Override
//                    public Task apply(Integer integer) throws Exception {
//                        Log.d(TAG, "apply: " + Thread.currentThread().getName());
//                        return new Task("this is a task with priority: " + integer, false, integer);
//                    }
//                })
//                .takeWhile(new Predicate<Task>() {
//                    @Override
//                    public boolean test(Task task) throws Exception {
//                        return task.getPriority() < 9;
//                    }
//                })
//                .observeOn(AndroidSchedulers.mainThread());
//        observable.subscribe(new Observer<Task>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//
//            }
//
//            @Override
//            public void onNext(Task task) {
//                Log.d(TAG, "onNext: " + task.getDescription());
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                Log.d(TAG, "onError: ");
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        });

      Observable.range(0, 5)
                .repeat(3)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
