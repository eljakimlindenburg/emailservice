package be.vdab.emailservice.infrastructure.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import be.vdab.emailservice.infrastructure.exception.JsonException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class JsonsTest {
    
    @Test
    void whenCreatingInstanceOfJsons_throwException() throws NoSuchMethodException {
        Constructor<Jsons> constructor = Jsons.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        Assertions.assertThatExceptionOfType(InvocationTargetException.class)
                .isThrownBy(constructor::newInstance)
                .withCauseExactlyInstanceOf(AssertionError.class);
    }
    
    @Test
    void createEmptyObjectNode_returnsEmptyObjectNode() {
        ObjectNode node = Jsons.createEmptyObjectNode();
        
        assertThat(node.isEmpty()).isTrue();
    }
    
    @Test
    void givenInputStreamThrowsIOException_whenStreamToJsonNode_thenThrowJsonException() {
        InputStream stream = Mockito.mock(InputStream.class);
        
        assertThatExceptionOfType(JsonException.class)
                .isThrownBy(() -> Jsons.streamToObjectNode(stream));
    }
    
    @Test
    void givenInputStream_whenStreamToJsonNode_thenCorrectJsonNode() {
        ByteArrayInputStream stream = new ByteArrayInputStream("{\"key\":\"value\"}".getBytes(StandardCharsets.UTF_8));
        JsonNode node = Jsons.streamToObjectNode(stream);
        
        assertThat(node.get("key").asText()).isEqualTo("value");
    }
    
    @Test
    void givenSerializableObject_whenMapToJsonString_thenSerializedCorrectly() {
        SimpleClass object = new SimpleClass("testValue", "testValue2");
        String jsonString = Jsons.object2Json(object);
        
        assertThat(jsonString).isEqualTo("{\"value\":\"testValue\",\"value2\":\"testValue2\"}");
    }
    
    @Test
    void givenUnSerializableObject_whenMapToJsonString_thenThrowException() {
        UnserializableClass unserializableClass = new UnserializableClass();
        assertThatExceptionOfType(JsonException.class)
                .isThrownBy(() -> Jsons.object2Json(unserializableClass));
    }
    
    @Test
    void givenJsonString_whenMapJsonStringToObject_thenIsParsedCorrectly() {
        SimpleClass object = new SimpleClass("testValue", "testValue2");
        String jsonString = Jsons.object2Json(object);
        
        SimpleClass deserializedObject = Jsons.json2Object(jsonString, SimpleClass.class);
        
        assertThat(deserializedObject).isEqualTo(object);
    }
    
    @Test
    void givenInvalidJsonString_throwException() {
        assertThatExceptionOfType(JsonException.class)
                .isThrownBy(() -> Jsons.json2Object("lolol", SimpleClass.class));
    }
    
    public static class SimpleClass {
        
        private String value;
        private String value2;
        
        public SimpleClass(String value, String value2) {
            this.value = value;
            this.value2 = value2;
        }
        
        private SimpleClass() {
        }
        
        public String getValue() {
            return value;
        }
        
        public String getValue2() {
            return value2;
        }
        
        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            SimpleClass that = (SimpleClass) o;
            return Objects.equals(value, that.value) &&
                    Objects.equals(value2, that.value2);
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(value, value2);
        }
    }
    
    public static class UnserializableClass {
        
        private final UnserializableClass self = this;
        
        @Override
        public String toString() {
            return self.getClass().getName();
        }
    }
}
