package ua.petros.validator;

import org.springframework.stereotype.Component;
import ua.petros.model.Project;
import ua.petros.model.Transaction;

import java.math.BigDecimal;
import java.util.Set;

/**
 * Created by Taras on 15.12.2019.
 */

@Component
public class BalanceValidator {

    // Utility to fix imbalance between balance and transactions
    public BigDecimal recalculateBalance(Project project){
        Set<Transaction> transactions = project.getTransactions();
        BigDecimal transactionsBalance = getTransactionsBalance(transactions);

        return transactionsBalance;
    }

    private BigDecimal getTransactionsBalance(Set<Transaction>transactions){
        BigDecimal result = new BigDecimal(0);
        if (transactions != null || !transactions.isEmpty()){
            BigDecimal transactionBalance = new BigDecimal(0);
            for (Transaction transaction: transactions){
                if(transaction.getIsIncome()){
                    transactionBalance = transactionBalance.add(transaction.getAmountNOK());
                } else {
                    transactionBalance = transactionBalance.subtract(transaction.getAmountNOK());
                }
            }
            result = transactionBalance;
        }
        return result;
    }
}
