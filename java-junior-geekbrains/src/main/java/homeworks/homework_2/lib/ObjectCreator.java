package homeworks.homework_2.lib;

import java.lang.reflect.Constructor;

public class ObjectCreator {
    public static <T> T creatorObject(Class<T> tClass) {
        try {
            Constructor<T> constructor = tClass.getDeclaredConstructor();
            constructor.setAccessible(true);

            T object =  constructor.newInstance();
            RandomDateAnnotationProcessor.processAnnotation(object);
            return object;
        } catch (Exception e) {
            System.err.println("Создание объекта завершилось с ошибкой!");
            return null;
        }
    }
}
