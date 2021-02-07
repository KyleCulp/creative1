package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import app.Utils;


public class Author {
    private String name;
    private String key;
    private String bio;
    private String birthday;
    private String deathday;
    private String isni;
    private String viaf;
    private List<String> websites;
    private List<String> photos;
    public Author() {}

    public Author(JsonObject author) {
        this.name = author.get("name").getAsString();
        this.key = author.get("name").getAsString();
        this.bio = author.get("bio").getAsString();
        this.birthday = author.get("birthday").getAsString();
        this.deathday = author.get("deathday").getAsString();
        this.isni = author.get("isni").getAsString();
        this.viaf = author.get("viaf").getAsString();
        this.websites = getWebsites(author);
        this.photos = getPhotos(author);
    }

    public String getName() { return name; }
    public String getKey() { return key; }
    public String getBio() { return bio; }
    public String getBirthday() { return birthday; }
    public String getDeathday() { return deathday; }
    public String getIsni() { return isni; }
    public String getViaf() { return viaf; }
    public List<String> getWebsites() { return websites; }
    public List<String> getPhotos() { return photos; }


    private List<String> getWebsites(JsonObject author) { 
        List<String> hold = new ArrayList<String>();
        JsonArray websites = author.get("links").getAsJsonArray();

        for(int i=0; i < websites.size(); i++) {
            JsonObject website = websites.get(i).getAsJsonObject();
            hold.add(website.get("url").getAsString());
        }

        return hold;
    }

    private List<String> getPhotos(JsonObject author) {
        List<String> hold = new ArrayList<String>();
        JsonArray photos = author.get("photos").getAsJsonArray();

        for(int i=0; i < photos.size(); i++) {
            String url = "https://covers.openlibrary.org/a/id/" + photos.get(i).getAsString() + "-L.jpg";
            hold.add(url);
        }

        return hold;
     }

     public void save()  {
        Path currentPath = Paths.get(System.getProperty("user.dir"));
        Path path = Paths.get(currentPath.toString(), "data", "authors", name + ".json");
        String json = new Gson().toJson(this);
        new Utils().saveStringToFile(path, json);
    }

}
