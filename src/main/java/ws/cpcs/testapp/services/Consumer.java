package ws.cpcs.testapp.services;

import ws.cpcs.testapp.AppConfig;

public class Consumer extends Task{

    public Consumer(String name) {
        super(name);
    }

    @Override
    public int changeValue() {
        return AppConfig.COUNTER.decrementAndGet();
    }
}
