package exercisefour;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<StudentTypeOne> listFist = Arrays.asList(
                new StudentTypeOne("SV01", "Nguyen Quoc Anh", LocalDate.of(1999, 4, 19), "Male"),
                new StudentTypeOne("SV02", "Tran Duc Dao", LocalDate.of(1997, 1, 1), "Female"),
                new StudentTypeOne("SV03", "Bui Minh Duc", LocalDate.of(1999, 4, 20), "Male")

        );
        System.out.print("Enter gender: ");
        Scanner scanner = new Scanner(System.in);
        String gender = scanner.nextLine();

        listFist.stream()
                .filter(h1 -> h1.getGender().equals(gender))
                .map(studentTypeOne -> new StudentTypeTwo(studentTypeOne.getId(), studentTypeOne.getName(), StudentTypeTwo.tinhTuoi(studentTypeOne.getBirthday())))
                .sorted(Comparator.comparing(StudentTypeTwo::getAge).reversed().thenComparing(Student::getId))
                .peek(e -> System.out.println(e))
                .collect(Collectors.toList());
    }
}
