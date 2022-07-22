package imdb;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import static com.diogonunes.jcolor.Ansi.colorize;
import static com.diogonunes.jcolor.Attribute.BOLD;

public class App {
    public static void main(String[] args) throws IOException, InterruptedException {
        String apikey = System.getenv("IMDB_API_KEY");
//        String url = "https://api.mocki.io/v2/549a5d8b/Top250Movies";
        String url = "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&count=4";
        HTTPClient httpClient = new HTTPClient();

        String body = httpClient.get(url);
        List<Map<String, String>> contents = JsonParser.parse(body);

        StickerGenerator stickerGenerator = new StickerGenerator();

        for (Map<String, String> content : contents) {
            System.out.println("Título: " + colorize(content.get("title"), BOLD()));
            System.out.println("Poster: " + colorize(content.get("url"), BOLD()));
            stickerGenerator.generateSticker(new URL(content.get("url")).openStream(), content.get("title") + ".jpg");
            System.out.println("---");
        }

    }
}
