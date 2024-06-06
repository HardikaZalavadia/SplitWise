package models;

import java.util.ArrayList;
import java.util.List;

public class Group {
    private List<User> user;
    private List<Expense> expense;
    private String name;

    public Group(String name) {
        this.user = new ArrayList<>();
        this.expense = new ArrayList<>();
        this.name = name;
    }

    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }

    public List<Expense> getExpense() {
        return expense;
    }

    public void setExpense(List<Expense> expense) {
        this.expense = expense;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
