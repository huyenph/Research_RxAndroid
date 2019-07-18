package com.utildev.examples.researchrxandroid.utils;

import android.util.Log;

import java.util.concurrent.Callable;

public class Calculator implements Callable<Integer> {
    private int a;
    private int b;

    public Calculator(int a, int b) {
        this.a = a;
        this.b = b;
    }

    private int sum() {
        int sum = this.a + this.b;
        Log.d("aaa", "result: " + a + " + " + b + " = " + sum);
        return sum;
    }

    @Override
    public Integer call() throws Exception {
        return sum();
    }
}