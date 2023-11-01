import model.Calculator;

import java.io.FileNotFoundException;

class Main {
    /*
    Ten FileNotFoundException wyrzuciłem tu w ten sposób umyślnie, jako że nie chciałem blokami try-catch
    "zaśmiecać" metody main, a dodatkowo uznałem, że brak tego pliku jest
    mało prawdopodobny. No ale chętnie to zmienię, jeśli będzie trzeba.
     */
    public static void main(String[] args) throws FileNotFoundException {
        // uzupełnij rozwiązanie

        Calculator calculator = new Calculator();
        calculator.printAndSaveResults();
    }
}