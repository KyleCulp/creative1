package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import model.Book;
import model.Library;


public class LibraryTab {
    private JPanel panel = new JPanel();
    private JTextField isbnField = new JTextField(13);
    private JButton submitButton = new JButton("Submit");
    private Library library;

    
    public LibraryTab(Library library) {
        this.library = library;
        panel.setPreferredSize(new Dimension(800, 600));
        // as many rows as needed, but always 3 columns
        GridLayout layout = new GridLayout(0, 3);
        panel.setLayout(layout);  
        init();
    }

    private void init() {
        for(int i=0; i < library.size(); i++) {
            // panel.add(new JLabel(library.getBooks().get(i).getTitle()));
            JPanel boxPanel = createBookBox(library.getBooks().get(i));
            panel.add(boxPanel);
        }
    }
    
    private JPanel createBookBox(Book book) {
        JPanel boxPanel = new JPanel();
        boxPanel.setSize(new Dimension(200, 400));
        // so that it doesn't inherit FlowLayout by default
        // boxPanel.setLayout(null);
    
        Border blackline = BorderFactory.createLineBorder(Color.black);
        boxPanel.setBorder(blackline);
    
        boxPanel.add(new JLabel(book.getISBN10()));
        boxPanel.add(new JLabel(book.getTitle()));
    
        return boxPanel;
    }

    public Library getLibrary() { return library; }
    public JPanel getPanel() { return this.panel; }
    public JButton getSubmitButton() { return submitButton; }
    public JTextField getISBNField() { return isbnField; }
}
