package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Coin;
import view.PiggyBankSimulator;

public class PBInsertListener implements ActionListener {
    private PiggyBankSimulator panel;
    
    public PBInsertListener(PiggyBankSimulator panel) {
        this.panel = panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String buttonLabel = "";
        for(var b: panel.getRadioButtons()) {
            if(b.isSelected()) {
                buttonLabel = b.getText();
                break;
            }
        }

        int value = 0;
        switch(buttonLabel) {
            case Coin.NICKEL: value = 5; break;
            case Coin.DIME: value = 10; break;
            case Coin.QUARTER: value = 25; break;
        }

        var c = new Coin(value);
        panel.getPiggyBank().enter(c);
        String m = panel.getDisplay().getText();
        panel.getDisplay().setText(m + "\nInserted: " + c + " | " + panel.getPiggyBank());
    }
    
}
