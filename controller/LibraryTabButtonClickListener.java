package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import view.AddBookTab;
import view.LibraryTab;
import view.MenuScreen;

public class LibraryTabButtonClickListener implements ActionListener {
    private LibraryTab tab;

    public LibraryTabButtonClickListener(LibraryTab tab) {
        this.tab = tab;
    }
    // public ButtonClickListener(LibraryTab tab) {
    //     this.tab = tab;
    // }

    // so much typing in java, hurts the soul almost as much as the wrist

    @Override
    public void actionPerformed(ActionEvent e) {
        String getValue = tab.getISBNField().getText();
        System.out.println(getValue);
        // var button = e.getSource();
        // String m = panel.getDisplay().getText() + "\n";
        // if(button == panel.getSubmitButton()) {
        //     JFrame window = panel.getWindow();
        //     window.getContentPane().removeAll();
        //     var menu = new MenuScreen(window);
        //     menu.init();
        //     window.pack();
        //     window.revalidate();
        // } 
    }
}
