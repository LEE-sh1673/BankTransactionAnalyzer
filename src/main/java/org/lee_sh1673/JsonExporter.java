package org.lee_sh1673;

/**
 * Export statistics transaction data to JSON format.
 * <p>
 * JSON = {
 * "key": "value";
 * ...
 * };
 *
 * @author LEE-sh1673
 * @since 2022-06-06
 */
public class JsonExporter implements Exporter {
    @Override public String export(SummaryStatistics summaryStatistics) {
        String result = "";

        result += "{";
        result += "\"Sum\" : " + summaryStatistics.getSum() + ",";
        result += "\"Max\" : " + summaryStatistics.getMax() + ",";
        result += "\"Min\" : " + summaryStatistics.getMin() + ",";
        result += "\"Average\" : " + summaryStatistics.getAverage() + ";";
        result += "}";

        return result;
    }
}
