package seminars.seminar_2.annotation;

import seminars.seminar_2.annotation.lib.ObjectCreator;

public class AnnotationsMain {
    public static void main(String[] args) {

        // Override, FunctionalInterface, Data, ...

        Person rndPerson = ObjectCreator.createObj(Person.class);
        System.out.println("age1 = " + rndPerson.age1);
        System.out.println("age2 = " + rndPerson.age2);




//        Object s = "1234";
//
//        if(s instanceof String) {
//            System.out.println("String!");
//        }
//        if(String.class.isInstance(s)) {
//            System.out.println("String!");
//        }
//
//        if(s.getClass().isAssignableFrom(String.class)) {
//            System.out.println("String");
//        }



    }
}
