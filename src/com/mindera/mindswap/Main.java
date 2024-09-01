package com.mindera.mindswap;


public class Main {

    public static void main(String[] args) {

        LimitedSharedResource limitedSharedResource = new LimitedSharedResource();

        Producer producer1 = new Producer(limitedSharedResource);

        Consumer consumer1 = new Consumer(limitedSharedResource);
        Consumer consumer2 = new Consumer(limitedSharedResource);

        producer1.start();

        consumer1.start();
        consumer2.start();
    }
}
