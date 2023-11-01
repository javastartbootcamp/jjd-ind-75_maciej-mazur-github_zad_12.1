package model;

public abstract class Operation {
    private final double firstOperand;
    private final double secondOperand;

    public Operation(double firstOperand, double secondOperand) {
        this.firstOperand = firstOperand;
        this.secondOperand = secondOperand;
    }

    public abstract double calculateResult();

    public double getFirstOperand() {
        return firstOperand;
    }

    public double getSecondOperand() {
        return secondOperand;
    }
}
