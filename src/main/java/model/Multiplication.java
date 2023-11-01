package model;

public class Multiplication extends Operation {
    public Multiplication(double firstOperand, double secondOperand) {
        super(firstOperand, secondOperand);
    }

    @Override
    public double calculateResult() {
        return getFirstOperand() * getSecondOperand();
    }
}
