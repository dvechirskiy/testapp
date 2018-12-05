package ws.cpcs.testapp.services;

import lombok.AllArgsConstructor;
import ws.cpcs.testapp.AppConfig;

public class Producer extends Task {
    public Producer(String name) {
        super(name);
    }

    @Override
    public int changeValue() {
        return AppConfig.COUNTER.incrementAndGet();
    }
}
