package org.lee_sh1673;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

public class BankStatementCSVParserTest {
    private final BankStatementParser statementParser
            = new BankStatementCSVParser();
    private static final double delta = 1e-15;


    @DisplayName("Salary 파싱 테스트")
    @Test
    public void shouldParseCorrectSalary() {
        final String line = "22-05-2015,1000,Salary";

        final BankTransaction result = statementParser.parseFrom(line);
        final BankTransaction expected = new BankTransaction(
                LocalDate.of(2015, Month.MAY, 22),
                1000,
                "Salary"
        );
        Assertions.assertEquals(expected.getDescription(), result.getDescription());
        Assertions.assertEquals(expected.getAmount(), result.getAmount(), delta);
    }

    @Test
    public void shouldParseOneCorrectLine() {
        // Set up test context.
        final String line = "30-01-2017,-50,Tesco";

        // Execute parsing test.
        final BankTransaction result = statementParser.parseFrom(line);
        final BankTransaction expected = new BankTransaction(
                LocalDate.of(2017, Month.JANUARY, 30),
                -50,
                "Tesco"
        );
        final double DELTA = 1e-15;

        // Set up assertions of expected results. -> Assert.assertEquals(expected, actual, [delta]);
        Assertions.assertEquals(expected.getDate(), result.getDate());
        Assertions.assertEquals(expected.getAmount(), result.getAmount(), DELTA);
        Assertions.assertEquals(expected.getDescription(), result.getDescription());
    }
}
