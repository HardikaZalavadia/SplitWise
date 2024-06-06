package commands;

import java.util.ArrayList;
import java.util.List;

public class CommandExecutor {
    List<Commands> commandsList = new ArrayList<>();

    public CommandExecutor(SettleUpUserCommand settleUpUserCommand) {
        this.commandsList.add(settleUpUserCommand);
    }

    public void addCommands(Commands commands){
        commandsList.add(commands);
    }

    public void removeCommands(Commands commands){
        commandsList.remove(commands);
    }

    public void executor(String input){
        for(Commands commands : commandsList){
            if(commands.match(input)){
                commands.execute(input);
                break;
            }
        }

    }
}
