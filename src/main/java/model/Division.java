package model;

public class Division extends Operation {
    public Division(double firstOperand, double secondOperand) {
        super(firstOperand, secondOperand);
    }

    @Override
    public double calculateResult() {
        if (getSecondOperand() == 0) {
            throw new ArithmeticException();
        }
        return getFirstOperand() / getSecondOperand();
    }
}
