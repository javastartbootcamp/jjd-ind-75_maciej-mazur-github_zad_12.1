package io.file;

import java.io.*;
import java.util.Scanner;

public class FileManager {
    private static final String INPUT_FILE_NAME = "operations.txt";
    private static final String OUTPUT_FILE_NAME = "results.txt";

    public String[] importEquations() throws FileNotFoundException {
        try {
            int inputFileLineNumber = countInputFileLines();
            String[] equations = new String[inputFileLineNumber];

        /*
        Poniżej bez bloku try-catch, ponieważ potencjalny FileNotFoundException zostanie wyrzucony do konstruktora
        Calculator za sprawą "throws FileNotFoundException"
         */
            Scanner scanner = new Scanner(new File(INPUT_FILE_NAME));
            for (int i = 0; i < inputFileLineNumber; i++) {
                equations[i] = scanner.nextLine();
            }

            return equations;
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Nie znaleziono pliku " + INPUT_FILE_NAME);
        }
    }

    public void exportEquations(String data) throws IOException {
        try (
                var fileWriter = new FileWriter(OUTPUT_FILE_NAME);
                var writer = new BufferedWriter(fileWriter)
                ) {
            writer.write(data);
        }
    }

    private int countInputFileLines() throws FileNotFoundException {
        int lineNumber = 0;

        try (Scanner scanner = new Scanner(new File(INPUT_FILE_NAME))) {
            while (scanner.hasNextLine()) {
                lineNumber++;
                scanner.nextLine();
            }
        }

        return lineNumber;
    }
}
