package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

public class MenuScreen {
    private JFrame window;

    public MenuScreen(JFrame window) {
        this.window = window;
        window.setTitle("Menu Screen");
    }

    public void init() {
        Container cp = window.getContentPane();
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(400, 200));
        panel.setLayout(new GridLayout(2, 1));

        JButton calcButton = new JButton("Calculator Simulator");
        panel.add(calcButton);
        cp.add(BorderLayout.CENTER, panel);

        calcButton.addActionListener(e -> {
            JsonObject jsonObject = get_json("https://openlibrary.org/books/OL7353617M.json");
            JsonArray authors = jsonObject.get("authors").getAsJsonArray();
            
            

            // Type listType = new TypeToken<List<String>>() {}.getType();


            // Gson gson = new Gson();
            // JSONObject book = get_json("https://openlibrary.org/books/OL7353617M.json");
            // JSONArray authors = book.getJSONArray("authors");
            for (int i = 0; i < authors.size(); i++) {
                JsonObject author = new Gson().fromJson(authors.get(i), JsonObject.class);
                System.out.print(author.get("key"));
            }
        });
    }

    public JsonObject get_json(String url) {
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
}
