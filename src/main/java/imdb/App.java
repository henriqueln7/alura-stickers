package imdb;

import java.io.IOException;
import java.net.URI;
import java.net.http.*;
import java.util.List;
import java.util.Map;

import static java.net.http.HttpResponse.*;

public class App {
    public static void main(String[] args) throws IOException, InterruptedException {
        String apikey = System.getenv("IMDB_API_KEY");
        String url = "https://imdb-api.com/en/API/Top250Movies/" + apikey;
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(URI.create(url)).GET().build();
        HttpResponse<String> send = httpClient.send(request, BodyHandlers.ofString());
        List<Map<String, String>> movies = JsonParser.parse(send.body());

        for (Map<String, String> movie : movies) {
            System.out.println("title: " + movie.get("title"));
            System.out.println("image: " + movie.get("image"));
            System.out.println("imDbRating: " + movie.get("imDbRating"));
            System.out.println("---");
        }

    }
}
