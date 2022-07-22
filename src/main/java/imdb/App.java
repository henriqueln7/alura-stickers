package imdb;

import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.net.URL;

import static com.diogonunes.jcolor.Ansi.colorize;
import static com.diogonunes.jcolor.Attribute.*;

public class App {
    public static void main(String[] args) throws IOException, InterruptedException {
        String apikey = System.getenv("IMDB_API_KEY");
        String url = "https://api.mocki.io/v2/549a5d8b/Top250Movies";
        HTTPClient httpClient = new HTTPClient();

        String body = httpClient.get(url);
        JsonNode movies = JsonParser.parse(body);

        StickerGenerator stickerGenerator = new StickerGenerator();

        for (JsonNode movie : movies) {
            System.out.println("Título: " + colorize(movie.get("title").asText(), BOLD()));
            System.out.println("Poster: " + colorize(movie.get("image").asText(), BOLD()));
            System.out.println(colorize("Avaliação: " + movie.get("imDbRating"), MAGENTA_BACK(), BLACK_TEXT()));
            stickerGenerator.generateSticker(new URL(movie.get("image").asText()).openStream(), movie.get("title") + ".jpg");
            Double imDbRating = Double.parseDouble(movie.get("imDbRating").asText());
            if (imDbRating == null) {
                System.out.println("Ainda não há avaliação para o filme.");
            } else {
                int imDbRatingFloor = (int) Math.floor(imDbRating);
                for (int i = 0; i < imDbRatingFloor; i++) {
                    System.out.print("🌟");
                }
                System.out.println();
            }
            System.out.println("---");
        }

    }
}
