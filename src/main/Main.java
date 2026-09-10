public class Main {

    public static void main(String[] args) {

        Report report = new Report(
                1,
                1,
                10,
                "The listing looks suspicious"
        );

        System.out.println("Report ID: " + report.getReportId());
        System.out.println("Listing ID: " + report.getListingId());
        System.out.println("Reason: " + report.getReason());
        System.out.println("Status: " + report.getStatus());

        report.setStatus("Resolved");

        System.out.println("New status: " + report.getStatus());
    }
}