package edu.hw6.Task5;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HackerNews {

    private final HttpClient httpClient;
    private final Pattern titlePattern = Pattern.compile("\"title\"\\s*:\\s*\"([^\"]*)\"");

    public HackerNews() {this.httpClient = HttpClient.newHttpClient();}

    public long[] hackerNewsTopStories() {
        HttpRequest request = HttpRequest.newBuilder()
            .GET()
            .uri(java.net.URI.create("https://hacker-news.firebaseio.com/v0/topstories.json"))
            .build();

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            String json = response.body();
            json = json.replaceAll("[\\[\\]]", "");

            String[] ids = json.split(",");
            long[] result = new long[ids.length];

            for (int i = 0; i < ids.length; i++) {
                result[i] = Long.parseLong(ids[i].trim());
            }

            return result;

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return new long[0];
        }
    }

    public String news(long id) {
        HttpRequest request = HttpRequest.newBuilder()
            .GET()
            .uri(java.net.URI.create("https://hacker-news.firebaseio.com/v0/item/" + id + ".json"))
            .build();

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            String json = response.body();
            Matcher matcher = titlePattern.matcher(json);

            if (matcher.find()) {
                return matcher.group(1);
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return "No news found for the given ID.";
    }
}
