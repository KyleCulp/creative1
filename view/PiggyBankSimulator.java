package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import controller.PBHistoryListener;
import controller.PBInsertListener;
import controller.PBStatsListener;
import model.Coin;
import model.PiggyBank;

public class PiggyBankSimulator {
    private JFrame window;
    private JTextArea display = new JTextArea();
    private JRadioButton[] radioButtons;
    private PiggyBank piggyBank = new PiggyBank();

    public PiggyBankSimulator(JFrame window) {
        this.window = window;
        window.setTitle("Piggy Bank Simulator");
    }

    public void init() {
        Container cp = window.getContentPane();
        var scrollPane = new JScrollPane(display, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(500, 500));

        cp.add(BorderLayout.CENTER, scrollPane);

        JPanel southPanel = new JPanel();
        southPanel.setLayout(new GridLayout(2, 1));
        cp.add(BorderLayout.SOUTH, southPanel);

        radioButtons = new JRadioButton[3];
        radioButtons[0] = new JRadioButton(Coin.NICKEL);
        radioButtons[0].setSelected(true);
        radioButtons[1] = new JRadioButton(Coin.DIME);
        radioButtons[2] = new JRadioButton(Coin.QUARTER);


        JPanel radioPanel = new JPanel();
        ButtonGroup radioGroup = new ButtonGroup();
        for(var b: radioButtons) {
            radioPanel.add(b);
            radioGroup.add(b);
        }
        southPanel.add(radioPanel);

        var insertButton = new JButton("Insert");
        insertButton.addActionListener(new PBInsertListener(this));
        var historyButton = new JButton("History");
        historyButton.addActionListener(new PBHistoryListener(this));
        var statsButton = new JButton("Stats");
        statsButton.addActionListener(new PBStatsListener(this));
        var exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> {
            window.getContentPane().removeAll();
            var menu = new MenuScreen(window);
            menu.init();
            window.pack();
            window.revalidate();
        });
    
        JPanel buttonPanel = new JPanel();
        southPanel.add(buttonPanel);

        buttonPanel.add(insertButton);
        buttonPanel.add(historyButton);
        buttonPanel.add(statsButton);
        buttonPanel.add(exitButton);

    }

    public PiggyBank getPiggyBank() { return piggyBank; }
    public JTextArea getDisplay() { return display; }
    public JRadioButton[] getRadioButtons() { return radioButtons; }


}
