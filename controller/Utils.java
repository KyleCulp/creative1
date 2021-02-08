package controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import model.Library;
import view.MenuScreen;

public class Utils {
    public JsonObject get_book(String isbn) {
        return getJson("https://openlibrary.org/isbn/" + isbn + ".json");
    }

    public JsonObject get_author(String id) { // id in: /authors/id format
        return getJson("https://openlibrary.org/" + id + ".json");
    }

    // Open Library redirects the isbn api to their books api, causing some issues
    private String getRealURL(String url) {
        try {
            HttpURLConnection con = (HttpURLConnection) (new URL(url).openConnection());
            con.setInstanceFollowRedirects(false);
            con.connect();
            int responseCode = con.getResponseCode();
            String location = con.getHeaderField("Location");
            return location;
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return "Not Found";
    }

    public JsonObject getJson(String url) {
        url = getRealURL(url);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();

        HttpResponse<String> response;
        try {
            response = client.send(request, BodyHandlers.ofString());
            return new Gson().fromJson(response.body(), JsonObject.class);
        } catch (IOException | InterruptedException e1) {
            e1.printStackTrace();
        }
        return new JsonObject();
    }

    public void saveStringToFile(Path path, String json) {
        try {
            File file = new File(path.toString());
            if (file.exists())
                return;
            BufferedWriter br = new BufferedWriter(new FileWriter(file));
            br.write(json);
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> getOrCreateAuthors(JsonArray authorsArray) {
        List<String> authors = new ArrayList<String>();
        for (int i = 0; i < authorsArray.size(); i++) {
            JsonObject author = authorsArray.get(i).getAsJsonObject();
            String author_key = author.get("key").getAsString();
            String author_name = _getOrCreateAuthor(author_key);
            authors.add(author_name);
        }
        return authors;
    }

    private String _getOrCreateAuthor(String author_key) {
        Library library = new MenuScreen().getLibrary();
        String potentialAuthor = library.hasAuthor(author_key);
        return potentialAuthor;
    }

}
