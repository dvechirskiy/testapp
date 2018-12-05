package ws.cpcs.testapp.api.endpoints.transaction;


import lombok.Data;
import javax.validation.constraints.NotEmpty;

@Data
class TransactionForm {
    @NotEmpty
    private Integer numberOfProducers;
    @NotEmpty
    private Integer numberOfConsumers;
}
