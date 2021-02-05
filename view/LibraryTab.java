package view;

import java.awt.Dimension;
import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class LibraryTab {
    private JPanel panel = new JPanel();
    private JTextField isbnField = new JTextField(13);
    private JButton submitButton = new JButton("Submit");


    
    public LibraryTab() {
        panel.setPreferredSize(new Dimension(800, 600));
        
    }



    public JPanel getPanel() { return this.panel; }
    public JButton getSubmitButton() { return submitButton; }
    public JTextField getISBNField() { return isbnField; }
}
