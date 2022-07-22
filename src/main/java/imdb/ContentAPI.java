package imdb;

import java.util.List;

public enum ContentAPI {
    IMDB("https://api.mocki.io/v2/549a5d8b/Top250Movies", new ImdbContentExtractor()),
    NASA("https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&count=4", new NasaContentExtractor()),
    PROGRAMMING_LANGUAGE_API("http://localhost:8080/programmingLanguages", new ProgrammingLanguageContentExtractor());

    private final String apiUrl;
    private final ContentExtractor contentExtractor;

    ContentAPI(String apiUrl, ContentExtractor contentExtractor) {
        this.apiUrl = apiUrl;
        this.contentExtractor = contentExtractor;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public List<Content> extract(String json) {
        return this.contentExtractor.extract(json);
    }
}
