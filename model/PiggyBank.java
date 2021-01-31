package model;

public class PiggyBank {
    private int balance;
    private int coinCount;

    public PiggyBank() {
        balance = 0;
        coinCount = 0;
    }

    public void enter(Coin c) {
        ++coinCount;
        balance += c.getValue();
    }

    public int getBalance() {
        return balance;
    }

    public int getCoinCount() {
        return coinCount;
    }
}
