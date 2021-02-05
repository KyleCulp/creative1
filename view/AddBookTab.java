package view;

import java.awt.Dimension;
import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.AddBookTabButtonClickListener;


public class AddBookTab {
    private JPanel panel = new JPanel();
    private JTextField isbnField = new JTextField(13);
    private JButton submitButton = new JButton("Submit");


    
    public AddBookTab() {
        panel.setPreferredSize(new Dimension(800, 600));
        
        JLabel label1 = new JLabel("Enter ISBN10 or ISBN13");
        panel.add(label1);
        panel.add(isbnField);
        panel.add(submitButton);

        AddBookTabButtonClickListener buttonClickListener = new AddBookTabButtonClickListener(this);
        submitButton.addActionListener(buttonClickListener);
    }



    public JPanel getPanel() { return this.panel; }
    public JButton getSubmitButton() { return submitButton; }
    public JTextField getISBNField() { return isbnField; }
}
