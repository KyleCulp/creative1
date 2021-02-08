package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import model.Author;
import model.Library;

// import model.Authors;

public class AuthorsTab {
    private JPanel panel = new JPanel();
    private JTextField isbnField = new JTextField(13);
    private JButton submitButton = new JButton("Submit");
    private Library library;


    
    public AuthorsTab(Library library) {
        this.library = library;
        GridLayout layout = new GridLayout(3, 0);
        panel.setLayout(layout);
        layout.setHgap(1);
        layout.setVgap(1);
        init();

    }
    private void init() {
        for (int i = 0; i < library.authorSize(); i++) {
            // panel.add(new JLabel(library.getBooks().get(i).getTitle()));
            JPanel boxPanel = createAuthorBox(library.getAuthors().get(i));
            panel.add(boxPanel);
        }
    }

    private JPanel createAuthorBox(Author author) {
        JPanel boxPanel = new JPanel();
        boxPanel.setPreferredSize(new Dimension(200, 500));
        // so that it doesn't inherit FlowLayout by default
        // boxPanel.setLayout(null);
        BoxLayout layout = new BoxLayout(boxPanel, BoxLayout.Y_AXIS);
        boxPanel.setLayout(layout);       

        Border blackline = BorderFactory.createLineBorder(Color.black);
        boxPanel.setBorder(blackline);

        boxPanel.add(new JLabel("Name: " + author.getName()));


        if(!author.isPhotosEmpty()) {
            Image image = null;
            URL url = null;
            try {
                url = new URL(author.getPhotos().get(0));
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


    public JPanel getPanel() { return this.panel; }
    public JButton getSubmitButton() { return submitButton; }
    public JTextField getISBNField() { return isbnField; }
}
