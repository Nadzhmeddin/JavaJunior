package homeworks.third_homework.model;

public class Person {
    protected long id;
    protected String name;
    protected int age;
    protected boolean active;
    protected long department_id;

    public Person(long id, String name, int age, boolean active, long department_id) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.active = active;
        this.department_id = department_id;
    }

    public Person(String name, int age, boolean active, long department_id) {
        this.name = name;
        this.age = age;
        this.active = active;
        this.department_id = department_id;
    }

    public Person() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public long getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(long department_id) {
        this.department_id = department_id;
    }

    @Override
    public String toString() {
        return "Person: " +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", active=" + active +
                ", department_id=" + department_id +
                '\n';
    }
}
