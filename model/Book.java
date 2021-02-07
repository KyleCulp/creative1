package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import app.Utils;

public class Book {

    private String title;
    private List<String> authors = new ArrayList<String>();
    private String author_key;
    private String publish_date;
    private String isbn_10;
    private String isbn_13;
    private String first_sentence;
    private String openlibrary_key; // example: /books/OL7353617M
    private int pages;
    private List<String> contributors;
    private List<String> photos;  // json key: covers

    public String getTitle() { return title; }
    public List<String> getAuthors() { return authors; }
    public String getPublishDate() { return publish_date; }
    public String getISBN10() { return isbn_10; }
    public String getISBN13() { return isbn_13; }
    public String getFirstSentence() { return first_sentence; }
    public String getOpenLibraryKey() { return openlibrary_key; }
    public int getPages() { return pages; }
    

    public Book(JsonObject book) {
        this.title = book.get("title").getAsString();
        this.authors = new Utils().getOrCreateAuthors(book.get("authors").getAsJsonArray());
        this.publish_date = book.get("publish_date").getAsString();
        this.isbn_10 = book.get("isbn_10").getAsJsonArray().get(0).getAsString();
        this.isbn_13 = book.get("isbn_13").getAsJsonArray().get(0).getAsString();
        this.first_sentence = initializeFirstSentence(book);
        this.pages = book.get("number_of_pages").getAsInt();
        this.openlibrary_key = book.get("key").getAsString();
        // this.contributors = initializeContributors(book);
        // this.photos = initializePhotos(book);
    }

    private List<String> initializeContributors(JsonObject book) {
        List<String> hold = new ArrayList<String>();
        JsonArray contributors = book.get("contributors").getAsJsonArray();

        for (int i = 0; i < contributors.size(); i++) {
            hold.add(contributors.get(i).getAsString());
        }

        return hold;
    }

    private String initializeFirstSentence(JsonObject book) {
        return book.get("first_sentence").getAsJsonObject().get("value").getAsString();
    }

    private List<String> initializePhotos(JsonObject book) {
        List<String> hold = new ArrayList<String>();
        JsonArray photos = book.get("photos").getAsJsonArray();

        for (int i = 0; i < photos.size(); i++) {
            String url = "https://covers.openlibrary.org/a/id/" + photos.get(i) + "-L.jpg";
            hold.add(url);
        }

        return hold;
    }

    public void save()  {
        Path currentPath = Paths.get(System.getProperty("user.dir"));
        Path path = Paths.get(currentPath.toString(), "data", "books", title + ".json");
        String json = new Gson().toJson(this);
        new Utils().saveStringToFile(path, json);
    }

// public Book(JsonObject book) {
// this.book = book;
// }

// public String[] getbooks() {

// }
}
