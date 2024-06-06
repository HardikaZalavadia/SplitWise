package services;

import dtos.Transaction;
import models.*;
import repository.GroupRepository;
import repository.UserExpenseRepository;
import strategies.SettleUpStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserService {
    private GroupRepository groupRepository;
    private UserExpenseRepository userExpenseRepository;

    private SettleUpStrategy settleUpStrategy;

    public UserService(GroupRepository groupRepository, UserExpenseRepository userExpenseRepository, SettleUpStrategy settleUpStrategy) {
        this.groupRepository = groupRepository;
        this.userExpenseRepository = userExpenseRepository;
        this.settleUpStrategy = settleUpStrategy;
    }

    public List<Transaction> settleUser(String userName, String groupName) {

            /*
               1. Get all expenses of a group
               2. Filter only regular expenses -> expenses
               3. For every expense -> find userExpenses (list)
               4. We iterate over all the userExpenses
                  Calculate the extra amount for every user

                  Map of extra amount -> Map<User, Integer>
                     Data : [A:2000, B:-1000]
                  If the type of userExpense is paidBy, extra_amount += amount;
                                                WhoHadToPay, extra_amount -= amount;
               5. Pass this map to another class, which will use heaps and gets me the
                  transactions list.
         */
        Map<User, Integer> extraAmount  = new HashMap<>();
        List<Expense> expenses = groupRepository.getExpenseByGroup(groupName);

        for(Expense expense:expenses){
            if(expense.getExpenseType().equals(ExpenseType.REGULAR)){
                List<UserExpense> userExpenses = userExpenseRepository.findUserExpenseById(expense.getId());
                for(UserExpense userExpense : userExpenses){
                    User user = userExpense.getUser();
                    if(!extraAmount.containsKey(user)){
                        extraAmount.put(user,0);
                    }

                    Integer amount = extraAmount.get(user);
                    if(userExpense.getUserExpenseType().equals(UserExpenseType.HAD_TO_PAY)){
                        amount -= userExpense.getAmount();

                    }
                    else{
                        amount += userExpense.getAmount();
                    }
                    extraAmount.put(user, amount);
                }

            }
        }
        // finding the transactions using extra amount for every user.
        List<Transaction> groupTransaction = settleUpStrategy.settleUpUser(extraAmount);
        List<Transaction> userTransaction = new ArrayList<>();

        for(Transaction transaction : groupTransaction){
            if(transaction.getFromUser().equals(userName)|| transaction.getToUser().equals(userName)){
                userTransaction.add(transaction);
            }
        }
        return userTransaction;
    }

}
