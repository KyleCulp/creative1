package model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import app.Utils;

public class Library {
    private List<Book> books = new ArrayList<Book>();
    private List<Author> authors = new ArrayList<Author>();

    public Library() {
        loadBooks();
        loadAuthors();
    }

    public int size() {
        return books.size();
    }

    public int authorSize() {
        return authors.size();
    }

    public List<Book> getBooks() { return books; }
    public List<Author> getAuthors() { return authors; }

    public void loadBooks() {
        Path currentPath = Paths.get(System.getProperty("user.dir"));
        Path dirPath = Paths.get(currentPath.toString(), "data", "books");
        File folder = new File(dirPath.toString());
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                Path file = listOfFiles[i].toPath();
                try {
                String json = new String(Files.readAllBytes(file));
                Book book = new Gson().fromJson(json, Book.class);
                books.add(book);
                } catch(IOException e) {
                    e.printStackTrace();
                }
            } 
        }
    } 
    
    public void loadAuthors() {
        Path currentPath = Paths.get(System.getProperty("user.dir"));
        Path dirPath = Paths.get(currentPath.toString(), "data", "authors");
        File folder = new File(dirPath.toString());
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                Path file = listOfFiles[i].toPath();
                try {
                String json = new String(Files.readAllBytes(file));
                Author author = new Gson().fromJson(json, Author.class);
                authors.add(author);
                } catch(IOException e) {
                    e.printStackTrace();
                }
            } 
        }
    }  

    // See if author is already loaded, therefore if it's downloaded or not
    public String hasAuthor(String author_key) {
        for(int i=0; i < authors.size(); i++) {
            if(authors.get(i).getKey() == author_key) {
                return authors.get(i).getName();
            }
        }
        return downloadAuthor(author_key);
    }


    public String downloadAuthor(String author_key) {
        JsonObject authorJson = new Utils().get_author(author_key);
        Author author = new Author(authorJson);
        author.save();

        return author.getName();
    }

}
