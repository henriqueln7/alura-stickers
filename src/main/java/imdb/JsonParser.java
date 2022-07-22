package imdb;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;

public class JsonParser {

    private JsonParser() {}

    public static JsonNode parse(String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(com.fasterxml.jackson.core.JsonParser.Feature.IGNORE_UNDEFINED, true);
        JsonNode jsonNode = mapper.readTree(json);
        return jsonNode.get("items");
    }

}
