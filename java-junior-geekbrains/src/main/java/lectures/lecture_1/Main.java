package lectures.lecture_1;

import java.util.Arrays;
import java.util.List;

// Лекция 1: Лямбды и Stream API
public class Main {
    public static void main(String[] args) {

//        PlainInterface plainInterface = new PlainInterface() {
//            @Override
//            public String action(int x, int y) {
//                return String.valueOf(x+y);
//            }
//        };

//        PlainInterface plainInterface = Integer::sum;
//
//        PlainInterface plainInterface1 = Integer::compare;
//
//        System.out.println(plainInterface.action(5, 3));
//        System.out.println(plainInterface1.action(1,5));

//        List<String> list = Arrays.asList("Привет", "мир", "я", "дома", "дача", "Наджмеддин", "Доргах");
//
//        list.stream()
//                .filter(s -> s.length() > 4)
//                .filter(s -> s.contains("о"))
//                .sorted()
//                .forEach(System.out::println);
//
//
//        List<Integer> numbers= Arrays.asList(1,10,8,2,5,6,7,8,9);
//
//        numbers.stream()
//                .sorted()
//                .distinct()
//                .forEach(System.out::println);



        List<User> users = Arrays.asList(
                new User("Mark", 30),
                new User("Steve", 20),
                new User("Vladimir", 45),
                new User("Gorni", 50)
        );

        users.stream()
                .filter(u -> u.age < 40)
                .filter(u -> u.name.contains("a"))
                .forEach(System.out::println);
    }
}
