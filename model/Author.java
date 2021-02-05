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


public class Author {
    private String name;
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
        this.bio = author.get("bio").getAsString();
        this.birthday = author.get("birthday").getAsString();
        this.deathday = author.get("deathday").getAsString();
        this.isni = author.get("isni").getAsString();
        this.viaf = author.get("viaf").getAsString();
        this.websites = getWebsites(author);
        this.photos = getPhotos(author);
    }


    public List<String> getWebsites(JsonObject author) { 
        List<String> hold = new ArrayList<String>();
        JsonArray websites = author.get("links").getAsJsonArray();

        for(int i=0; i < websites.size(); i++) {
            JsonObject website = websites.get(i).getAsJsonObject();
            hold.add(website.get("url").getAsString());
        }

        return hold;
    }

    public List<String> getPhotos(JsonObject author) {
        List<String> hold = new ArrayList<String>();
        JsonArray photos = author.get("photos").getAsJsonArray();

        for(int i=0; i < photos.size(); i++) {
            String url = "https://covers.openlibrary.org/a/id/" + photos.get(i).getAsString() + "-L.jpg";
            hold.add(url);
        }

        return hold;
     }

    public void saveAuthor(String path) {
        Path root = FileSystems.getDefault().getPath("").toAbsolutePath();
        Path filePath = Paths.get(root.toString(), "data", "authors");
        Gson gson = new Gson();
        String json = gson.toJson(this);
        // LibraryUtils utils = new LibraryUtils();    
        // utils.saveStringToFile(json, filepath);


        try {   
            File file = new File(filePath.toString());
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

    public void createIfNonexistent(String id) {

    }



}
