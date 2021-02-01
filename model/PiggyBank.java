package model;

import java.util.ArrayList;

public class PiggyBank {
    private int balance;
    private ArrayList<Coin> coinHistory = new ArrayList<>();

    public PiggyBank() {
        balance = 0;
    }

    public void enter(Coin c) {
        balance += c.getValue();
        coinHistory.add(c);
    }

    public int getBalance() {
        return balance;
    }

    public int getCoinCount() {
        return coinHistory.size();
    }

    public ArrayList<Coin> getCoinHistory() {
        return coinHistory;
    }

    @Override
    public String toString() {
        return "Balance = " +  balance + ", Coin count = " + getCoinCount();
    }
}
