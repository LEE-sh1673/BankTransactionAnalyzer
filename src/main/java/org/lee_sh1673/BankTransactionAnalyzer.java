package org.lee_sh1673;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class BankTransactionAnalyzer {
    private static final String RESOURCES = "src/main/resources/";

    public static double calculateTotalAmount(final List<BankTransaction> bankTransactions) {
        double total = 0d;

        for (final BankTransaction bankTransaction : bankTransactions) {
            total += bankTransaction.getAmount();
        }
        return total;
    }

    public static List<BankTransaction> selectInMonth(final List<BankTransaction> bankTransactions,
                                                      final Month month) {

        final List<BankTransaction> bankTransactionsInMonth = new ArrayList<>();

        for (final BankTransaction bankTransaction : bankTransactions) {
            if (bankTransaction.getDate().getMonth() == month) {
                bankTransactionsInMonth.add(bankTransaction);
            }
        }
        return bankTransactionsInMonth;
    }

    // TODO: 현재 코드는 특정 데이터 형식 만을 지원함. 새로운 열을 추가하거나 다른 데이터 형식을 지원하도록 구현할 것.
    public static void getMonthTransactions(final String fileName,
                                            final Month month) throws IOException {

        final BankStatementCSVParser bankStatementParser = new BankStatementCSVParser();

        final Path path = Paths.get(RESOURCES + fileName);
        final List<String> lines = Files.readAllLines(path);

        final List<BankTransaction> bankTransactions
                = bankStatementParser.parseLinesFromCSV(lines);

        System.out.println("The total for all transactions is " + calculateTotalAmount(bankTransactions));
        System.out.println("Transactions in " + month.name() + " " + selectInMonth(bankTransactions, month));
    }


    public static void main(String[] args) throws IOException {
        getMonthTransactions(args[0], Month.JANUARY);
    }
}
