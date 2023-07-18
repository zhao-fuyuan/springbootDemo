package com.example.demo.config;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface TableSplit {
    String tableName();

    String shardType();

    String shardBy();

    String shardRange() default "";
}
