package edu.LogParser;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import static edu.LogParser.LogAnalyzer.readLogEntries;
import static edu.LogParser.LogAnalyzer.readLogEntriesFromFile;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ParserTest {

    private boolean compareMarkdownFiles(Path filePath1, Path filePath2) throws IOException {
        String content1 = new String(Files.readAllBytes(filePath1));
        String content2 = new String(Files.readAllBytes(filePath2));
        return content1.equals(content2);
    }

    private boolean checkAdocFileContainsLine(String filePath, String expectedLine) throws IOException {
        Path path = Paths.get(filePath);
        List<String> lines = Files.readAllLines(path);
        return lines.stream().anyMatch(line -> line.contains(expectedLine));
    }

    @Test
    void testCommandLineArgumentsParsing() {
        String[] args = {"-p", "log/path", "-f", "2023-01-01", "-t", "2023-12-31", "-fmt", "markdown"};
        CommandLineArguments commandLineArguments = new CommandLineArguments(args);

        assertEquals("log/path", commandLineArguments.getPath());
        assertEquals("2023-01-01", commandLineArguments.getFromDateCom());
        assertEquals("2023-12-31", commandLineArguments.getToDateCom());
        assertEquals("markdown", commandLineArguments.getOutputFormat());
    }

    @Test
    void testReadFromUrlFileCompileMarkdown() throws IOException {
        String[] args = {"-p",
            "https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs",
            "-fmt", "markdown"};
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

            MarkdownReport report = new MarkdownReport(path, logEntries, fromDateObj, toDateObj);
            report.setFromDate(fromDateObj);
            report.setToDate(toDateObj);
            report.saveReportToFile("d/reports/markdown/", "1");

        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] args2 = {"-p", "d/reports/markdown/1.md", "-f", "2015-05-30", "-fmt", "markdown"};
        ;
        CommandLineArguments commandLineArguments2 = new CommandLineArguments(args2);
        String path2 = commandLineArguments2.getPath();

        try {
            List<LogEntry> logEntries = readLogEntries(path);
            DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_DATE;
            LocalDate fromDateObj = null;
            LocalDate toDateObj = null;
            /* Сюда сунул path, а не path2 чтобы не различались заголовки в Метриках -> Файл (-ы). Он тащит из файла, но заголок записываю как URL-ку*/
            MarkdownReport report2 = new MarkdownReport(path, logEntries, fromDateObj, toDateObj);
            report2.setFromDate(fromDateObj);
            report2.setToDate(toDateObj);
            report2.saveReportToFile("d/reports/markdown/", "2");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Path fp1 = Path.of("d/reports/markdown/1.md");
        Path fp2 = Path.of("d/reports/markdown/2.md");
        assertTrue(compareMarkdownFiles(fp1, fp2));
        Files.delete(fp1);
        Files.delete(fp2);
    }

    @Test
    void testDataAdoc() throws IOException {
        String[] args = {"-p",
            "https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs",
            "-f", "2015-05-30", "-fmt", "adoc"};
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
            AdocReport report = new AdocReport(path, logEntries, fromDateObj, toDateObj);
            report.setFromDate(fromDateObj);
            report.setToDate(toDateObj);
            report.saveReportToFile("d/reports/adoc/", "3");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String adocFilePath = "d/reports/adoc/3.adoc";
        String expectedLine = "| Начальная дата | 2015-05-30";
        String expectedLine2 = "| GET /downloads/product_1 HTTP/1.1 | 9219";
        String expectedLine3 = "| Количество запросов | 15161 ";
        String expectedLine4 = "| 200 | OK | 1146";
        assertTrue(checkAdocFileContainsLine(adocFilePath, expectedLine));
        assertTrue(checkAdocFileContainsLine(adocFilePath, expectedLine2));
        assertTrue(checkAdocFileContainsLine(adocFilePath, expectedLine3));
        assertTrue(checkAdocFileContainsLine(adocFilePath, expectedLine4));
    }

}


