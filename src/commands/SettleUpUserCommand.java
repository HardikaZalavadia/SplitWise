package commands;

import controller.UserController;
import dtos.Transaction;

import java.util.List;

public class SettleUpUserCommand implements Commands{
    private UserController userController;
    public static final String SETTLE_UP_USER = "settleUpUser";

    public SettleUpUserCommand(UserController userController) {
        this.userController = userController;
    }

    @Override
    public void execute(String input) {
        List<String> words = List.of(input.split(" "));
        List<Transaction> transactions = userController.settleUser(words.get(0),words.get(1));

//        print transaction
        for(Transaction transaction:transactions){
            System.out.println("Giver ->" + transaction.getFromUser() + " Receiver "+ transaction.getFromUser() + " : "+ transaction.getAmount());
        }

    }

    @Override
    public boolean match(String input) {
        List<String> word = List.of(input.split(" "));
        if(word.get(2).equals(SETTLE_UP_USER)){
            return true;
        }
        return false;
    }
}
