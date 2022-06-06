package org.lee_sh1673;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class BankStatementProcessor {
    private final List<BankTransaction> bankTransactions;

    public BankStatementProcessor(final List<BankTransaction> bankTransactions) {
        this.bankTransactions = bankTransactions;
    }

    public double calculateTotalAmount() {
        double totalAmount = 0d;

        for (final BankTransaction bankTransaction : bankTransactions) {
            totalAmount += bankTransaction.getAmount();
        }
        return totalAmount;
    }

    public double calculateTotalInMonth(final Month month) {
        double totalAmount = 0d;

        for (final BankTransaction bankTransaction : bankTransactions) {

            if (bankTransaction.getDate().getMonth() == month) {
                totalAmount += bankTransaction.getAmount();
            }
        }
        return totalAmount;
    }

    public double calculateTotalInCategory(final String category) {
        double totalAmount = 0d;

        for (final BankTransaction bankTransaction : bankTransactions) {

            if (bankTransaction.getDescription().equals(category)) {
                totalAmount += bankTransaction.getAmount();
            }
        }
        return totalAmount;
    }

    public List<BankTransaction> findTransactionGreaterThanEqual(final int amount) {
        final List<BankTransaction> transactions = new ArrayList<>();
        for (final BankTransaction transaction : bankTransactions) {

            if (transaction.getAmount() >= amount) {
                transactions.add(transaction);
            }
        }
        return transactions;
    }

    public List<BankTransaction> findTransactionsInMonth(final Month month) {
        final List<BankTransaction> transactions = new ArrayList<>();
        for (final BankTransaction transaction : bankTransactions) {

            if (transaction.getDate().getMonth() == month) {
                transactions.add(transaction);
            }
        }
        return transactions;
    }

    public List<BankTransaction> findTransactionsInMonthAndGreater(final Month month,
                                                                   final int amount) {

        final List<BankTransaction> transactions = new ArrayList<>();
        for (final BankTransaction transaction : bankTransactions) {

            if (transaction.getDate().getMonth() == month
                    && transaction.getAmount() > amount) {
                transactions.add(transaction);
            }
        }
        return transactions;
    }
}
