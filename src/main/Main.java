public class Main {

    public static void main(String[] args) {

        Student student = new Student(
                1,
                "Anna",
                "anna@student.de",
                "123456"
        );

        new StudentDashboard(student);
    }
}