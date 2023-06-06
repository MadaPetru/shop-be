package ro.adi.shop.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.data.domain.Sort;

import java.io.IOException;

public class SortDeserializer extends JsonDeserializer<Sort> {
    @Override
    public Sort deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String sortString = jsonParser.getValueAsString();
        // Parse the sortString and create a Sort instance
        // Example: Sort.by(Sort.Order.asc("fieldName"))
        // Implement the logic according to your specific needs
        //for now we will leave it like that
        return null;
    }
}
