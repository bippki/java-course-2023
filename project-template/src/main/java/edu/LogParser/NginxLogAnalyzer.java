package edu.LogParser;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import static edu.LogParser.LogAnalyzer.readLogEntries;

public class NginxLogAnalyzer {
    public static void main(String[] args) {
        CommandLineArguments commandLineArguments = new CommandLineArguments(args);

        String path = commandLineArguments.getPath();
        String fromDate = commandLineArguments.getFromDateCom();
        String toDate = commandLineArguments.getToDateCom();
        String outputFormat = commandLineArguments.getOutputFormat();


        try {
            List<LogEntry> logEntries = readLogEntries(path);
            DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_DATE;
            LocalDate fromDateObj = fromDate != null ? LocalDate.parse(fromDate, dateFormatter) : null;
            LocalDate toDateObj = toDate != null ? LocalDate.parse(toDate, dateFormatter) : null;

            if ("markdown".equalsIgnoreCase(outputFormat)) {
                MarkdownReport report = new MarkdownReport(path, logEntries,fromDateObj,toDateObj);
                report.setFromDate(fromDateObj);
                report.getFromDate();
                report.setToDate(toDateObj);
                report.getToDate();
                report.printReport();
                report.saveReportToFile("src/main/resources/reports/markdown/", "testParse");
            } else {
                AdocReport report = new AdocReport(path, logEntries,fromDateObj,toDateObj);
                report.setFromDate(fromDateObj);
                report.setToDate(toDateObj);
                report.printReport();
                report.saveReportToFile("src/main/resources/reports/adoc/", "testParse");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
