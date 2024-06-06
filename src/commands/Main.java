package commands;

import controller.UserController;
import repository.GroupRepository;
import repository.UserExpenseRepository;
import services.UserService;
import strategies.heapSettleUpStrategy;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CommandExecutor commandExecutor = new CommandExecutor(
                                            new SettleUpUserCommand(
                                                    new UserController(
                                                            new UserService(new GroupRepository(),
                                                                    new UserExpenseRepository(),
                                                                    new heapSettleUpStrategy()
                                                            )
                                                    )
                                            ) );
        while(true){
            String input = scanner.next();
//          input -> Niv goaTrip settleUpUser
            commandExecutor.executor(input);
        }
    }
}
