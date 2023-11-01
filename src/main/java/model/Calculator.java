package model;

import exception.IllegalOperatorException;
import io.ConsolePrinter;
import io.file.FileManager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Locale;

public class Calculator {
    private final String[] equations;
    private final FileManager fileManager;
    private final ConsolePrinter printer = new ConsolePrinter();

    public Calculator() throws FileNotFoundException {
        fileManager = new FileManager();
        equations = fileManager.importEquations();
        printer.printLine("Zaimportowano pomyślnie wszystkie równania z pliku");
    }

    public void printAndSaveResults() {
        try {
            Operation[] calculableOperations = buildCalculableOperations();
            String equationsWithResults = buildEquationsWithResults(calculableOperations);
            fileManager.exportEquations(equationsWithResults);
            printer.printLine("Zadane równania wraz z przekalkulowanymi wynikami:\n\n" +
                    equationsWithResults +
                    "\n\nUdało się pomyślnie wyeksportować wszystkie równania wraz z wynikami do pliku");
        } catch (ArithmeticException e) {
            printer.printLine("Niedozwolona operacja dzielenia przez zero.");
        } catch (IOException e) {
            printer.printLine("Błąd zapisu do pliku.");
        } catch (IllegalOperatorException e) {
            printer.printLine(e.getMessage());
        } catch (NumberFormatException e) {
            printer.printLine("Nieprawidłowy format równań w pliku. Operandy i operatory muszą być od siebie " +
                    "oddzielone pojedynczymi spacjami i nie mogą zawierać żadnych liter.");
        }
    }

    private String buildEquationsWithResults(Operation[] operations) throws ArithmeticException {
        StringBuilder sb = new StringBuilder();
        double equationResult;

        for (int i = 0; i < operations.length; i++) {
            try {
                equationResult = operations[i].calculateResult();
                sb.append(equations[i] + " = " + formatResult(equationResult) + "\n");
            } catch (ArithmeticException e) {
                sb.append(equations[i] + " = NIEDOZWOLONA OPERACJA! (Dzielenie przez zero)");
            }
        }

        return sb.toString();
    }

    private String formatResult(double result) {
        int decimalPart = (int) result;

        if (((double) decimalPart) == result) {     // jeśli w wyniku pierwotnie była liczba całkowita
            return String.format(Locale.US, "%.1f", result);
        } else {                                // obcięcie części ułamkowej wprowadzi nierówność między liczbami
            return String.valueOf(result);     // umyślny brak ograniczenia miejsc po przecinku dla zachowania większej precyzji
        }
    }

    private Operation[] buildCalculableOperations() {
        Operation[] calculableEquations = new Operation[equations.length];

        for (int i = 0; i < calculableEquations.length; i++) {
            calculableEquations[i] = extractEquationFromString(equations[i]);
        }

        return calculableEquations;
    }

    private Operation extractEquationFromString(String equationString) {
        String[] split = equationString.split(" ");
        double firstOperand = Double.parseDouble(split[0]);
        double secondOperand = Double.parseDouble(split[2]);
        String operator = split[1];

        switch (operator) {
            case "+" -> {
                return new Sum(firstOperand, secondOperand);
            }
            case "-" -> {
                return new Subtraction(firstOperand, secondOperand);
            }
            case "*" -> {
                return new Multiplication(firstOperand, secondOperand);
            }
            case "/" -> {
                return new Division(firstOperand, secondOperand);
            }
            default -> throw new IllegalOperatorException("W pliku podano błędny operator \"" + operator + "\"");
        }
    }
}
