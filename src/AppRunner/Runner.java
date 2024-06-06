package AppRunner;

import controller.UserController;
import dtos.Transaction;
import models.*;
import repository.ExpenseRepository;
import repository.GroupRepository;
import repository.UserExpenseRepository;
import repository.UserRepository;
import services.UserService;
import strategies.heapSettleUpStrategy;

import java.util.ArrayList;
import java.util.List;

public class Runner {
    public static void main(String[] args) {
//        creat user
        User niv = new User("Niv", 1234555, "8907");
        User nitya = new User("Nitya", 1234555, "8907");
        User niti = new User("Niti", 1234555, "8907");
        User nandini = new User("Nandini", 1234555, "8907");
        User niki = new User("Niki", 1234555, "8907");

        List<User> goaGuys = new ArrayList<>();
        goaGuys.add(niv);
        goaGuys.add(niti);
        goaGuys.add(nitya);
        goaGuys.add(nandini);
        goaGuys.add(niki);

//        creat the group and add this users
        Group goaTrip = new Group("GOA_TRIP");
        goaTrip.setUser(goaGuys);

//        add expense
        Expense dinner = new Expense("Dinner", 6000, ExpenseType.REGULAR, 1);

//        creat UserExpense share for everyone
        UserExpense nivShare  = new UserExpense(niv, 1000,dinner, UserExpenseType.HAD_TO_PAY);
        UserExpense nityaShare = new UserExpense(nitya, 2000, dinner,UserExpenseType.HAD_TO_PAY);
        UserExpense nitishare = new UserExpense(niti, 1000, dinner, UserExpenseType.HAD_TO_PAY);
        UserExpense nandiniShare = new UserExpense(nandini, 1000, dinner, UserExpenseType.HAD_TO_PAY);
        UserExpense nikiShare = new UserExpense(niki,1000, dinner,UserExpenseType.HAD_TO_PAY);

//        capture who paid what
        UserExpense paidByNiv = new UserExpense(niv,6000,dinner, UserExpenseType.PAID_BY);

//        add all the data in dataBase/repository
        GroupRepository groupRepository = new GroupRepository();
        UserRepository userRepository = new UserRepository();
        UserExpenseRepository userExpenseRepository = new UserExpenseRepository();
        ExpenseRepository expenseRepository = new ExpenseRepository();

        userRepository.setUsers(goaGuys);
        goaTrip.getExpense().add(dinner);
        groupRepository.getGroups().add(goaTrip);
        expenseRepository.getExpense().add(dinner);

        userExpenseRepository.getUserExpense().add(nivShare);
        userExpenseRepository.getUserExpense().add(nityaShare);
        userExpenseRepository.getUserExpense().add(nitishare);
        userExpenseRepository.getUserExpense().add(nandiniShare);
        userExpenseRepository.getUserExpense().add(nikiShare);

        userExpenseRepository.getUserExpense().add(paidByNiv);

        UserController userController = new UserController(new UserService(groupRepository,userExpenseRepository,new heapSettleUpStrategy()));

        List<Transaction> userTransaction = userController.settleUser("Niv","GOA_TRIP");

        System.out.println("SettleUp Transaction of the Group: ");
        for(Transaction transaction:userTransaction){
            System.out.println(transaction.getFromUser() + " -> " + transaction.getToUser() + " : " + transaction.getAmount());
        }

    }
}
