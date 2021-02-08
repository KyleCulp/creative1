package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
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
        // panel.setPreferredSize(new Dimension(800, 600));
        // as many rows as needed, minimum of 2, but always 3 columns
        GridLayout layout = new GridLayout(3, 0);
        panel.setLayout(layout);
        layout.setHgap(1);
        layout.setVgap(1);
        init();
    }

    private void init() {
        for (int i = 0; i < library.size(); i++) {
            // panel.add(new JLabel(library.getBooks().get(i).getTitle()));
            JPanel boxPanel = createBookBox(library.getBooks().get(i));
            panel.add(boxPanel);
        }
    }

    private JPanel createBookBox(Book book) {
        JPanel boxPanel = new JPanel();
        boxPanel.setPreferredSize(new Dimension(200, 500));
        // so that it doesn't inherit FlowLayout by default
        // boxPanel.setLayout(null);
        BoxLayout layout = new BoxLayout(boxPanel, BoxLayout.Y_AXIS);
        boxPanel.setLayout(layout);       

        Border blackline = BorderFactory.createLineBorder(Color.black);
        boxPanel.setBorder(blackline);

        boxPanel.add(new JLabel("Title: " + book.getTitle()));
        boxPanel.add(new JLabel("Author: " + book.getFirstAuthor()));
        boxPanel.add(new JLabel("ISBN10: " + book.getISBN10()));
        boxPanel.add(new JLabel("ISBN13: " + book.getISBN13()));
        boxPanel.add(new JLabel("Published: " + book.getPublishDate()));
        boxPanel.add(new JLabel("Pages: " + Integer.toString(book.getPages())));


        if(!book.isPhotosEmpty()) {
            Image image = null;
            URL url = null;
            try {
                url = new URL(book.getPhotos().get(0));
                image = ImageIO.read(url);
            } catch (MalformedURLException ex) {
                System.out.println("Malformed URL");
            } catch (IOException iox) {
                System.out.println("Can not load file");
            }

            JLabel label = new JLabel(new ImageIcon(image));
            boxPanel.add(label, BorderLayout.CENTER);
        
        }

        // boxPanel.add(new JLabel(book.getISBN10()));

        return boxPanel;
    }

    public Library getLibrary() {
        return library;
    }

    public JPanel getPanel() {
        return this.panel;
    }

    public JButton getSubmitButton() {
        return submitButton;
    }

    public JTextField getISBNField() {
        return isbnField;
    }
}
