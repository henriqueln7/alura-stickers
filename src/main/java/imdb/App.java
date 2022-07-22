package imdb;

import java.io.IOException;
import java.util.List;

import static com.diogonunes.jcolor.Ansi.colorize;
import static com.diogonunes.jcolor.Attribute.BOLD;

public class App {
    public static void main(String[] args) throws IOException {
        ContentAPI[] apis = ContentAPI.values();
        HTTPClient httpClient = new HTTPClient();
        StickerGenerator stickerGenerator = new StickerGenerator();
        for (ContentAPI api : apis) {
            String body = httpClient.get(api.getApiUrl());
            List<Content> contents = api.extract(body);
            for (Content content : contents) {
                System.out.println("Título: " + colorize(content.title(), BOLD()));
                System.out.println("Poster: " + colorize(content.imageUrl(), BOLD()));
                stickerGenerator.generateSticker(content);
                System.out.println("---");
            }
        }
    }
}
