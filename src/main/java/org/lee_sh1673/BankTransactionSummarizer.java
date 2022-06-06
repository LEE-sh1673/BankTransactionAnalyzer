package org.lee_sh1673;

@FunctionalInterface
public interface BankTransactionSummarizer {
    double summarize(double acc, BankTransaction bankTransaction);
}
