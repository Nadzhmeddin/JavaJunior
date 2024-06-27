package seminars.seminar_1;

import java.util.List;

public class Stream {
    public static void main(String[] args) {


//        List<String> stringList = new ArrayList<>(List.of("java", "c#", "cotlin", "go", "ruby", "assembler", "pascal", "rust", "javascript", "php", "delphi"));
//
//        printLangsWithhLengths(stringList);

//        int result = List.of(1,2,3,4,5,6,7,8,9,10).stream()
//                .reduce(0, (a,b) -> a + b);
//        System.out.println(result);

    }

    static void printLangsWithhLengths(List<String> stringList) {
//        for (String string : stringList) {
//            if(string.length() > 4) {
//                System.out.println(string);
//            }
//        }

        stringList.stream()
                .filter(s -> s.length() > 4)
                .map(s -> s.toUpperCase())
                .forEach(s -> System.out.println(s));

    }

}
