package org.lee_sh1673;

import java.time.Month;
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
}
