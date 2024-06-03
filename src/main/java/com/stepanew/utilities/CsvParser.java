package com.stepanew.utilities;

import com.stepanew.entities.Paths;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Класс для парсинга CSV-файлов и заполнения объекта Paths.
 */
public class CsvParser {

    private final static String CSV_PATH = "src/main/resources/paths.csv";

    private final Paths paths;

    {
        paths = new Paths();
    }

    /**
     * Читает CSV-файл и заполняет объект Paths данными из файла.
     *
     * @return объект Paths, содержащий данные из CSV-файла
     */
    public Paths readDataFile() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(CSV_PATH))) {
            String oneLine;

            while ((oneLine = bufferedReader.readLine()) != null) {
                String[] fields = oneLine.split(",");

                if (fields.length != 2) {
                    throw new IOException("Incorrect csv`s data");
                }

                paths.addPath(fields[0].trim(), fields[1].trim());
            }

            if (paths.getPaths().keySet().size() != 9) {
                throw new IOException("Csv size is must be 9");
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't open data file");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        return paths;
    }
}
