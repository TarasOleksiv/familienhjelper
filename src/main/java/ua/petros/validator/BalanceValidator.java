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
        BigDecimal transactionsBalance = getTransactionsBalance(transactions).getBalance();

        return transactionsBalance;
    }

    public BigDecimal recalculateDonation(Project project){
        Set<Transaction> transactions = project.getTransactions();
        BigDecimal transactionsDonation = getTransactionsBalance(transactions).getDonation();

        return transactionsDonation;
    }

    public BigDecimal recalculateExpense(Project project){
        Set<Transaction> transactions = project.getTransactions();
        BigDecimal transactionsExpense = getTransactionsBalance(transactions).getExpense();

        return transactionsExpense;
    }

    private Amount getTransactionsBalance(Set<Transaction>transactions){
        Amount result = new Amount(new BigDecimal(0), new BigDecimal(0), new BigDecimal(0));
        if (transactions != null || !transactions.isEmpty()){
            BigDecimal transactionBalance = new BigDecimal(0);
            BigDecimal transactionDonation = new BigDecimal(0);
            BigDecimal transactionExpense = new BigDecimal(0);
            for (Transaction transaction: transactions){
                if(transaction.getIsIncome()){
                    transactionBalance = transactionBalance.add(transaction.getAmountNOK());
                    transactionDonation = transactionDonation.add(transaction.getAmountNOK());
                } else {
                    transactionBalance = transactionBalance.subtract(transaction.getAmountNOK());
                    transactionExpense = transactionExpense.add(transaction.getAmountNOK());
                }
            }
            result.setBalance(transactionBalance);
            result.setDonation(transactionDonation);
            result.setExpense(transactionExpense);
        }
        return result;
    }

    public class Amount {
        private BigDecimal balance;
        private BigDecimal donation;
        private BigDecimal expense;

        public Amount(BigDecimal balance, BigDecimal donation, BigDecimal expense) {
            this.balance = balance;
            this.donation = donation;
            this.expense = expense;
        }

        public BigDecimal getBalance() {
            return balance;
        }

        public void setBalance(BigDecimal balance) {
            this.balance = balance;
        }

        public BigDecimal getDonation() {
            return donation;
        }

        public void setDonation(BigDecimal donation) {
            this.donation = donation;
        }

        public BigDecimal getExpense() {
            return expense;
        }

        public void setExpense(BigDecimal expense) {
            this.expense = expense;
        }
    }

}

