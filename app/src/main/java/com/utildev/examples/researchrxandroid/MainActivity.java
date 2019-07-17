package com.utildev.examples.researchrxandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;

import com.jakewharton.rxbinding3.view.RxView;

import org.reactivestreams.Subscription;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import kotlin.Unit;
import okhttp3.ResponseBody;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "aaa";
    private CompositeDisposable disposable = new CompositeDisposable();
    private SearchView searchView;
    private long timeSinceLastRequest; // for log printouts only. Not part of logic.

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

//        Observable.range(0, 5)
//                .repeat(3)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<Integer>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(Integer integer) {
//
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

//        Observable.timer(5, TimeUnit.SECONDS)
//                .subscribeOn(Schedulers.io())
//                .takeWhile(new Predicate<Long>() {
//                    @Override
//                    public boolean test(Long aLong) throws Exception {
//                        Log.d(TAG, "test: " + Thread.currentThread().getName());
//                        return aLong <= 5;
//                    }
//                })
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<Long>() {
//                    long time = 0;
//
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                        time = System.currentTimeMillis() / 1000;
//                    }
//
//                    @Override
//                    public void onNext(Long aLong) {
//                        Log.d(TAG, "onNext: " + aLong);
//                        Log.d(TAG, "onNext: " + ((System.currentTimeMillis()/1000) - time) + " seconds have elapsed.");
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        Log.d(TAG, "onComplete: ");
//                    }
//                });

//        Task[] list = new Task[5];
//        list[0] = (new Task("Take out the trash", true, 3));
//        list[1] = (new Task("Walk the dog", false, 2));
//        list[2] = (new Task("Make my bed", true, 1));
//        list[3] = (new Task("Unload the dishwasher", false, 0));
//        list[4] = (new Task("Make dinner", true, 5));
//
//        Observable<Task> observable = Observable
//                .fromIterable(DataSource.createTaskList())
//                .subscribeOn(Schedulers.io())
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
//
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        });

//        MainViewModel viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
//        try {
//            viewModel.makeFutureQuery().get()
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe(new Observer<ResponseBody>() {
//                        @Override
//                        public void onSubscribe(Disposable d) {
//                            Log.d(TAG, "onSubscribe: ");
//                        }
//
//                        @Override
//                        public void onNext(ResponseBody responseBody) {
//                            try {
//                                Log.d(TAG, "onNext: " + responseBody.string());
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            }
//                        }
//
//                        @Override
//                        public void onError(Throwable e) {
//                            Log.d(TAG, "onError: " + e.getMessage());
//                        }
//
//                        @Override
//                        public void onComplete() {
//                            Log.d(TAG, "onComplete: ");
//                        }
//                    });
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

//        viewModel.makeReactiveQuery().observe(this, new Observer<ResponseBody>() {
//            @Override
//            public void onChanged(ResponseBody responseBody) {
//                try {
//                    Log.d(TAG, "onNext: " + responseBody.string());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        });

        // Distinct
//        Observable<Task> taskObservable = Observable
//                .fromIterable(DataSource.createTaskList())
//                .distinct(new Function<Task, Integer>() {
//                    @Override
//                    public Integer apply(Task task) throws Exception {
//                        return task.getPriority();
//                    }
//                })
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());
//
//        taskObservable.subscribe(new Observer<Task>() {
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
//
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        });

//        Observable<Task> observable = Observable
//                .fromIterable(DataSource.createTaskList())
//                .takeWhile(new Predicate<Task>() {
//                    @Override
//                    public boolean test(Task task) throws Exception {
//                        Log.d(TAG, "test: " + task.getDescription());
//                        return task.isComplete();
//                    }
//                })
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());
//
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
//                Log.d(TAG, "onComplete: ");
//            }
//        });

        // Map
//        Observable.fromIterable(DataSource.createTaskList())
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .map(new Function<Task, Task>() {
//                    @Override
//                    public Task apply(Task task) throws Exception {
//                        Log.d(TAG, "apply: " + task.getDescription() + " - " + task.isComplete());
//                        task.setComplete(true);
//                        return task;
//                    }
//                })
//                .subscribe(new Observer<Task>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(Task task) {
//                        Log.d(TAG, "onNext: " + task.getDescription() + " - " + task.isComplete());
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

        // Buffer
//        RxView.clicks(findViewById(R.id.btn))
//                .map(new Function<Unit, Integer>() {
//                    @Override
//                    public Integer apply(Unit unit) throws Exception {
//                        return 1;
//                    }
//                })
//                .buffer(4, TimeUnit.SECONDS)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<List<Integer>>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                        disposable.add(d);
//                    }
//
//                    @Override
//                    public void onNext(List<Integer> integers) {
//                        Log.d(TAG, "onNext: You clicked " + integers.size() + " times in 4 seconds!");
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

        searchView = findViewById(R.id.searchView);
        timeSinceLastRequest = System.currentTimeMillis();

        Observable<String> stringObservable = Observable
                .create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                        // Listen for text input into the SearchView
                        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                            @Override
                            public boolean onQueryTextSubmit(String query) {
                                return false;
                            }

                            @Override
                            public boolean onQueryTextChange(String newText) {
                                if (!emitter.isDisposed()) {
                                    emitter.onNext(newText);
                                }
                                return false;
                            }
                        });
                    }
                })
                .debounce(1000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io());

        stringObservable.subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                disposable.add(d);
            }

            @Override
            public void onNext(String s) {
                Log.d(TAG, "onNext: time  since last request: " + (System.currentTimeMillis() - timeSinceLastRequest));
                Log.d(TAG, "onNext: search query: " + s);
                timeSinceLastRequest = System.currentTimeMillis();
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: ");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: ");
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.clear();
    }
}
