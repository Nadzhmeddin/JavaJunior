package homeworks.second_homework.lib;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface RandomDate {
    long min() default 1355256000000L; // 12 декабря 2012 год
    long max() default 1735678800000L; // 01 января 2025 год.
}
