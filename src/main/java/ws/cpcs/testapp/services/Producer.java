package ws.cpcs.testapp.services;

import ws.cpcs.testapp.AppConfig;

import java.util.concurrent.atomic.AtomicInteger;

public class Producer extends Task {
    static final private AtomicInteger number = new AtomicInteger(0);
    static final private String TASK_NAME = "Producer";

    Producer(String name) {
        super(name);
    }

    public Producer() {
        super(String.format("%s %d", TASK_NAME, number.incrementAndGet()));
    }

    @Override
    public int changeValue() {
        return AppConfig.COUNTER.incrementAndGet();
    }
}
