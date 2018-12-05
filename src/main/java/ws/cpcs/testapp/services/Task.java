package ws.cpcs.testapp.services;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ws.cpcs.testapp.AppConfig;

import java.util.concurrent.Callable;
import java.util.concurrent.locks.ReentrantLock;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public abstract class Task implements Callable<String> {
    static ReentrantLock counterLock = new ReentrantLock(true);

    String name;

    @Override
    public String call() {
        int counter = AppConfig.COUNTER.get();
        while (counter < AppConfig.MAX_COUNTER_NUMBER && counter > AppConfig.MIN_COUNTER_NUMBER) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            counterLock.lock();
            counter = AppConfig.COUNTER.get();
            try {
                if (counter < AppConfig.MAX_COUNTER_NUMBER && counter > AppConfig.MIN_COUNTER_NUMBER) {
                    counter = changeValue();
                    System.out.println(name + " changed the counter: " + counter);
                    if (counter >= AppConfig.MAX_COUNTER_NUMBER || counter <= AppConfig.MIN_COUNTER_NUMBER) {
                        return "done";
                    }
                } else {
                    counter = AppConfig.COUNTER.get();
                }
            } finally {
                counterLock.unlock();
            }
        }
        return null;
    }

    public abstract int changeValue();
}
