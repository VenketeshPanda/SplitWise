package dev.venketesh.splitwise.controllers;

import dev.venketesh.splitwise.dtos.ResponseStatus;
import dev.venketesh.splitwise.dtos.SettleUpGroupRequestDTO;
import dev.venketesh.splitwise.dtos.SettleUpGroupResponseDTO;
import dev.venketesh.splitwise.models.Transaction;
import dev.venketesh.splitwise.service.SettleUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class SettleUpController {

    @Autowired
    private SettleUpService settleUpService;

    public SettleUpGroupResponseDTO settleUp(SettleUpGroupRequestDTO requestDTO){
        SettleUpGroupResponseDTO responseDTO = new SettleUpGroupResponseDTO();
        try {
            List<Transaction> transactionList = settleUpService.settleUpGroup(requestDTO.getGroupId());
            responseDTO.setTransactions(transactionList);
            responseDTO.setResponseStatus(ResponseStatus.SUCCESS);
        } catch (Exception e){
            responseDTO.setMessage(e.getMessage());
            responseDTO.setResponseStatus(ResponseStatus.FAILURE);
        }
        return responseDTO;
    }
}
