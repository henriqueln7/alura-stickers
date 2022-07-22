package imdb;

import java.util.List;
import java.util.Map;

public class ProgrammingLanguageContentExtractor implements ContentExtractor {
    @Override
    public List<Content> extract(String json) {
        List<Map<String, String>> contentsParsed = JsonParser.parse(json);
        return contentsParsed.stream().map(parsedContent -> {
            String title = parsedContent.get("title");
            String imageUrl = parsedContent.get("imageUrl");
            return new Content(title, imageUrl);
        }).toList();
    }
}
