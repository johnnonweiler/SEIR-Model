package uk.ramp.seir.io;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uk.ramp.seir.population.SeirRecord;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class Output {
    private static final Logger LOGGER = LogManager.getLogger(Output.class);

    private Output() {
        //hidden constructor
    }

    public static void printSeirCSV(Map<Integer, SeirRecord> records, String fileName) {

        try {
            FileWriter out = new FileWriter(fileName);
            String[] headers = {"Day", "S", "E", "I", "R"};
            try (CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT.withHeader(headers))) {
                records.forEach((day, record) -> {
                    try {
                        printer.printRecord(day, record.getS(), record.getE(), record.getI(), record.getR());
                    } catch (IOException e) {
                        LOGGER.error(e);
                    }
                });
            }

        } catch (IOException e) {
            LOGGER.error(e);
        }

    }

}
