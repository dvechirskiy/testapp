package ws.cpcs.testapp.services;

import ws.cpcs.testapp.AppConfig;

import java.util.concurrent.atomic.AtomicInteger;

public class Consumer extends Task{
    static final private AtomicInteger number = new AtomicInteger(0);
    static final private String TASK_NAME = "Consumer";

    public Consumer() {
        super(String.format("%s %d", TASK_NAME, number.incrementAndGet()));
    }

    public Consumer(String name) {
        super(name);
    }

    @Override
    public int changeValue() {
        return AppConfig.COUNTER.decrementAndGet();
    }
}
