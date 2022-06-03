package org.lee_sh1673;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class BankTransactionAnalyzerSimple {
    private static final String RESOURCES = "src/main/resources/";

    // TODO: 현재 코드는 특정 데이터 형식 만을 지원함. 새로운 열을 추가하거나 다른 데이터 형식을 지원하도록 구현할 것.
    public static void getTotalTransactions(final String file) throws IOException {

        // TODO: `getMonthTransactions`와 중복된 부분 발생. 추후 리펙토링 필요.
        final Path path = Paths.get(RESOURCES + file);
        final List<String> lines = Files.readAllLines(path);
        double total = 0d;

        for (final String line : lines) {
            final String[] columns = line.split(",");
            final double amount = Double.parseDouble(columns[1]);
            total += amount;
        }
        System.out.println("The total for all transactions is " + total);
    }

    // TODO: 현재 코드는 특정 데이터 형식 만을 지원함. 새로운 열을 추가하거나 다른 데이터 형식을 지원하도록 구현할 것.
    public static void getMonthTransactions(final String file,
                                            final Month month) throws IOException {

        // TODO: `getTotalTransactions`와 중복된 부분 발생. 추후 리펙토링 필요.
        final Path path = Paths.get(RESOURCES + file);
        final List<String> lines = Files.readAllLines(path);
        double total = 0d;
        final DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        for (final String line : lines) {
            final String[] columns = line.split(",");
            final LocalDate date = LocalDate.parse(columns[0], DATE_PATTERN);

            if (date.getMonth() == month) {
                final double amount = Double.parseDouble(columns[1]);
                total += amount;
            }
        }
        System.out.println("The total for all transactions in " + month.name() + " is " + total);
    }

    public static void main(String[] args) throws IOException {

    }
}
