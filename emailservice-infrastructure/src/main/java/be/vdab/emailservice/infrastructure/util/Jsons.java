package be.vdab.emailservice.infrastructure.util;

import be.vdab.emailservice.infrastructure.exception.JsonException;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.IOException;
import java.io.InputStream;

public final class Jsons {
    
    public static final ObjectMapper OBJECT_MAPPER = createObjectMapper();
    
    private Jsons() {
        throw new AssertionError("No instances of utility class");
    }
    
    private static ObjectMapper createObjectMapper() {
        var om = new ObjectMapper();
        om.registerModule(new JavaTimeModule());
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        om.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        om.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return om;
    }
    
    public static ObjectNode createEmptyObjectNode() {
        return OBJECT_MAPPER.createObjectNode();
    }
    
    public static ObjectNode streamToObjectNode(InputStream inputStream) {
        try {
            return (ObjectNode) OBJECT_MAPPER.readTree(inputStream);
        } catch (IOException e) {
            throw JsonException.from(e);
        }
    }
    
    public static ObjectNode object2ObjectNode(Object obj) {
        return OBJECT_MAPPER.valueToTree(obj);
    }
    
    public static String object2Json(Object obj) {
        try {
            return OBJECT_MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw JsonException.from(e);
        }
    }
    
    public static <T> T json2Object(String serialized, Class<T> tClass) {
        try {
            return OBJECT_MAPPER.readValue(serialized, tClass);
        } catch (IOException e) {
            throw JsonException.from(e);
        }
    }
}
