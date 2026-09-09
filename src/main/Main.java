public class Main{
    public static void main ( String[] args) {

        Student student = new Student(
            1, "Anna","anna@student.de", "123445"

        );

        Lister lister = new Lister(
            2,"Daniel", "daniel@student.de", "123456"
        );

        Admin admin = new Admin(
            3,"Admin", "admin@berlinsublet.de","1234admin"

        );

        System.out.println(student.getName()+ "-"+ student.getRole());
        System.out.println(lister.getName()+ "-"+ lister.getRole());
        System.out.println(admin.getName()+ "-"+ admin.getRole());





    }
} 