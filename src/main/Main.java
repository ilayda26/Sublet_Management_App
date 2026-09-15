public class Main {

    public static void main(String[] args) {

        Lister lister = new Lister(
                2,
                "Daniel",
                "daniel@sublet.de",
                "123456"
        );

        new ListerDashboard(lister);
    }
}