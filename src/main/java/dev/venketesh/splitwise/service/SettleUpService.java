package dev.venketesh.splitwise.service;

import dev.venketesh.splitwise.models.Expense;
import dev.venketesh.splitwise.models.Group;
import dev.venketesh.splitwise.models.Transaction;
import dev.venketesh.splitwise.repositories.GroupRepository;
import dev.venketesh.splitwise.strategies.HeapSettleUpStrategy;
import dev.venketesh.splitwise.strategies.SettleUpStrategy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SettleUpService {

    private GroupRepository groupRepository;
    private SettleUpStrategy settleUpStrategy;

    public SettleUpService(GroupRepository groupRepository){
        this.groupRepository=groupRepository;
        this.settleUpStrategy=new HeapSettleUpStrategy();
    }

    public List<Transaction> settleUpGroup(Long id){
        Optional<Group> groupOptional=groupRepository.findById(id);
        if(groupOptional.isEmpty()){
            throw new RuntimeException("Group is not available");
        }
        Group group = groupOptional.get();
        List<Expense> expenses = group.getExpenses();

        return settleUpStrategy.settleUp(expenses);
        // 1. Get the group
        // 2. Get all the expenses related to that
        // 3. Call the algo and pass the expenses to get the transaction
    }
}
