package homeworks.second_homework;

import homeworks.second_homework.lib.ObjectCreator;

public class HomeworkMain {
    /**
     * В существующий класс ObjectCreatOr добавить поддержку аннотации RandomDate (по аналогии с Random):
     * 1. Аннотация должна обрабатываться только над полями типа java.util.Date
     * 2. Проверить, что min < max
     * 3. В поле, помеченной аннотацией, нужно вставить рандомную дату,
     * UNIX-время которой находится в диапазоне [min, max)
     *
     * 4. *** Добавить поддержку для типов Instant
     * 5. *** Добавить атрибут Zone и поддержку для типов LocalDate, LocalDateTime
     *
     * Примечание:
     * Unix-время - количество миллисекунд, прошедших с 1 января 1970 по UTC-0.
     */


    public static void main(String[] args) {

        MyCalendar randomDate = ObjectCreator.creatorObject(MyCalendar.class);

        System.out.println("firstDate: " + randomDate.getFirstDate());


    }
}
