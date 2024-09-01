package com.mindera.mindswap;

import java.util.Random;


public class Consumer implements Runnable {
    private final LimitedSharedResource resources;
    private final Random random;

    public Consumer(LimitedSharedResource resources) {
        this.resources = resources;
        random = new Random();
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            synchronized (resources) {
                resources.consumeItems(getRandomItemsToConsume());
            }
        }
    }

    private int getRandomItemsToConsume() {
        return random.nextInt(resources.getSharedResources().size());
    }

    public void start() {
        Thread thread = new Thread(this);
        thread.start();
    }
}
