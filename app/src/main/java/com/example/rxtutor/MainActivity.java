package com.example.rxtutor;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableObserver;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleObserver;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.PublishSubject;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "TAG";

    CompositeDisposable disposables = new CompositeDisposable();

    DisposableObserver disposableObserver;

    CreateDataObservable dataObservable = new CreateDataObservable();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Observer<Integer> obr = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                disposables.add(d);
            }

            @Override
            public void onNext(Integer integer) {
                Log.d(TAG, "onNext: " + integer);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
        dataObservable.getInt().subscribe(obr);




        /*Observable<Integer> o = Observable.create(s -> {
            s.onNext(1);
            s.onNext(2);
            s.onNext(3);
            s.onComplete();
        });

        Observer<Integer> or = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer integer) {
                Log.d(TAG, "onNext: " + integer);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
        o.subscribe(or);*/
        /*o.map(i -> "Число " + i)
                .subscribe(s -> {
                    Log.d(TAG, "accept: " + s);
                }).dispose();*/

       /* Observable<String> myObservable = Observable.just("a", "bb", "ccc", "dddd", "eeeee");

        Disposable disposable = myObservable.doAfterNext(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.d(TAG, "accept: " + String.format("About to process emission: '%s'", s));
            }
        })
                .map(new Function<String, Integer>() {
                    @Override
                    public Integer apply(String s) throws Exception {
                        return s.length();
                    }
                }).subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer x) throws Exception {
                        System.out.println(x);
                    }
                });*/

        //Single
       /* Single<Task> taskSingle = Single
                .create(new SingleOnSubscribe<Task>() {
                    @Override
                    public void subscribe(SingleEmitter<Task> emitter) throws Exception {
                        Task task = new Task("Alex");
                        emitter.onSuccess(task);
                    }
                })
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread());


        SingleObserver<Task> singleObserver = new SingleObserver<Task>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onSuccess(Task task) {
                Log.d(TAG, "onSuccess: " + task.getName());
            }

            @Override
            public void onError(Throwable e) {
            }
        };
        taskSingle.subscribe(singleObserver);*/
        //--------------------------------------

        //Completable
        /*Task task1 = new Task("MMMMM");
        Completable completable = Completable
                .create(new CompletableOnSubscribe() {
                    @Override
                    public void subscribe(CompletableEmitter emitter) throws Exception {
                        if (!emitter.isDisposed()) {
                            Thread.sleep(2000);
                            emitter.onComplete();
                        }
                    }
                })
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread());

        CompletableObserver completableObserver = new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: ");
            }

            @Override
            public void onError(Throwable e) {
            }
        };
        completable.subscribe(completableObserver);*/
        //----------------------------------------

        //Create
        /*Observable
                .create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                        emitter.onNext("Hello");
                        emitter.onComplete();
                    }
                }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                Log.d(TAG, "onNext: " + s);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });*/
        //---------------------------------


        /*getUserObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Function<Task, Observable<Task>>() {
                    @Override
                    public Observable<Task> apply(Task task) throws Exception {
                        return getAddress(task);
                    }
                })
                .subscribe(new Observer<Task>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Task task) {
                        Log.d(TAG, "onNext: " + task.getName() + "_" + task.getAddress().getAddress());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

    private Observable<Task> getUserObservable() {
        return Observable
                .create(new ObservableOnSubscribe<Task>() {
                    @Override
                    public void subscribe(ObservableEmitter<Task> emitter) throws Exception {
                        for (Task task : DataSource.createTasksList()) {
                            if (!emitter.isDisposed()) {
                                emitter.onNext(task);
                            }
                        }
                        if (!emitter.isDisposed()) {
                            emitter.onComplete();
                        }
                    }
                }).subscribeOn(Schedulers.io());
    }

    private Observable<Task> getAddress(final Task task) {
        final String[] adres = new String[]{
                "1600 Amphitheatre Parkway, Mountain View, CA 94043",
                "2300 Traverwood Dr. Ann Arbor, MI 48105",
                "500 W 2nd St Suite 2900 Austin, TX 78701",
                "355 Main Street Cambridge, MA 02142"
        };
        return Observable
                .create(new ObservableOnSubscribe<Task>() {
                    @Override
                    public void subscribe(ObservableEmitter<Task> emitter) throws Exception {
                        Address address = new Address();
                        address.setAddress(adres[new Random().nextInt(2) + 0]);

                        if (!emitter.isDisposed()) {
                            task.setAddress(address);
                        }
                        int sleepTime = new Random().nextInt(1000) + 500;

                        Thread.sleep(sleepTime);
                        emitter.onNext(task);
                        emitter.onComplete();
                    }
                }).subscribeOn(Schedulers.io());*/


        //flatMap + filter
        /*Observable<String> observable = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .subscribeOn(Schedulers.io())
                .filter(new Predicate<String>() {
                    @Override
                    public boolean test(String s) throws Exception {
                        return s.length() != 5;
                    }
                })
                .flatMap(new Function<String, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(String s) throws Exception {
                        return Observable.fromArray(s.split(""));
                    }
                })
                .observeOn(AndroidSchedulers.mainThread());

                observable.subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        Log.d(TAG, "onNext: " + s);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });*/
        //-----------------------------------

       /* Observable<Task> observable = Observable
                .fromIterable(DataSource.createTasksList())
                .subscribeOn(Schedulers.io());

        observable.buffer(2)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Task>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Task> tasks) {
                        Log.d(TAG, "onNext: bundle results: -------------------");
                        for (Task task : tasks) {
                            Log.d(TAG, "onNext: " + task.getName());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });*/


        /*-----------------------------------------------*/
        /*RxJavaPlugins.setOnObservableSubscribe(new BiFunction<Observable, Observer, Observer>() {
            @Override
            public Observer apply(Observable observable, Observer observer) throws Exception {
                if (!observable.getClass().getName().toLowerCase().contains("map")) {
                    return observer;
                }

                Log.d(TAG, "Started: ");

                class SignalTracker implements Observer<Object>, Disposable {
                    Disposable upstream;

                    @Override
                    public void onSubscribe(Disposable d) {
                        upstream = d;
                        observer.onSubscribe(this);
                    }

                    @Override
                    public void onNext(Object o) {
                        observer.onNext(o);
                    }

                    @Override
                    public void onError(Throwable t) {
                        observer.onError(t);
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "Completed: ");
                        observer.onComplete();
                    }

                    @Override
                    public void dispose() {
                        upstream.dispose();
                    }

                    @Override
                    public boolean isDisposed() {
                        return upstream.isDisposed();
                    }
                }
                return new SignalTracker();
            }
        });

        Observable<Integer> observable = Observable.range(1, 5)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .map(new Function<Integer, Integer>() {
                    @Override
                    public Integer apply(Integer integer) throws Exception {
                        try {
                            TimeUnit.SECONDS.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        return integer * 3;
                    }
                });

        observable.subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer x) throws Exception {
                Log.d(TAG, "accept: " + x);
            }
        });

        try {
            Thread.sleep(6000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposables.clear();
    }
}
