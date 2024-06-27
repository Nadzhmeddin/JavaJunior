package seminars.seminar_2.annotation.lib;

import java.lang.reflect.Constructor;

public class ObjectCreator {

    public static <T> T createObj(Class<T> tClass) {
        try{
            Constructor<T> constructor = tClass.getDeclaredConstructor();
            constructor.setAccessible(true);

            T obj = constructor.newInstance();
            RandomAnnotationProcessor.processAnnotation(obj);
            return obj;
        } catch (Exception e) {
            System.err.println("Не получилось создать объект");
            return null;
        }
    }
}
