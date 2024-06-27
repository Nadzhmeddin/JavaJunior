package homeworks.homework_1;

import java.util.Arrays;
import java.util.List;

public class PersonRepo {

    List<Person> persons;

    public PersonRepo() {
        this.persons = Arrays.asList(
                new Person("Mike", 22, 250.0, new Department("Tester")),
                new Person("Alex", 42, 300.0, new Department("Project Manager")),
                new Person("Lola", 38, 400.0, new Department("Team Leader")),
                new Person("Kate", 24, 150.0, new Department("Tester")),
                new Person("Iqor", 26, 500.0, new Department("Senior Engineer")),
                new Person("Vladimir", 38, 610.5, new Department("Project Manager")),
                new Person("Gorni", 56, 490, new Department("Tester")),
                new Person("Daniil", 21, 120.0, new Department("Team Leader")),
                new Person("Chloe", 38, 1000, new Department("Team Leader")),
                new Person("Artem", 40, 440, new Department("Project Manager")),
                new Person("Nikita", 56, 700, new Department("Senior Engineer")),
                new Person("Yurii", 62, 490, new Department("Team Leader")),
                new Person("Diana", 29, 370.5, new Department("Senior Engineer")),
                new Person("Clark", 32, 410, new Department("Project Manager")),
                new Person("Bruce", 49, 500.5, new Department("Team Leader")),
                new Person("Ivan", 19, 150, new Department("Senior Engineer")),
                new Person("Elena", 20, 185.5, new Department("Tester")),
                new Person("Arthur", 31, 300.0, new Department("Project Manager")),
                new Person("Ronan", 42, 330.4, new Department("Senior Engineer")),
                new Person("Max", 50, 450.5, new Department("Tester"))
        );
    }
}
