package strategies;

import dtos.Transaction;
import models.User;

import java.util.List;
import java.util.Map;

public interface SettleUpStrategy {
    List<Transaction> settleUpUser(Map<User, Integer> map);
}
