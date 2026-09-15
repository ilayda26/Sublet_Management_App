import java.util.ArrayList;
import java.util.List;

public class DataStore {

    public static List<User> users =
            new ArrayList<>();

    public static List<Listing> listings =
            new ArrayList<>();

    public static List<Application> applications =
            new ArrayList<>();

    public static List<Report> reports =
            new ArrayList<>();

    private static int nextUserId = 1;
    private static int nextListingId = 1;
    private static int nextApplicationId = 1;
    private static int nextReportId = 1;

    public static int getNextUserId() {
        return nextUserId++;
    }

    public static int getNextListingId() {
        return nextListingId++;
    }

    public static int getNextApplicationId() {
        return nextApplicationId++;
    }

    public static int getNextReportId() {
        return nextReportId++;
    }

    public static boolean emailExists(
            String email) {

        for (User user : users) {

            if (user.getEmail()
                    .equalsIgnoreCase(email)) {

                return true;
            }
        }

        return false;
    }

    public static User findUser(
            String email,
            String password) {

        for (User user : users) {

            if (user.getEmail()
                    .equalsIgnoreCase(email)

                    && user.getPassword()
                    .equals(password)) {

                return user;
            }
        }

        return null;
    }

    public static User findUserById(
            int userId) {

        for (User user : users) {

            if (user.getUserId()
                    == userId) {

                return user;
            }
        }

        return null;
    }

    public static Listing findListingById(
            int listingId) {

        for (Listing listing : listings) {

            if (listing.getListingId()
                    == listingId) {

                return listing;
            }
        }

        return null;
    }

    public static void ensureAdmin() {

        String adminEmail =
                "admin@berlinsublet.de";

        for (User user : users) {

            if (user.getEmail()
                    .equalsIgnoreCase(
                            adminEmail
                    )) {

                return;
            }
        }

        users.add(
                new Admin(
                        1000,
                        "Administrator",
                        adminEmail,
                        "admin123"
                )
        );
    }

    public static void recalculateIds() {

        int highestUserId = 0;
        int highestListingId = 0;
        int highestApplicationId = 0;
        int highestReportId = 0;

        for (User user : users) {

            // Admin ID should not affect normal user IDs
            if (!(user instanceof Admin)
                    && user.getUserId()
                    > highestUserId) {

                highestUserId =
                        user.getUserId();
            }
        }

        for (Listing listing : listings) {

            if (listing.getListingId()
                    > highestListingId) {

                highestListingId =
                        listing.getListingId();
            }
        }

        for (Application application :
                applications) {

            if (application
                    .getApplicationId()
                    > highestApplicationId) {

                highestApplicationId =
                        application
                                .getApplicationId();
            }
        }

        for (Report report : reports) {

            if (report.getReportId()
                    > highestReportId) {

                highestReportId =
                        report.getReportId();
            }
        }

        nextUserId =
                highestUserId + 1;

        nextListingId =
                highestListingId + 1;

        nextApplicationId =
                highestApplicationId + 1;

        nextReportId =
                highestReportId + 1;
    }
}