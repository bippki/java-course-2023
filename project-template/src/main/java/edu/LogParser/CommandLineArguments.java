package edu.LogParser;

import org.apache.commons.cli.*;

public class CommandLineArguments {
    private String path;
    private String fromDate;
    private String toDate;
    private String outputFormat;

    public CommandLineArguments(String[] args) {
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
        CommandLine cmd = null;

        try {
            cmd = parser.parse(options, args);
            parseCommandLine(cmd);
        } catch (org.apache.commons.cli.ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("nginx-log-stats", options);
            System.exit(1);
        }
    }

    private void parseCommandLine(CommandLine cmd) {
        path = cmd.getOptionValue("path");
        fromDate = cmd.getOptionValue("from");
        toDate = cmd.getOptionValue("to");
        outputFormat = cmd.getOptionValue("format", "markdown");
    }

    public String getPath() {
        return path;
    }

    public String getFromDateCom() {
        return fromDate;
    }

    public String getToDateCom() {
        return toDate;
    }

    public String getOutputFormat() {
        return outputFormat;
    }
}
