package org.lee_sh1673;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Month;
import java.util.List;

/**
 * This is entry-point class of BankTransactionAnalyzer.
 *
 * <p>
 * It connects parser, calculation, display results functions.
 * </p>
 *
 * @author LEE-sh1673
 * @since 22-06-04
 */
public class BankTransactionAnalyzer {
    private static final String RESOURCES = "src/main/resources/";
    private static final BankStatementCSVParser bankStatementParser =
            new BankStatementCSVParser();

    public static void main(String[] args) throws IOException {
        final String fileName = args[0];
        final Path path = Paths.get(RESOURCES + fileName);
        final List<String> lines = Files.readAllLines(path);

        final List<BankTransaction> bankTransactions =
                bankStatementParser.parseLinesFromCSV(lines);
        final BankStatementProcessor bankStatementProcessor =
                new BankStatementProcessor(bankTransactions);

        collectSummary(bankStatementProcessor);
    }

    private static void collectSummary(final BankStatementProcessor bankStatementProcessor) {
        System.out.println("The total for all transaction is "
                + bankStatementProcessor.calculateTotalAmount());

        System.out.println("The total for transaction in Month:");
        for (final Month month : Month.values()) {
            final double monthTransactions =
                    bankStatementProcessor.calculateTotalInMonth(month);

            if (Double.compare(monthTransactions, 0d) != 0) {
                System.out.println(month.name() + " : "
                        + bankStatementProcessor.calculateTotalInMonth(month));
            }
        }

        System.out.println("The total salary received is "
                + bankStatementProcessor.calculateTotalInCategory("Salary"));
    }
}
