package imdb;

import imdb.JsonParser.IMDBTopMovieResponse;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import static com.diogonunes.jcolor.Ansi.colorize;
import static com.diogonunes.jcolor.Attribute.*;

public class App {
    public static void main(String[] args) throws IOException, InterruptedException {
        String apikey = System.getenv("IMDB_API_KEY");
        String url = "https://api.mocki.io/v2/549a5d8b/Top250Movies";
        HTTPClient httpClient = new HTTPClient();

        String body = httpClient.get(url);
        List<IMDBTopMovieResponse> movies = JsonParser.parse(body);

        StickerGenerator stickerGenerator = new StickerGenerator();

        for (IMDBTopMovieResponse movie : movies) {
            System.out.println("Título: " + colorize(movie.title(), BOLD()));
            System.out.println("Poster: " + colorize(movie.thumbnailImageUrl(), BOLD()));
            System.out.println(colorize("Avaliação: " + movie.imdbRating(), MAGENTA_BACK(), BLACK_TEXT()));
            stickerGenerator.generateSticker(new URL(movie.thumbnailImageUrl()).openStream(), movie.title() + ".jpg");
            Double imDbRating = movie.imdbRating();
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
