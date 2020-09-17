package com.deltadefense.carina.utils;

import au.com.bytecode.opencsv.CSVWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CSVReportGenerator {

    private static final Logger LOGGER = LoggerFactory.getLogger(CSVReportGenerator.class);

    public CSVReportGenerator csvReportGenerator() {
        return new CSVReportGenerator();
    }

    public void createCSVVideoReport(List<String[]> videoData, String csvFilename) throws IOException, SQLException {
        try {
            CSVWriter writer = getNewCSVWriter(csvFilename);
            String videoCSVHeader = "Video number, Video Title, Video URL, Video Length";
            createAndWriteCSVHeader(videoCSVHeader, writer);
            writeAllDataAfterHeader(videoData, writer);
            closeCSVWriter(writer);
            LOGGER.info("CSV Successfully Written! File Located: %s", csvFilename);
        } catch (Exception e) {
            LOGGER.error("exception :" + e.getMessage());
            throw e;
        }
    }

    private void writeAllDataAfterHeader(List<String[]> videoData, CSVWriter writer) throws IOException, SQLException {
        LOGGER.debug("Writing all data past header to CSV");
        writer.writeAll((ResultSet) videoData, true);
    }

    private void createAndWriteCSVHeader(String headerData, CSVWriter writer) {
        LOGGER.debug("Splitting %s data by comma for future writing to CSV", headerData);
        String[] csvHeaderData =  headerData.split(",");
        writeCSVLine(csvHeaderData, writer);
    }

    private void writeCSVLine(String[] csvDataToWrite, CSVWriter writer) {
        LOGGER.debug("Writing data: %s to CSV", csvDataToWrite.toString());
        writer.writeNext(csvDataToWrite);
    }

    private static CSVWriter getNewCSVWriter(String csvFilename) throws IOException {
        LOGGER.debug("Opening a writer for the CSV file: %s", csvFilename);
        return new CSVWriter(new FileWriter(csvFilename));
    }

    private void closeCSVWriter(CSVWriter writer) throws IOException {
        writer.close();
    }

}
