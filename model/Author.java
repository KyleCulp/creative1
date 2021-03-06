package model;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import controller.Utils;


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
        // if(author.has("bio")) { this.bio = author.get("bio").getAsString(); }
        if(author.has("birthday")) { this.birthday = author.get("birthday").getAsString(); }
        if(author.has("deathday")) { this.deathday = author.get("deathday").getAsString(); }
        if(author.has("isni")) { this.isni = author.get("isni").getAsString(); }
        if(author.has("viaf")) { this.viaf = author.get("viaf").getAsString(); }
        
        // this.birthday = author.get("birthday").getAsString();
        // this.deathday = author.get("deathday").getAsString();
        // this.isni = author.get("isni").getAsString();
        // this.viaf = author.get("viaf").getAsString();
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
    public boolean isPhotosEmpty() { return photos.isEmpty(); }


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
