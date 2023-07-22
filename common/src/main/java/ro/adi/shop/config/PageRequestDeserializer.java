package ro.adi.shop.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.IOException;

public class PageRequestDeserializer extends JsonDeserializer<PageRequest> {

    @Override
    public PageRequest deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        int page = node.get("page").asInt();
        int size = node.get("size").asInt();
        String direction = node.get("sortDirection").asText();
        String property = node.get("sortProperty").asText();

        Sort sort = Sort.by(Sort.Direction.fromString(direction), property);
        Pageable pageable = PageRequest.of(page, size, sort);

        return (PageRequest) pageable;
    }
}