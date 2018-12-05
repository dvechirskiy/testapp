package ws.cpcs.testapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.concurrent.ListenableFutureCallback;
import ws.cpcs.testapp.domain.transaction.Transaction;
import ws.cpcs.testapp.repository.transaction.TransactionRepository;

public class TaskListenableFutureCallback implements ListenableFutureCallback<String> {

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public void onFailure (Throwable exception) {
        System.out.println("Failure message: " + exception.getMessage());
        exception.printStackTrace();
    }

    @Override
    public void onSuccess (String result) {
        if (result != null) {
            transactionRepository.save(new Transaction("The counter was exceeded a limit value"));
        }
    }

}
