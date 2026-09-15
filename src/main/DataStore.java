import java.util.ArrayList;
import java.util.List;

public class DataStore {

    // Temporary shared data until database integration
    public static List<Listing> listings = new ArrayList<>();
    public static List<Application> applications = new ArrayList<>();
    public static List<Report> reports = new ArrayList<>();

    private static int nextListingId = 1;
    private static int nextApplicationId = 1;
    private static int nextReportId = 1;

    public static int getNextListingId() {
        return nextListingId++;
    }

    public static int getNextApplicationId() {
        return nextApplicationId++;
    }

    public static int getNextReportId() {
        return nextReportId++;
    }
}
