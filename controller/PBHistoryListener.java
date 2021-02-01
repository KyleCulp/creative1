package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import model.Coin;
import view.PiggyBankSimulator;

public class PBHistoryListener implements ActionListener {

    private PiggyBankSimulator panel;

    public PBHistoryListener(PiggyBankSimulator panel) {
        this.panel = panel;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        ArrayList<Coin> history = panel.getPiggyBank().getCoinHistory();
        int index = 0;
        String existingMessage =  panel.getDisplay().getText();
        String m = "";
        if(history.size() > 0) {
            for (var coin: history) {
                m += ++index + ". " + coin + "\n";
            }
            panel.getDisplay().setText("\n" + existingMessage + "Coin Insert History\n" + m);
        } else {
            panel.getDisplay().setText(existingMessage + "\n" + "No coin insert history");
        }
    }
}
