package org.lee_sh1673;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;

public class BankStatementCSVParserTest {

    private final BankStatementParser statementParser
            = new BankStatementCSVParser();

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
        Assert.assertEquals(expected.getDate(), result.getDate());
        Assert.assertEquals(expected.getAmount(), result.getAmount(), DELTA);
        Assert.assertEquals(expected.getDescription(), result.getDescription());
    }
}
