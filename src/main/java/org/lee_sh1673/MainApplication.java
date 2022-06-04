package org.lee_sh1673;

import java.io.IOException;

/**
 * This is entry-point class.
 */
public class MainApplication {
    public static void main(final String[] args) throws IOException {
        final BankStatementAnalyzer bankStatementAnalyzer
                = new BankStatementAnalyzer();

        final BankStatementParser bankStatementParser
                = new BankStatementCSVParser();

        bankStatementAnalyzer.analyze(args[0], bankStatementParser);
    }
}
