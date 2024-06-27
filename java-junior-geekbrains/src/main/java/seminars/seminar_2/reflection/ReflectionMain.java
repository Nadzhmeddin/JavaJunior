package seminars.seminar_2.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectionMain {
    public static void main(String[] args) throws Exception {


        Person unnamed = new Person();
        Person person = new Person("personName");

        System.out.println(unnamed);
        System.out.println(person);


        Class<Person> personClass = Person.class;

        // Вызов конструкторов
        Constructor<Person> constructor = personClass.getConstructor(String.class);
        Person person2 = constructor.newInstance("via reflect");

        System.out.println(person2);


        // Вызов методов

        Method getName = Person.class.getMethod("getName");


//        System.out.println(person.getName()); // через обычный getName(), определенный в Person
//        System.out.println(getName.invoke(unnamed)); // через рефлекcию, создание метода getName с методом invoke, куда передается откуда брать имя.
//        System.out.println(getName.invoke(person2));

        Method setName = Person.class.getMethod("setAge", int.class); // создание метода setAge, здесь передается название метода и тип, принимаемый методом.
        setName.invoke(person2, 20);
        System.out.println(person2);

        System.out.println("Counter: " + Person.getCounter());
        // Создание статического поля

        Method getCounter = Person.class.getMethod("getCounter");
        System.out.println("Counter: " + getCounter.invoke(null));


        // Создание полей через рефлексию

        Field name = Person.class.getDeclaredField("name");

        System.out.println(name.get(person2));


    }
}
