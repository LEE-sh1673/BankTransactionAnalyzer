package org.lee_sh1673;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class BankStatementProcessor {
    private final List<BankTransaction> bankTransactions;

    public BankStatementProcessor(final List<BankTransaction> bankTransactions) {
        this.bankTransactions = bankTransactions;
    }

    public double summarize(final BankTransactionSummarizer transactionSummarizer) {
        double result = 0d;

        for (final BankTransaction bankTransaction : bankTransactions) {
            result = transactionSummarizer.summarize(result, bankTransaction);
        }
        return result;
    }

    public double calculateTotalAmount() {
        return summarize(((acc, bankTransaction) -> acc + bankTransaction.getAmount()));
    }

    public double calculateTotalInMonth(final Month month) {
        return summarize(((acc, bankTransaction) ->
                bankTransaction.getDate().getMonth() == month
                        ? acc + bankTransaction.getAmount()
                        : acc));
    }

    public double calculateTotalInCategory(final String category) {
        return summarize(((acc, bankTransaction) ->
                bankTransaction.getDescription().equals(category)
                        ? acc + bankTransaction.getAmount()
                        : acc));
    }

    public List<BankTransaction> findTransactions(final BankTransactionFilter transactionFilter) {
        final List<BankTransaction> transactions = new ArrayList<>();
        for (final BankTransaction bankTransaction : bankTransactions) {

            if (transactionFilter.test(bankTransaction)) {
                transactions.add(bankTransaction);
            }
        }
        return transactions;
    }

    public List<BankTransaction> findTransactionsGreaterThenEqual(final double amount) {
        return findTransactions(bankTransaction -> bankTransaction.getAmount() >= amount);
    }
}
