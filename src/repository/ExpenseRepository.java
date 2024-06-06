package repository;

import models.Expense;

import java.util.ArrayList;
import java.util.List;

public class ExpenseRepository {
    List<Expense> expense = new ArrayList<>();

    public List<Expense> getExpense() {
        return expense;
    }

    public void setExpense(List<Expense> expense) {
        this.expense = expense;
    }
}
