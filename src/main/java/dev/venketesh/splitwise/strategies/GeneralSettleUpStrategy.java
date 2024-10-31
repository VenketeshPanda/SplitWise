package dev.venketesh.splitwise.strategies;

import dev.venketesh.splitwise.models.Expense;
import dev.venketesh.splitwise.models.Transaction;

import java.util.List;

public class GeneralSettleUpStrategy implements SettleUpStrategy{
    @Override
    public List<Transaction> settleUp(List<Expense> expenses) {
        return List.of();
    }
}
