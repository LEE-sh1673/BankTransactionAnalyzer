package org.lee_sh1673;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Month;
import java.util.List;

/**
 * It connects parser, calculation, display results modules
 * and show Bank transaction statements summary.
 *
 * @author LEE-sh1673
 * @since 22-06-04
 */
public class BankStatementAnalyzer {
    private static final String RESOURCES = "src/main/resources/";

    public void analyze(final String fileName,
                        final BankStatementParser bankStatementParser) throws IOException {

        final Path path = Paths.get(RESOURCES + fileName);
        final List<String> lines = Files.readAllLines(path);

        final List<BankTransaction> bankTransactions =
                bankStatementParser.parseLinesFrom(lines);
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
