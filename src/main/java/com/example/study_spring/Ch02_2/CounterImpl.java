package com.example.study_spring.Ch02_2;

public class CounterImpl implements Counter {
    private int count;
    @Override
    public void increase() {
        count++;
    }

    @Override
    public int getCount() {
        return count;
    }
}
