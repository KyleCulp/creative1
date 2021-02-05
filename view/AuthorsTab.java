package view;

import java.awt.Dimension;
import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import model.Authors;

public class AuthorsTab {
    private JPanel panel = new JPanel();
    private JTextField isbnField = new JTextField(13);
    private JButton submitButton = new JButton("Submit");
    Authors authors = new Authors();


    
    public AuthorsTab() {
        panel.setPreferredSize(new Dimension(800, 600));
        
        JLabel label1 = new JLabel("Enter ISBN10 or ISBN13", SwingConstants.CENTER);
        panel.add(label1, BorderLayout.PAGE_START);
//         panel.add(label1, BorderLayout.PAGE_START);

    }



    public JPanel getPanel() { return this.panel; }
    public JButton getSubmitButton() { return submitButton; }
    public JTextField getISBNField() { return isbnField; }
}
