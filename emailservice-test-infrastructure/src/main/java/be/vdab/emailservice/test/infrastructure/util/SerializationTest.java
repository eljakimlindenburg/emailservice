package be.vdab.emailservice.test.infrastructure.util;

import static org.assertj.core.api.Assertions.assertThat;

import be.vdab.emailservice.infrastructure.util.Jsons;
import org.junit.jupiter.api.Test;

public abstract class SerializationTest<T> {
    
    @Test
    public void objectSerializationTest() {
        var original = createInstance();
        T deserialized = serializeDeserialize(original, type());
        assertEqual(original, deserialized);
    }
    
    @SuppressWarnings("unchecked")
    protected Class<T> type() {
        return (Class<T>) Generics.parameterFromGenericClass(getClass());
    }
    
    protected void assertEqual(T original, T deserialized) {
        assertThat(deserialized).isEqualTo(original);
    }
    
    protected abstract T createInstance();
    
    protected T serializeDeserialize(T object, Class<T> tClass) {
        String serialized = Jsons.object2Json(object);
        return Jsons.json2Object(serialized, tClass);
    }
}
