package edu.LogParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

class MarkdownReport extends Report {
    public MarkdownReport(String logPath, List<LogEntry> entries,LocalDate a, LocalDate b) {
        super(logPath, entries,a,b);
    }

    @Override
    public String generateReport() {
        appendSection(toSave, "Общая информация");
        toSave.append(generateTable(getHeadersSectionMain(), getDataSectionMain()));

        appendSection(toSave, "Запрашиваемые ресурсы");
        toSave.append(generateTable(getHeadersSectionResources(), getDataSectionResources()));

        appendSection(toSave, "Коды ответа");
        toSave.append(generateTable(getHeadersSectionCodes(), getDataSectionCodes()));

        return toSave.toString();
    }

    @Override
    public String generateTable(String[] headers, String[][] data) {
        StringBuilder tableBuilder = new StringBuilder();

        for (String header : headers) {
            tableBuilder.append("| ").append(header).append(" ");
        }
        tableBuilder.append("|\n");

        tableBuilder.append("|:---: ".repeat(headers.length));
        tableBuilder.append("|\n");

        for (String[] row : data) {
            for (String cell : row) {
                tableBuilder.append("| ").append(cell).append(" ");
            }
            tableBuilder.append("|\n");
        }

        return tableBuilder.toString();
    }

    private void appendSection(StringBuilder report, String sectionTitle) {
        report.append("#### ").append(sectionTitle).append(System.lineSeparator());
    }

    @Override
    public void printReport() {
        printMainSection();
        printResourcesSection();
        printResponseCodesSection();
    }

    @Override
    public void saveReportToFile(String directoryPath, String fileName) throws IOException {
        Path directory = Path.of(directoryPath);
        Path filePath = directory.resolve(fileName + ".md");
        Files.createDirectories(directory);
        Files.writeString(filePath, generateReport(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        System.out.println("Отчет сохранен в файл: " + filePath);
    }

    @Override
    public void printMainSection() {
        System.out.println("Общая информация");
        ConsoleTablePrinter.printTable(getHeadersSectionMain(), getDataSectionMain());
    }

    @Override
    public void printResourcesSection() {
        System.out.println("Запрашиваемые ресурсы");
        ConsoleTablePrinter.printTable(getHeadersSectionResources(), getDataSectionResources());
    }

    @Override
    public void printResponseCodesSection() {
        System.out.println("Коды ответа");
        ConsoleTablePrinter.printTable(getHeadersSectionCodes(), getDataSectionCodes());
    }
}
