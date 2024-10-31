package dev.venketesh.splitwise.strategies;

import dev.venketesh.splitwise.models.Expense;
import dev.venketesh.splitwise.models.Transaction;
import dev.venketesh.splitwise.models.UserExpense;
import dev.venketesh.splitwise.models.UserExpenseType;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class HeapSettleUpStrategy implements SettleUpStrategy {
    @Override
    public List<Transaction> settleUp(List<Expense> expenses) {
        PriorityQueue<Integer> getter = new PriorityQueue<>();
        PriorityQueue<Integer> setter = new PriorityQueue<>();
        List<Transaction> transactions = new ArrayList<>();

        for(Expense expense: expenses){
            for(UserExpense userExpense: expense.getUserExpenses()){
                if(userExpense.getUserExpenseType().equals(UserExpenseType.PAID_BY)){
                    getter.add(userExpense.getAmount());
                }else{
                    setter.add(userExpense.getAmount());
                }
            }
        }

        while(!getter.isEmpty() && !setter.isEmpty()){
            Transaction transaction = new Transaction();
            int highestGetter = getter.poll();
            int highestSettler = setter.poll();

            if(highestGetter<highestSettler){
                getter.add(highestSettler-highestGetter);
//                transaction.setFrom();

            } else if(highestSettler<highestGetter){
                setter.add(highestGetter-highestSettler);
            }
        }
        return List.of();
    }
}
