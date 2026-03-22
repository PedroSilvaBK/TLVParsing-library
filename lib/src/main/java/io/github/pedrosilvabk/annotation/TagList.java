package io.github.pedrosilvabk.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.SOURCE)
public @interface TagList {
    int containerTag();
    int itemTag();
    Class codec() default void.class;
}
