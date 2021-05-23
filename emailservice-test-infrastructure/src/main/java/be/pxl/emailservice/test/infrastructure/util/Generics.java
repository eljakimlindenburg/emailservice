package be.pxl.emailservice.test.infrastructure.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Generics {
    
    private Generics() {
        throw new AssertionError("utility class, do not instantiate");
    }
    
    public static Class<?> parameterFromGenericClass(Class<?> subType) {
        ParameterizedType type = (ParameterizedType) subType.getGenericSuperclass();
        Type parameter = type.getActualTypeArguments()[0];
        if (Class.class.isAssignableFrom(parameter.getClass())) {
            return (Class<?>) ((ParameterizedType) subType.getGenericSuperclass()).getActualTypeArguments()[0];
        } else {
            return getClassFromTypeName(parameter.getTypeName());
        }
    }
    
    private static Class<?> getClassFromTypeName(String typeName) {
        @SuppressWarnings("squid:S4784")
        Pattern pattern = Pattern.compile("^(.*)<.*>$");
        Matcher typeNameMatcher = pattern.matcher(typeName);
        if (typeNameMatcher.matches()) {
            return classFromString(typeNameMatcher.group(1));
        } else {
            return classFromString(typeName);
        }
    }
    
    private static Class<?> classFromString(String group) {
        try {
            return Class.forName(group);
        } catch (ClassNotFoundException exc) {
            throw new GenericsException(exc);
        }
    }
    
    public static class GenericsException extends RuntimeException {
        
        GenericsException(ClassNotFoundException cause) {
            super(cause.getMessage(), cause);
        }
    }
}
