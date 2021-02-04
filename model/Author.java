package model;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class Author {
    private String name;
    private String bio;
    private String birthday;
    private String deathday;
    private String isni;
    private String viaf;
    private List<String> websites;
    private List<String> photos;

    public Author(JsonObject author) {
        this.name = author.get("name").getAsString();
        this.bio = author.get("bio").getAsString();
        this.birthday = author.get("birthday").getAsString();
        this.deathday = author.get("deathday").getAsString();
        this.isni = author.get("isni").getAsString();
        this.viaf = author.get("viaf").getAsString();
        this.viaf = author.get("viaf").getAsString();
    }

    public List<String> getWebsites(JsonObject author) { 
        List<String> hold = new ArrayList<String>;
        return hold;
    }
    public List<String> getPhotos(JsonObject author) {
        List<String> hold = new ArrayList<String>;
        return hold;
     }

    public void saveAuthor(Author author) {
        Gson gson = new Gson();
        String filepath = "/data/authors/" + this.name;
        String json = gson.toJson(author);
        saveJson(json, filepath);
    }

    public void saveJson(String data, String filename) {
        try (
          FileWriter fw = new FileWriter(filename)) {
          fw.write(data);
        } catch (Exception e) {
          throw new RuntimeException(e);
        }
    }


    // Todo: Build image urls   
    // https://covers.openlibrary.org/a/id/$(Image Id)-L.jpg

}
