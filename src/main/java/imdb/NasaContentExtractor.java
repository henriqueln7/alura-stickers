package imdb;

import java.util.List;
import java.util.Map;

public class NasaContentExtractor implements ContentExtractor {
    public List<Content> extract(String json) {
        List<Map<String, String>> contentParsed = JsonParser.parse(json);

        return contentParsed.stream().map(parsedContent -> {
            String title = parsedContent.get("title");
            String imageUrl = parsedContent.get("url");
            return new Content(title, imageUrl);
        }).toList();

    }
}
