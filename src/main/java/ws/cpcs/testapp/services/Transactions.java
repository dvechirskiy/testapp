package ws.cpcs.testapp.services;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.AsyncListenableTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
@Slf4j
public class Transactions {

    private final AsyncListenableTaskExecutor executor;
    private final ListenableFutureCallback threadListenableCallback;

    @Autowired
    public Transactions(AsyncListenableTaskExecutor  executor, ListenableFutureCallback threadListenableCallback) {
        this.executor = executor;
        this.threadListenableCallback = threadListenableCallback;
    }

    public void addConsumer(Integer number) {
        for(int i=0;i<number;i++) {
            addTask(new Consumer());
        }
    }

    public void addProducer(Integer number) {
        for(int i=0;i<number;i++) {
            addTask(new Producer());
        }
    }

    private void addTask(Task task) {
        ListenableFuture<String> f = executor.submitListenable(task);
        f.addCallback(threadListenableCallback);
    }

}
