package app;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;


public class Utils {
    public JsonObject get_book(String isbn) {
        return get_json("https://openlibrary.org/books/" + isbn + ".json");
    }

    public JsonObject get_author(String id) {
        return get_json("https://openlibrary.org/authors/" + id + ".json");
    }

    private JsonObject get_json(String url) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();

        HttpResponse<String> response;
        try {
            response = client.send(request, BodyHandlers.ofString());

            return new Gson().fromJson(response.body(), JsonObject.class);
        } catch (IOException | InterruptedException e1) {
            e1.printStackTrace();
        }
        // bad programming i hope the call doesn't fail
        return new JsonObject();
    }

    public String findFile(String type) {
        switch(type) {
            case "Book":
                return _findFile(directory);
                break;
            case "Author":
                return _findFile(directory);
                break;
        }
        case type
    }

    private String _findFile(String directory) {

    }
}
