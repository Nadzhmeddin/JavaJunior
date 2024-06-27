package homeworks.homework_1;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Homework {
    public static void main(String[] args) {

        PersonRepo personRepo = new PersonRepo();


//        System.out.println(findYoungestPerson(personRepo.persons));
//        System.out.println(findMostExpensiveDepartment(personRepo.persons));
//        System.out.println(groupByDepartment(personRepo.persons));
//        System.out.println(groupByDepartmentName(personRepo.persons));
//        System.out.println(getDepartmentOldestPerson(personRepo.persons));


    }

    /**
     * Найти самого молодого сотрудника
     */
    static Optional<Person> findYoungestPerson(List<Person> persons) {

        return persons.stream()
                .sorted(Comparator.comparing(p -> p.getAge()))
                .findFirst();
    }

    /**
     * Найти департамент, в котором работает сотрудник с самой большой зарплатой
     */
    static Optional<Department> findMostExpensiveDepartment(List<Person> persons) {

        return persons.stream()
                .sorted(Comparator.comparing(p -> p.getSalary()))
                .map(p -> p.getDepartment())
                .findFirst();
    }

    /**
     * Сгруппировать сотрудников по департаментам
     */
    static Map<Department, List<Person>> groupByDepartment(List<Person> people) {

        return people.stream()
                .collect(Collectors.groupingBy(p -> p.getDepartment()));
    }

    /**
     * Сгруппировать сотрудников по названиям департаментов
     */
    static Map<String, List<Person>> groupByDepartmentName(List<Person> people) {
        return people.stream()
                .collect(Collectors.groupingBy(p -> p.getDepartment().getName()));
    }

    /**
     * В каждом департаменте найти самого старшего сотрудника
     */
    static Map<String, Person> getDepartmentOldestPerson(List<Person> people) {

        return people.stream()
                .collect(Collectors.toMap(
                        b -> b.getDepartment().getName(),
                        b -> b,
                        (a,b) -> {
                            if(a.getAge() > b.getAge()){
                                return a;
                            }
                            return b;
                        }
                ));
    }
}
