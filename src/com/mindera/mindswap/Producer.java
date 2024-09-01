package com.mindera.mindswap;

import java.util.Random;

import static com.mindera.mindswap.Constants.RESOURCES_LIMIT;

public class Producer implements Runnable {
    private final String[] items = {"item_0", "item_1", "item_2", "item_3", "item_4", "item_5"};
    private final LimitedSharedResource resources;
    private final Random random = new Random();

    public Producer(LimitedSharedResource resources) {
        this.resources = resources;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            synchronized (resources) {
                if (resources.getSharedResources().size() <= RESOURCES_LIMIT) {
                    resources.addItem(getRandomItemToProduce());
                }
            }
        }
    }

    private String getRandomItemToProduce() {
        return items[random.nextInt(items.length)];
    }

    public void start() {
        Thread thread = new Thread(this);
        thread.start();
    }
}
