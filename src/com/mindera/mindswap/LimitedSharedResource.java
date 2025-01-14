package com.mindera.mindswap;

import java.util.ArrayList;
import java.util.List;

/**
 * Thread-safe shared resource with limited capacity
 */
public class LimitedSharedResource {
    private final ArrayList<String> sharedResources;

    /**
     * Constructor initializes empty resource list
     */
    public LimitedSharedResource() {
        sharedResources = new ArrayList<>();
    }

    /**
     * Consumes a specified number of items from the shared resource
     * @param items Number of items to consume
     */
    public synchronized void consumeItems(int items) {
        while (sharedResources.isEmpty()) {
            try {
                // Wait if no items are available
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
        
        // Consume only available items
        int itemsToConsume = Math.min(items, sharedResources.size());
        for (int i = 0; i < itemsToConsume; i++) {
            String item = sharedResources.removeFirst();
            System.out.println("Consumer consumed: " + item);
        }
        // Notify producers that space is available
        notifyAll();
    }

    /**
     * Adds an item to the shared resource if capacity allows
     * @param item Item to add
     */
    public synchronized void addItem(String item) {
        while (sharedResources.size() >= Constants.RESOURCES_LIMIT) {
            try {
                // Wait if buffer is full
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
        
        sharedResources.add(item);
        System.out.println("New resource added " + item);
        System.out.println("Number of items in shared resources: " + sharedResources.size());
        // Notify consumers that items are available
        notifyAll();
    }

    /**
     * Returns a thread-safe copy of the shared resources
     * @return Copy of shared resources list
     */
    public synchronized List<String> getSharedResources() {
        return new ArrayList<>(sharedResources);
    }
}
