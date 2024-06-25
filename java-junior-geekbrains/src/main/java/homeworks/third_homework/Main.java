package homeworks.third_homework;

import homeworks.third_homework.dao.DepartmentCrud;
import homeworks.third_homework.dao.PersonCrud;

public class Main {
    public static void main(String[] args) {

        // Создание таблиц
        DepartmentCrud.createDepartmentTable();
        PersonCrud.createPersonTable();

        // Вставка данных в таблицу Department
//        DepartmentCrud.insertDepartment(new Department("Software Engineer"));
//        DepartmentCrud.insertDepartment(new Department("Tester"));
//        DepartmentCrud.insertDepartment(new Department("HR"));

        // Вставка данных в таблицу Person
//        PersonCrud.insertPerson(new Person("Mike", 29, true, 1));
//        PersonCrud.insertPerson(new Person("Kate", 29, true, 3));
//        PersonCrud.insertPerson(new Person("Evgenii", 28, false, 2));
//        PersonCrud.insertPerson(new Person("Artem", 36, true, 2));
//        PersonCrud.insertPerson(new Person("Iqor", 27, false, 3));
//        PersonCrud.insertPerson(new Person("Egor", 34, true, 1));
//        PersonCrud.insertPerson(new Person("Yana", 22, false, 3));

        // Получения наименования департамента по id Person
//        System.out.println(PersonCrud.getPersonDepartmentName(3));

//        System.out.println(PersonCrud.getPersonDepartments());


        System.out.println(PersonCrud.getDepartmentPersons());


    }
}
