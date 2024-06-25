package seminars.second_seminar.reflection;

public class Person {
    private final String name;
    private int age;

    private static long counter = 0;

    public Person() {
        this("unnamed");
    }

    public Person(String name) {
        this.name = name;
        counter++;
    }

    public String getName() {
        return name;
    }

    public static long getCounter() {
        return counter;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
