package seminars.seminar_1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Lambda {
    public static void main(String[] args) {

//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("Hello World");
//            }
//        };


        // () -> () Ничего не принимает, ничего не возвращает: Runnable
        Runnable runnable = () -> System.out.println("hello");
        runnable.run();


        // (t) -> () Принимает аргумент, ничего не возвращает: Consumer(потребитель)
        Consumer<String> stringPrinter = s -> System.out.println(s);


//        Consumer<String> stringPrinter = new Consumer<String>() {
//            @Override
//            public void accept(String s) {
//                System.out.println(s);
//            }
//        };

        // () -> R Ничего не принимает, но что-то возвращает: Supplier(поставщик)
        // в данном примере возвращает рандомное число от 0 до 10(не включительно)

        Supplier<Integer> randIntProvider = () -> ThreadLocalRandom.current().nextInt(0, 10);


        System.out.println(randIntProvider.get());
        System.out.println(randIntProvider.get());
        System.out.println(randIntProvider.get());


        // (t) -> R  - Function принимает объекты одного типа и возвращает объекты другого типа

        Function<String, Integer> stringLengthFunction = String::length;

        System.out.println(stringLengthFunction.apply("Hello"));


        // Predicate - возвращает всегда boolean значение
        Predicate<Integer> isEvenPredicate = x -> x % 2 == 0;

        System.out.println(isEvenPredicate.test(3)); // false
        System.out.println(isEvenPredicate.test(2)); // true
        System.out.println(isEvenPredicate.test(0)); // true


        // Компаратор - интерфейс, задающий порядок


        List<String> stringList = new ArrayList<>(List.of("java", "c#", "cotlin", "go", "ruby"));

        Collections.sort(stringList, (a, b) -> a.length() -b.length());

    }
}
