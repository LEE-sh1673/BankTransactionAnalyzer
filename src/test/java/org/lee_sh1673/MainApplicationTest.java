package org.lee_sh1673;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class MainApplicationTest {
    private static final double DELTA = 1e-15;
    private static final String RESOURCES = "src/main/resources/";
    private static final String FILE_NAME = "bank-data-simple.csv";

    private final BankStatementParser statementParser
            = new BankStatementCSVParser();


    @DisplayName("BankStatementAnalyzer 무결성 테스트")
    @Test
    public void shouldCorrectSummary() throws IOException {
        final Path path = Paths.get(RESOURCES + FILE_NAME);
        final List<String> lines = Files.readAllLines(path);

        final List<BankTransaction> bankTransactions =
                statementParser.parseLinesFrom(lines);

        final BankStatementProcessor bankStatementProcessor =
                new BankStatementProcessor(bankTransactions);

        // Set up test results.
        final double totalAmount =
                bankStatementProcessor.calculateTotalAmount();

        final List<Double> totalAmountInMonths = new ArrayList<>();
        for (final Month month : Month.values()) {
            double totalAmountMonth =
                    bankStatementProcessor.calculateTotalInMonth(month);

            if (Math.abs(totalAmountMonth) > DELTA) {
                totalAmountInMonths.add(totalAmountMonth);
            }
        }

        final double totalAmountInCategory =
                bankStatementProcessor.calculateTotalInCategory("Salary");

        // Set up expected values.
        final List<Double> expectedTotalAmountInMonths = Arrays.asList(-150.0d, 6970.d);

        Assertions.assertEquals(totalAmount, 6820.0d, DELTA);
        Assertions.assertIterableEquals(totalAmountInMonths, expectedTotalAmountInMonths);
        Assertions.assertEquals(totalAmountInCategory, 6000.0d, DELTA);
    }
}
