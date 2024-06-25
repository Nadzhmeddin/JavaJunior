package seminars.second_seminar.annotation.lib;

import java.lang.reflect.Field;

public class RandomAnnotationProcessor {
    public static void processAnnotation(Object object) {

        // найти все поля класса, над которыми стоит аннотация @Random
        // вставить туда рандомное число в диапазоне [0, 100)

        java.util.Random random = new java.util.Random();
        Class<?> objClass = object.getClass();
        for (Field field : objClass.getDeclaredFields()) {

            //instanceOf
            // object instanceOf Person
//            AnnotationsMain.Person.class.isInstance(object)
//            object.getClass().isAssignableFrom(AnnotationsMain.Person.class)

            if(field.isAnnotationPresent(Random.class) && field.getType().isAssignableFrom(int.class)) {
                Random annotation = field.getAnnotation(Random.class);
                int min = annotation.min();
                int max = annotation.max();
                try {
                    field.setAccessible(true); // чтобы можно было изменять финальные поля
                    field.set(object, random.nextInt(min, max));
                } catch (IllegalAccessException e) {
                    System.out.println("Не удалось вставить значение в поле: " + e.getMessage());
                }
            }
        }
    }
}
