package model;

public class Subtraction extends Operation {
    public Subtraction(double firstOperand, double secondOperand) {
        super(firstOperand, secondOperand);
    }

    @Override
    public double calculateResult() {
        return getFirstOperand() - getSecondOperand();
    }
}
