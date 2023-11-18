package edu.LogParser;

import org.apache.commons.cli.*;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import static edu.LogParser.LogAnalyzer.readLogEntries;

public class NginxLogAnalyzer {
    public static void main(String[] args) {
        Options options = new Options();

        Option pathOption = new Option("p", "path", true, "Path to NGINX log files (local path or URL)");
        pathOption.setRequired(true);
        options.addOption(pathOption);

        Option fromOption = new Option("f", "from", true, "Start date for log analysis in ISO8601 format");
        options.addOption(fromOption);

        Option toOption = new Option("t", "to", true, "End date for log analysis in ISO8601 format");
        options.addOption(toOption);

        Option formatOption = new Option("fmt", "format", true, "Output format (markdown or adoc)");
        options.addOption(formatOption);

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd;

        try {
            cmd = parser.parse(options, args);
        } catch (org.apache.commons.cli.ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("nginx-log-stats", options);
            System.exit(1);
            return;
        }

        String path = cmd.getOptionValue("path");
        String fromDate = cmd.getOptionValue("from");
        String toDate = cmd.getOptionValue("to");
        String outputFormat = cmd.getOptionValue("format", "markdown");


        try {
            List<LogEntry> logEntries = readLogEntries(path);
            DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_DATE;
            LocalDate fromDateObj = fromDate != null ? LocalDate.parse(fromDate, dateFormatter) : null;
            LocalDate toDateObj = toDate != null ? LocalDate.parse(toDate, dateFormatter) : null;

            if ("markdown".equalsIgnoreCase(outputFormat)) {
                MarkdownReport report = new MarkdownReport(path, logEntries);
                report.setFromDate(fromDateObj);
                report.setToDate(toDateObj);
                report.printReport();
                String userDir = System.getProperty("user.dir");
                String outputPath = userDir + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "reports" + File.separator + "markdown";

                report.saveReportToFile("outputPath", "testParse");
            } else {
                AdocReport report = new AdocReport(path, logEntries);
                report.setFromDate(fromDateObj);
                report.setToDate(toDateObj);
                report.printReport();
                report.saveReportToFile("src/main/resources/reports/adoc", "testParse");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
