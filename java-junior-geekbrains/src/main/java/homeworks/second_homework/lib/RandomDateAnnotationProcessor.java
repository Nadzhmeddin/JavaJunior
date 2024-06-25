package homeworks.second_homework.lib;

import java.lang.reflect.Field;
import java.util.Date;

public class RandomDateAnnotationProcessor {
    public static void processAnnotation(Object object) {

        // Найти все поля, над которыми стоит аннотация @RandomDate
        // вставить туда рандомные даты в диапазоне от [min, max)

        java.util.Random random = new java.util.Random();
        Class<?> objectClass = object.getClass();

        for (Field field : objectClass.getDeclaredFields()) {
            if(field.isAnnotationPresent(RandomDate.class) && field.getType().isAssignableFrom(Date.class)) {
                RandomDate annotation = field.getAnnotation(RandomDate.class);
                long min = annotation.min();
                long max = annotation.max();
                try {
                    if(min < max) { // проверка, что минимальная дата меньше максимальной
                        Date newDate = new Date(random.nextLong(min,max)); // создание переменной дата, где генерируем дату от min до max
                        field.setAccessible(true);
                        field.set(object, newDate);
                    } else throw new IllegalAccessException("Начальная дата больше конечной даты!");
                } catch (IllegalAccessException e) {
                    System.out.println("Не удалось вставить значение в поле: " + e.getMessage());
                }

            }
        }
    }
}
