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
            System.out.println("title: \u001b[1m" + movie.get("title") + " \u001b[m");
            System.out.println("image: \u001b[1m" + movie.get("image") + " \u001b[m");
            System.out.println("\u001b[45m \u001b[30mimDbRating: \u001b[1m" + movie.get("imDbRating") + " \u001b[m");
            int imDbRatingFloor = (int) Math.floor(Double.parseDouble(movie.get("imDbRating")));
            for (int i = 0; i < imDbRatingFloor; i++) {
                System.out.print("\uD83C\uDF1F");
            }
            System.out.println();
            System.out.println("---");
        }

    }
}