package org.lee_sh1673;

public class HtmlExporter implements Exporter {
    @Override public String export(SummaryStatistics summaryStatistics) {
        String result = "";

        // Set <head>
        result += "<!doctype html5>";
        result += "<head ><meta charset=\"utf-8\">";
        result += "<meta name=\"viewport\" content=\"width=device-width\">";
        result += "<meta charset='utf-8'><title>Bank Transaction Report</title></head>";

        // Set <body>
        result += "<body>";
        result += "<ul>";
        result += "<li><string>The sum is</strong>: " + summaryStatistics.getSum() + "</li>";
        result += "<li><string>The max is</strong>: " + summaryStatistics.getMax() + "</li>";
        result += "<li><string>The min is</strong>: " + summaryStatistics.getMin() + "</li>";
        result += "<li><string>The average is</strong>: " + summaryStatistics.getAverage() + "</li>";
        result += "</ul>";
        result += "</body>";

        // End of html files.
        result += "</html>";
        return result;
    }
}
