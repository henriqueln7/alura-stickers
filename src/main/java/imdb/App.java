package imdb;

import java.io.IOException;
import java.util.List;

import static com.diogonunes.jcolor.Ansi.colorize;
import static com.diogonunes.jcolor.Attribute.BOLD;

public class App {
    public static void main(String[] args) throws IOException, InterruptedException {
        String apikey = System.getenv("IMDB_API_KEY");
        String url = "https://api.mocki.io/v2/549a5d8b/Top250Movies";
//        String url = "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&count=4";
        HTTPClient httpClient = new HTTPClient();

        String body = httpClient.get(url);
//        ContentExtractor nasaContentExtractor = new NasaContentExtractor();
        ContentExtractor imdbContentExtractor = new ImdbContentExtractor();
        List<Content> contents = imdbContentExtractor.extract(body);

        StickerGenerator stickerGenerator = new StickerGenerator();

        for (Content content : contents) {
            System.out.println("Título: " + colorize(content.title(), BOLD()));
            System.out.println("Poster: " + colorize(content.imageUrl(), BOLD()));
            stickerGenerator.generateSticker(content);
            System.out.println("---");
        }

    }
}
