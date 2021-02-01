package model;

public class Coin {
    public static final String NICKEL = "Nickel";
    public static final String DIME = "Dime";
    public static final String QUARTER = "Quarter";

    private int value;

    public Coin(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        String name = "N/A";
        switch(value) {
            case 5: name = Coin.NICKEL; break;
            case 10: name = Coin.DIME; break;
            case 25: name = Coin.QUARTER; break;
        }
        return name;
    }
}
