package edu.LogParser;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public abstract class Report {
    @Getter
    @Setter
    protected LocalDate fromDate = null;
    @Getter
    @Setter
    protected LocalDate toDate = null;
    @Getter
    protected final List<LogEntry> entries;
    @Getter
    protected final String logPath;
    @Getter
    protected final StringBuilder toSave;
    @Getter
    protected final String[] headersSectionMain;
    @Getter
    protected final String[] headersSectionResources;
    @Getter
    protected final String[] headersSectionCodes;
    @Getter
    protected final String[][] dataSectionMain;
    @Getter
    protected final String[][] dataSectionResources;
    @Getter
    protected final String[][] dataSectionCodes;

    public Report(String logPath, List<LogEntry> entries) {
        this.entries = entries;
        this.logPath = logPath;
        this.toSave = new StringBuilder();

        this.headersSectionMain = new String[]{"Метрика", "Значение"};
        this.dataSectionMain = new String[][]{
                {"Файл (-ы)", getLogPath()},
                {"Начальная дата", (getFromDate() != null ? getFromDate().format(DateTimeFormatter.ISO_DATE) : "-")},
                {"Конечная дата", (getToDate() != null ? getToDate().format(DateTimeFormatter.ISO_DATE) : "-")},
                {"Количество запросов", String.valueOf(getEntries().size())},
                {"Средний размер ответа", LogAnalyzer.getAverageResponseSize(getEntries(), getFromDate(), getToDate()) + "b"}
        };

        Map<String, Long> resourceMap = LogAnalyzer.getResourceRequests(getEntries(), getFromDate(), getToDate());

        this.headersSectionResources = new String[] {"Ресурс", "Количество"};
        String[][] tempDataSectionResources = new String[resourceMap.size()][2];
        int i = 0;

        for (Map.Entry<String, Long> entry : resourceMap.entrySet()) {
            tempDataSectionResources[i][0] = entry.getKey();
            tempDataSectionResources[i][1] = String.valueOf(entry.getValue());
            i++;
        }
        Arrays.sort(tempDataSectionResources, Comparator.comparingInt(o -> -Integer.parseInt(o[1])));
        this.dataSectionResources = tempDataSectionResources;

        Map<Integer, Long> codesMap = LogAnalyzer.getResponseCodes(getEntries(), getFromDate(), getToDate());
        this.headersSectionCodes = new String[] {"Код", "Имя", "Количество"};


        String[][] tempDataSectionCodes = new String[codesMap.size()][3];
        i = 0;

        for (Map.Entry<Integer, Long> entry : codesMap.entrySet()) {
            tempDataSectionCodes[i][0] = String.valueOf(entry.getKey());
            tempDataSectionCodes[i][1] = ResponseCode.getResponseCodeName(entry.getKey());
            tempDataSectionCodes[i][2] = String.valueOf(entry.getValue());
            i++;
        }
        Arrays.sort(tempDataSectionCodes, Comparator.comparingInt(o -> -Integer.parseInt(o[2])));
        this.dataSectionCodes = tempDataSectionCodes;
    }
    abstract String generateReport();

    abstract String generateTable(String[] headers, String[][] data);

    abstract void printReport();

    abstract void printMainSection();
    abstract void printResourcesSection();
    abstract void printResponseCodesSection();

    abstract void saveReportToFile(String path,String name) throws IOException;
}
