package model;

public class Sum extends Operation {
    public Sum(double firstOperand, double secondOperand) {
        super(firstOperand, secondOperand);
    }

    @Override
    public double calculateResult() {
        return getFirstOperand() + getSecondOperand();
    }
}
