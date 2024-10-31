package dev.venketesh.splitwise.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CommandExecutor {
    public List<Command> commands;
    private SettleUpCommand settleUpCommand;

    public CommandExecutor(SettleUpCommand settleUpCommand){
        commands=new ArrayList<>();
        this.settleUpCommand=settleUpCommand;
        commands.add(settleUpCommand);
    }

    public void addCommand(Command command){
        commands.add(command);
    }

    public void execute(String cmd){
        for(Command command: commands){
            if(command.matches(cmd)){
                command.execute(cmd);
            }
        }
    }
}
