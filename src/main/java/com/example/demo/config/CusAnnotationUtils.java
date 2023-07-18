package com.example.demo.config;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class CusAnnotationUtils {
    public CusAnnotationUtils() {
    }

    public static <T extends Annotation> T getAnnotationValueFromMethodOrClass(String clazzName, String methodName, Class<T> annotationClass) throws Exception {
        T annotationVal = null;
        Class<?> clazz = Class.forName(clazzName);
        Method[] var5 = clazz.getDeclaredMethods();
        int var6 = var5.length;

        for(int var7 = 0; var7 < var6; ++var7) {
            Method method = var5[var7];
            if (method.isAnnotationPresent(annotationClass) && methodName.equals(method.getName())) {
                annotationVal = method.getAnnotation(annotationClass);
                break;
            }
        }

        if (annotationVal == null) {
            annotationVal = clazz.getAnnotation(annotationClass);
        }

        return annotationVal;
    }
}

