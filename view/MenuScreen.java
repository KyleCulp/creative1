package view;

import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import model.Library;

public class MenuScreen {
    private JFrame window;
    Library library = new Library();
    
    public MenuScreen() { }
    public MenuScreen(JFrame window) {
        this.window = window;
        window.setTitle("Library Database");
    }

    public void init() {
        Container cp = window.getContentPane();
        // JPanel panel = new JPanel();
        cp.setPreferredSize(new Dimension(800, 400));
        JTabbedPane tabbedPane = new JTabbedPane();

        LibraryTab libraryTab = new LibraryTab(library);
        tabbedPane.add("Library", libraryTab.getPanel());
        AuthorsTab authorsTab = new AuthorsTab();
        tabbedPane.add("Authors", authorsTab.getPanel());

        AddBookTab addBookTab = new AddBookTab();
        tabbedPane.add("Add Book", addBookTab.getPanel());
        
        cp.add(tabbedPane);
    }

    public Library getLibrary() { return library; }

}
