package com.hewei.formblocks;


import com.hewei.formblocks.annotations.TypeConverter;
import com.hewei.formblocks.annotations.TypeConverters;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class FormManager {
    public void init(Class<?> cls) {
        Annotation[] annotations = cls.getDeclaredAnnotations();
        for (Annotation annotation : annotations) {
            if (annotation instanceof TypeConverters) {
                TypeConverters converters = (TypeConverters) annotation;
                for (Class<?> c : converters.value()) {
                    initConverterInClass(c);
                }
            }
        }
    }

    private void initConverterInClass(Class<?> cls) {
        Method[] methods = cls.getDeclaredMethods();
        for (Method m : methods) {
            TypeConverter converter = m.getAnnotation(TypeConverter.class);
            if (converter != null) {
                registerMethod(m);
            }
        }
    }

    private void registerMethod(Method m) {

    }
}
