package com.example.rxtutor;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class CallableLongAction implements Callable<Integer> {

    private final String data;

    public CallableLongAction(String data) {
        this.data = data;
    }

    @Override
    public Integer call() throws Exception {
        return longAction(data);
    }

    private Integer longAction(String text) {

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return Integer.parseInt(text);
    }

}
