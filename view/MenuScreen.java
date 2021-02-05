package view;

import java.awt.Container;
import java.awt.Dimension;


import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class MenuScreen {
    private JFrame window;

    public MenuScreen(JFrame window) {
        this.window = window;
        window.setTitle("Library Database");
    }

    public void init() {
        Container cp = window.getContentPane();
        // JPanel panel = new JPanel();
        cp.setPreferredSize(new Dimension(800, 400));
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setBounds(50,50,200,200); 

        LibraryTab libraryTab = new LibraryTab();
        tabbedPane.add("Library", libraryTab.getPanel());

        AuthorsTab authorsTab = new AuthorsTab();
        tabbedPane.add("Authors", authorsTab.getPanel());

        AddBookTab addBookTab = new AddBookTab();
        tabbedPane.add("Add Book", addBookTab.getPanel());
        
        cp.add(tabbedPane);
    }

}
