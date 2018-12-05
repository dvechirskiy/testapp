package ws.cpcs.testapp.services;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class Transactions {

    private final TaskExecutor executor;

    @Autowired
    public Transactions(TaskExecutor executor) {
        this.executor = executor;
    }

    public void addConsumer(Integer number) {
        Integer nextTaskNumber = (((ThreadPoolTaskExecutor)executor).getActiveCount());
        for(int i=0;i<number;i++) {
            nextTaskNumber++;
            addTask(new Consumer("Consumer " + nextTaskNumber));
            log.debug("Consumer is added "  + nextTaskNumber);
        }
    }

    public void addProducer(Integer number) {
        Integer nextTaskNumber = (((ThreadPoolTaskExecutor)executor).getActiveCount());
        for(int i=0;i<number;i++) {
            nextTaskNumber++;
            addTask(new Producer("Producer " + nextTaskNumber));
            log.debug("Producer is added "  + nextTaskNumber);
        }

    }

    private void addTask(Task task) {
        executor.execute(task);
    }

}
