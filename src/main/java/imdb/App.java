package imdb;

import imdb.JsonParser.IMDBTopMovieResponse;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.http.*;
import java.util.List;

import static com.diogonunes.jcolor.Ansi.colorize;
import static com.diogonunes.jcolor.Attribute.*;
import static java.net.http.HttpResponse.BodyHandlers;

public class App {
    public static void main(String[] args) throws IOException, InterruptedException {
        String apikey = System.getenv("IMDB_API_KEY");
        String url = "https://api.mocki.io/v2/549a5d8b/Top250Movies";
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(URI.create(url)).GET().build();
        HttpResponse<String> httpResponse = httpClient.send(request, BodyHandlers.ofString());
        if (httpResponse.statusCode() >= 400) {
            System.out.println("Houve algum problema ao conectar com a API :')");
            return;
        }
        List<IMDBTopMovieResponse> movies = JsonParser.parse(httpResponse.body());

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
