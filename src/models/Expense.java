package models;

public class Expense {
    private int Id;
    private String description;
    private int amount;
    private ExpenseType expenseType;


    public Expense(String description, int amount, ExpenseType expenseType, int Id) {
        this.description = description;
        this.amount = amount;
        this.expenseType = expenseType;
        this.Id = Id;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public ExpenseType getExpenseType() {
        return expenseType;
    }

    public void setExpenseType(ExpenseType expenseType) {
        this.expenseType = expenseType;
    }
}
