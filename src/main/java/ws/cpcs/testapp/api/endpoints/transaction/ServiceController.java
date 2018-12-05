package ws.cpcs.testapp.api.endpoints.transaction;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ws.cpcs.testapp.AppConfig;
import ws.cpcs.testapp.domain.transaction.Transaction;
import ws.cpcs.testapp.repository.transaction.TransactionRepository;
import ws.cpcs.testapp.services.Transactions;

@RestController
@RequestMapping("/api/v1/service")
@Api(tags = "Service")
public class ServiceController {

    private final TransactionRepository transactionRepository;
    private final Transactions transactions;

    @Autowired
    public ServiceController(TransactionRepository transactionRepository, Transactions transactions) {
        this.transactionRepository = transactionRepository;
        this.transactions = transactions;
    }

    @PutMapping("transactions")
    public ResponseEntity putTransaction(@RequestBody TransactionForm transactionForm) {
        Transaction transaction = new Transaction(transactionForm.getNumberOfProducers(), transactionForm.getNumberOfConsumers());
        transactionRepository.save(transaction);
        transactions.addConsumer(transactionForm.getNumberOfConsumers());
        transactions.addProducer(transactionForm.getNumberOfProducers());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("reset")
    public  ResponseEntity resetCounter(@RequestBody Integer number) {
        AppConfig.COUNTER.set(number);
        return ResponseEntity.ok().build();
    }
}
