package exercisefour;

import java.time.LocalDate;

public class StudentTypeTwo extends Student {
    int age;

    public StudentTypeTwo(String id, String name, int age) {
        super(id, name);
        this.age = age;
    }

    public StudentTypeTwo() {
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public static int tinhTuoi(LocalDate birthday) {
        LocalDate localDate = LocalDate.now();
        return localDate.getYear() - birthday.getYear();
    }

    @Override
    public String toString() {
        return "StudenTypeTwo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
