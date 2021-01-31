package model;

public class Calculator {
    private double lastN;
    private double lastN2;
    private double result;

    public void enter(double n) {
        // I think it's the other way in the videos, but entering 5, then 4,
        // then dividing and getting 0.8 didn't sit well with me
        lastN = lastN2;
        lastN2 = n;
    }
    public void add() {
        result = lastN + lastN2;
    }
    public void multiply() {
        result = lastN * lastN2;
    }
    public void subtract() {
        result = lastN - lastN2;
    }
    public void divide() {
        result = lastN / lastN2;
    }
    public double getResult() {
        return result;
    }
}
