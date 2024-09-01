package com.mindera.mindswap;

import java.util.ArrayList;
import java.util.List;

import static com.mindera.mindswap.Constants.RESOURCES_LIMIT;

public class LimitedSharedResource {
    private static final Object lock = new Object();
    private final ArrayList<String> sharedResources;

    public LimitedSharedResource() {
        sharedResources = new ArrayList<>();
    }

    public void consumeItems(int items) {
        synchronized (lock) {
            if (!sharedResources.isEmpty()) {
                for (int i = 0; i < items; i++) {
                    String item = sharedResources.removeFirst();
                    System.out.println("Consumer consumed: " + item);
                }
            }
        }
    }

    public void addItem(String item) {
        synchronized (lock) {
            if (sharedResources.size() < RESOURCES_LIMIT) {
                sharedResources.add(item);
                System.out.println("New resource added " + item);
                System.out.println("Number of items in shared resources: " + getSharedResources().size());
            }
        }
    }

    public List<String> getSharedResources() {
        return sharedResources;
    }
}
