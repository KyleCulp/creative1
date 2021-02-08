package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.LibraryTab;

public class LibraryTabButtonClickListener implements ActionListener {
    private LibraryTab tab;

    public LibraryTabButtonClickListener(LibraryTab tab) {
        this.tab = tab;
    }
    // public ButtonClickListener(LibraryTab tab) {
    // this.tab = tab;
    // }

    // so much typing in java, hurts the soul almost as much as the wrist

    @Override
    public void actionPerformed(ActionEvent e) {
        String getValue = tab.getISBNField().getText();
        System.out.println(getValue);

    }
}
