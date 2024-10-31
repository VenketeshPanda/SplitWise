package dev.venketesh.splitwise.strategies;

import dev.venketesh.splitwise.models.Expense;
import dev.venketesh.splitwise.models.Transaction;

import java.util.List;

public interface SettleUpStrategy {
    List<Transaction> settleUp(List<Expense> expenses);
}
