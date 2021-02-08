package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import app.App;
import app.Utils;
import model.Book;
import view.AddBookTab;

public class AddBookTabButtonClickListener implements ActionListener {
    private AddBookTab tab;


    public AddBookTabButtonClickListener(AddBookTab tab) {
        this.tab = tab;
    }

    // so much typing in java, hurts the soul almost as much as the wrist

    @Override
    public void actionPerformed(ActionEvent e) {
        String isbn = tab.getISBNField().getText();
        JsonObject bookJson = new Utils().get_book(isbn);

        Book book = new Book(bookJson);
        book.save();
        tab.showNewlyDownloadedBook(book);
    }
}
