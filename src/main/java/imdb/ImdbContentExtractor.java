package imdb;

import java.util.List;
import java.util.Map;

public class ImdbContentExtractor implements ContentExtractor {
    @Override
    public List<Content> extract(String json) {
        List<Map<String, String>> contentParsed = JsonParser.parse(json);

        return contentParsed.stream().map(parsedContent -> {
            String title = parsedContent.get("title");
            String imageUrl = parsedContent.get("image");
            return new Content(title, imageUrl);
        }).toList();

    }
}
