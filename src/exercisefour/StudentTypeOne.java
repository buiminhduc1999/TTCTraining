package exercisefour;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class StudentTypeOne extends Student {
    LocalDate birthday;
    String gender;

    public StudentTypeOne(String id, String name, LocalDate birthday, String gender) {
        super(id, name);
        this.birthday = birthday;
        this.gender = gender;
    }

    public StudentTypeOne() {
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    @Override
    public String toString() {
        return "StudenTypeOne{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                ", gender=" + gender +
                '}';
    }
}
