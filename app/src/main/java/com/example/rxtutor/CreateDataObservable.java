package com.example.rxtutor;

import android.os.SystemClock;

import io.reactivex.Observable;
import io.reactivex.Observer;

public class CreateDataObservable {

    public Observable<Integer> getInt() {
        return new Observable<Integer>() {
            @Override
            protected void subscribeActual(Observer<? super Integer> observer) {
                for (int i = 0; i < 5; i++) {
                    observer.onNext(i);
                    SystemClock.sleep(1000);
                }
                observer.onComplete();
            }
        };
    }
}
