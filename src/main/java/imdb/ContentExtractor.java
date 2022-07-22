package imdb;

import java.util.List;

public interface ContentExtractor {
    List<Content> extract(String json);
}
