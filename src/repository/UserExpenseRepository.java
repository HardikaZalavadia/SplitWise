package repository;

import models.UserExpense;

import java.util.ArrayList;
import java.util.List;

public class UserExpenseRepository {
    List<UserExpense> userExpense = new ArrayList<>();

    public List<UserExpense> getUserExpense() {
        return userExpense;
    }

    public void setUserExpense(List<UserExpense> userExpense) {
        this.userExpense = userExpense;
    }

    public List<UserExpense> findUserExpenseById(int id) {
        List<UserExpense> userExpenses = new ArrayList<>();
        for(UserExpense userExpense : userExpense){
            if(userExpense.getExpense().getId() == id){
                userExpenses.add(userExpense);
            }
        }
        return userExpenses;
    }
}
