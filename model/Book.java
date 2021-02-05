package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import app.Utils;

public class Book {

    private String title;
    private String author;
    private String publish_date;
    private String isbn_10;
    private String isbn_13;
    private String first_sentence;
    private int pages;
    private String openlibrary_key;
    private List<String> contributors;
    private List<String> photos;

    
    public Book(String isbn) {
        JsonObject bookJson = new Utils().get_book(isbn);
        new Author().createIfNonexistent(bookJson.get(memberName)
    }

    public String fetchBook(string isbn) {}

    public Book(JsonObject book) {
        this.title = book.get("title").getAsString();
        // this.author = getOrCreateAuthor(book);
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

    public void save(String path) throws IOException {
        Gson gson = new Gson();
        String filepath = path + "\\data\\books\\" + this.title + ".json";
        String json = gson.toJson(this);
        // LibraryUtils utils = new LibraryUtils();    
        // utils.saveStringToFile(json, filepath);


        try {   
            File file = new File(filepath);
            try {
                if (file.createNewFile()) {
                    System.out.println("File created");
                } else {
                    System.out.println("File already exists!");
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            BufferedWriter br = new BufferedWriter(new FileWriter(file));
            br.write(json);
            br.close();
        
        } catch (IOException e) {
            e.printStackTrace();
        }   
    }

// public Book(JsonObject book) {
// this.book = book;
// }

// public String[] getbooks() {

// }
}
