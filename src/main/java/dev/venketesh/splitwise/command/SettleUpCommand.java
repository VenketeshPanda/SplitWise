package dev.venketesh.splitwise.command;

import dev.venketesh.splitwise.controllers.SettleUpController;
import dev.venketesh.splitwise.dtos.ResponseStatus;
import dev.venketesh.splitwise.dtos.SettleUpGroupRequestDTO;
import dev.venketesh.splitwise.dtos.SettleUpGroupResponseDTO;
import dev.venketesh.splitwise.models.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SettleUpCommand implements Command{

    @Autowired
    private SettleUpController settleUpController;

    @Override
    public boolean matches(String cmd) {
        List<String> words = List.of(cmd.split(" "));
        if(words.size()==2  && words.get(0).equals("SettleUp")){
            return true;
        }
        return false;
    }

    @Override
    public void execute(String cmd) {
        List<String> words = List.of(cmd.split(" "));
        Long groupId=Long.parseLong(words.get(1));

        SettleUpGroupRequestDTO requestDTO = new SettleUpGroupRequestDTO();
        requestDTO.setGroupId(groupId);
        SettleUpGroupResponseDTO responseDTO = settleUpController.settleUp(requestDTO);
        if(responseDTO.getResponseStatus().equals(ResponseStatus.SUCCESS)){
            System.out.println(responseDTO.getMessage());
            for(Transaction transaction: responseDTO.getTransactions()){
                System.out.println(transaction.getFrom().getName()+" paid "+transaction.getTo().getName()+" : "+transaction.getAmount());
            }
        }else{
            System.out.println(responseDTO.getMessage());
        }
    }
}
