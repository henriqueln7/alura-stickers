package imdb;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class JsonParser {

    private JsonParser() {}

    public static List<IMDBTopMovieResponse> parse(String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(com.fasterxml.jackson.core.JsonParser.Feature.IGNORE_UNDEFINED, true);
        IMDBTop250MoviesResponse map = mapper.readValue(json, IMDBTop250MoviesResponse.class);
        return map.items;
    }

    private record IMDBTop250MoviesResponse(List<IMDBTopMovieResponse> items, String errorMessage) {}

    record IMDBTopMovieResponse(String id, int rank, String title, String fullTitle, int year, String image, String crew,
                                @JsonProperty("imDbRating") Double imdbRating,
                                @JsonProperty("imDbRatingCount") Double imdbRatingCount) {
    }

}
