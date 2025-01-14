package com.mindera.mindswap;

/**
 * Main class that demonstrates the Producer-Consumer pattern
 */
public class Main {

    public static void main(String[] args) {

        // Create shared resource with limited capacity
        LimitedSharedResource limitedSharedResource = new LimitedSharedResource();

        // Create one producer
        Producer producer1 = new Producer(limitedSharedResource);

        // Create two consumers
        Consumer consumer1 = new Consumer(limitedSharedResource);
        Consumer consumer2 = new Consumer(limitedSharedResource);

        // Start all threads
        producer1.start();

        consumer1.start();
        consumer2.start();
    }
}
