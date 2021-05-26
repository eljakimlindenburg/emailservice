package be.pxl.emailservice.test.infrastructure.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

@SuppressWarnings("squid:S3011")
public class Whitebox {
    
    private Whitebox() {
        throw new AssertionError("No instances of utility class");
    }
    
    public static <T> T createInstance(Class<T> clazz) {
        try {
            Constructor<T> constructor = clazz.getDeclaredConstructor();
            constructor.setAccessible(true);
            return constructor.newInstance();
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            throw new WhiteboxException("Failed to create instance with no-arg constructor", e);
        }
    }
    
    public static void setInternalState(Object target, String field, Object value) {
        Class<?> c = target.getClass();
        try {
            var f = getFieldFromHierarchy(c, field);
            f.setAccessible(true);
            f.set(target, value);
        } catch (Exception e) {
            throw new WhiteboxException("Unable to set internal state on a private field. Please report to mockito mailing list.", e);
        }
    }
    
    private static Field getFieldFromHierarchy(Class<?> clazz, String field) {
        var f = getField(clazz, field);
        while (f == null && clazz != Object.class) {
            clazz = clazz.getSuperclass();
            f = getField(clazz, field);
        }
        if (f == null) {
            throw new WhiteboxException(
                    "You want me to get this field: '" + field +
                            "' on this class: '" + clazz.getSimpleName() +
                            "' but this field is not declared withing hierarchy of this class!");
        }
        return f;
    }
    
    private static Field getField(Class<?> clazz, String field) {
        try {
            return clazz.getDeclaredField(field);
        } catch (NoSuchFieldException e) {
            return null;
        }
    }
    
    public static class WhiteboxException extends RuntimeException {
        
        WhiteboxException(String message) {
            super(message);
        }
        
        WhiteboxException(String message, Exception cause) {
            super(message, cause);
        }
    }
}
