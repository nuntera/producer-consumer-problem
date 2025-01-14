package com.mindera.mindswap;

import java.util.Random;

/**
 * Consumer class that removes items from the shared resource
 */
public class Consumer implements Runnable {
    private final LimitedSharedResource resources;
    private final Random random;

    /**
     * Constructor for Consumer
     * @param resources The shared resource to consume from
     */
    public Consumer(LimitedSharedResource resources) {
        this.resources = resources;
        random = new Random();
    }

    /**
     * Main run loop that continuously consumes items
     */
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                // Wait for 2 seconds between consumption attempts
                Thread.sleep(2000);
                resources.consumeItems(getRandomItemsToConsume());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    /**
     * Generates a random number of items to consume (1-3)
     * @return Number of items to consume
     */
    private int getRandomItemsToConsume() {
        return random.nextInt(3) + 1;
    }

    /**
     * Starts the consumer thread
     */
    public void start() {
        Thread thread = new Thread(this);
        thread.start();
    }
}
