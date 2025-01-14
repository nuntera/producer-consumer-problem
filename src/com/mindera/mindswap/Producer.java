package com.mindera.mindswap;

import java.util.Random;

/**
 * Producer class that adds items to the shared resource
 */
public class Producer implements Runnable {
    /** Array of possible items to produce */
    private final String[] items = {"item_0", "item_1", "item_2", "item_3", "item_4", "item_5"};
    private final LimitedSharedResource resources;
    private final Random random = new Random();

    /**
     * Constructor for Producer
     * @param resources The shared resource to produce to
     */
    public Producer(LimitedSharedResource resources) {
        this.resources = resources;
    }

    /**
     * Main run loop that continuously produces items
     */
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                // Wait for 500ms between production attempts
                Thread.sleep(500);
                resources.addItem(getRandomItemToProduce());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    /**
     * Generates a random item from the items array
     * @return Random item to produce
     */
    private String getRandomItemToProduce() {
        return items[random.nextInt(items.length)];
    }

    /**
     * Starts the producer thread
     */
    public void start() {
        Thread thread = new Thread(this);
        thread.start();
    }
}
