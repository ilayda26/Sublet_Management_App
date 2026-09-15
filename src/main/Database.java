import java.sql.*;
import java.time.LocalDate;

public class Database {

    private static final String URL =
            "jdbc:sqlite:sublet.db";

    public static void initialize() {

        try (Connection connection =
                     DriverManager.getConnection(URL);

             Statement statement =
                     connection.createStatement()) {

            statement.execute("""
                    CREATE TABLE IF NOT EXISTS users (
                        user_id INTEGER PRIMARY KEY,
                        name TEXT NOT NULL,
                        email TEXT NOT NULL UNIQUE,
                        password TEXT NOT NULL,
                        role TEXT NOT NULL
                    )
                    """);

            statement.execute("""
                    CREATE TABLE IF NOT EXISTS listings (
                        listing_id INTEGER PRIMARY KEY,
                        lister_id INTEGER NOT NULL,
                        title TEXT NOT NULL,
                        borough TEXT NOT NULL,
                        neighbourhood TEXT NOT NULL,
                        price REAL NOT NULL,
                        start_date TEXT NOT NULL,
                        end_date TEXT NOT NULL,
                        room_type TEXT NOT NULL,
                        description TEXT,
                        image_path TEXT
                    )
                    """);

            statement.execute("""
                    CREATE TABLE IF NOT EXISTS applications (
                        application_id INTEGER PRIMARY KEY,
                        student_id INTEGER NOT NULL,
                        listing_id INTEGER NOT NULL,
                        status TEXT NOT NULL
                    )
                    """);

            statement.execute("""
                    CREATE TABLE IF NOT EXISTS reports (
                        report_id INTEGER PRIMARY KEY,
                        student_id INTEGER NOT NULL,
                        listing_id INTEGER NOT NULL,
                        reason TEXT NOT NULL,
                        status TEXT NOT NULL
                    )
                    """);

            System.out.println(
                    "Database ready."
            );

        } catch (SQLException e) {

            System.out.println(
                    "Database error: "
                            + e.getMessage()
            );
        }
    }

    public static void loadData() {

        DataStore.users.clear();
        DataStore.listings.clear();
        DataStore.applications.clear();
        DataStore.reports.clear();

        loadUsers();
        loadListings();
        loadApplications();
        loadReports();

        // Makes sure there is always an admin account
        DataStore.ensureAdmin();

        // Prevent duplicate IDs after restart
        DataStore.recalculateIds();

        System.out.println(
                "Database data loaded."
        );
    }

    private static void loadUsers() {

        String sql =
                "SELECT * FROM users";

        try (Connection connection =
                     DriverManager.getConnection(URL);

             Statement statement =
                     connection.createStatement();

             ResultSet results =
                     statement.executeQuery(sql)) {

            while (results.next()) {

                int id =
                        results.getInt(
                                "user_id"
                        );

                String name =
                        results.getString(
                                "name"
                        );

                String email =
                        results.getString(
                                "email"
                        );

                String password =
                        results.getString(
                                "password"
                        );

                String role =
                        results.getString(
                                "role"
                        );

                User user;

                if ("Admin".equalsIgnoreCase(role)) {

                    user =
                            new Admin(
                                    id,
                                    name,
                                    email,
                                    password
                            );

                } else if ("Lister".equalsIgnoreCase(role)) {

                    user =
                            new Lister(
                                    id,
                                    name,
                                    email,
                                    password
                            );

                } else {

                    user =
                            new Student(
                                    id,
                                    name,
                                    email,
                                    password
                            );
                }

                DataStore.users.add(
                        user
                );
            }

        } catch (SQLException e) {

            System.out.println(
                    "Could not load users: "
                            + e.getMessage()
            );
        }
    }

    private static void loadListings() {

        String sql =
                "SELECT * FROM listings";

        try (Connection connection =
                     DriverManager.getConnection(URL);

             Statement statement =
                     connection.createStatement();

             ResultSet results =
                     statement.executeQuery(sql)) {

            while (results.next()) {

                Listing listing =
                        new Listing(
                                results.getInt(
                                        "listing_id"
                                ),

                                results.getInt(
                                        "lister_id"
                                ),

                                results.getString(
                                        "title"
                                ),

                                results.getString(
                                        "borough"
                                ),

                                results.getString(
                                        "neighbourhood"
                                ),

                                results.getDouble(
                                        "price"
                                ),

                                LocalDate.parse(
                                        results.getString(
                                                "start_date"
                                        )
                                ),

                                LocalDate.parse(
                                        results.getString(
                                                "end_date"
                                        )
                                ),

                                results.getString(
                                        "room_type"
                                ),

                                results.getString(
                                        "description"
                                )
                        );

                String imagePath =
                        results.getString(
                                "image_path"
                        );

                if (imagePath != null
                        && !imagePath.isEmpty()) {

                    listing.setImagePath(
                            imagePath
                    );
                }

                DataStore.listings.add(
                        listing
                );
            }

        } catch (SQLException e) {

            System.out.println(
                    "Could not load listings: "
                            + e.getMessage()
            );
        }
    }

    private static void loadApplications() {

        String sql =
                "SELECT * FROM applications";

        try (Connection connection =
                     DriverManager.getConnection(URL);

             Statement statement =
                     connection.createStatement();

             ResultSet results =
                     statement.executeQuery(sql)) {

            while (results.next()) {

                Application application =
                        new Application(
                                results.getInt(
                                        "application_id"
                                ),

                                results.getInt(
                                        "student_id"
                                ),

                                results.getInt(
                                        "listing_id"
                                )
                        );

                application.setStatus(
                        results.getString(
                                "status"
                        )
                );

                DataStore.applications.add(
                        application
                );
            }

        } catch (SQLException e) {

            System.out.println(
                    "Could not load applications: "
                            + e.getMessage()
            );
        }
    }

    private static void loadReports() {

        String sql =
                "SELECT * FROM reports";

        try (Connection connection =
                     DriverManager.getConnection(URL);

             Statement statement =
                     connection.createStatement();

             ResultSet results =
                     statement.executeQuery(sql)) {

            while (results.next()) {

                Report report =
                        new Report(
                                results.getInt(
                                        "report_id"
                                ),

                                results.getInt(
                                        "student_id"
                                ),

                                results.getInt(
                                        "listing_id"
                                ),

                                results.getString(
                                        "reason"
                                )
                        );

                report.setStatus(
                        results.getString(
                                "status"
                        )
                );

                DataStore.reports.add(
                        report
                );
            }

        } catch (SQLException e) {

            System.out.println(
                    "Could not load reports: "
                            + e.getMessage()
            );
        }
    }

    public static void saveAll() {

        try (Connection connection =
                     DriverManager.getConnection(URL)) {

            connection.setAutoCommit(
                    false
            );

            try {

                clearTables(
                        connection
                );

                saveUsers(
                        connection
                );

                saveListings(
                        connection
                );

                saveApplications(
                        connection
                );

                saveReports(
                        connection
                );

                connection.commit();

                System.out.println(
                        "Database saved."
                );

            } catch (SQLException e) {

                connection.rollback();

                System.out.println(
                        "Save failed: "
                                + e.getMessage()
                );
            }

        } catch (SQLException e) {

            System.out.println(
                    "Database connection error: "
                            + e.getMessage()
            );
        }
    }

    private static void clearTables(
            Connection connection)
            throws SQLException {

        try (Statement statement =
                     connection.createStatement()) {

            statement.executeUpdate(
                    "DELETE FROM applications"
            );

            statement.executeUpdate(
                    "DELETE FROM reports"
            );

            statement.executeUpdate(
                    "DELETE FROM listings"
            );

            statement.executeUpdate(
                    "DELETE FROM users"
            );
        }
    }

    private static void saveUsers(
            Connection connection)
            throws SQLException {

        String sql =
                """
                INSERT INTO users
                (user_id, name, email, password, role)
                VALUES (?, ?, ?, ?, ?)
                """;

        try (PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            for (User user :
                    DataStore.users) {

                statement.setInt(
                        1,
                        user.getUserId()
                );

                statement.setString(
                        2,
                        user.getName()
                );

                statement.setString(
                        3,
                        user.getEmail()
                );

                statement.setString(
                        4,
                        user.getPassword()
                );

                statement.setString(
                        5,
                        user.getRole()
                );

                statement.addBatch();
            }

            statement.executeBatch();
        }
    }

    private static void saveListings(
            Connection connection)
            throws SQLException {

        String sql =
                """
                INSERT INTO listings
                (
                    listing_id,
                    lister_id,
                    title,
                    borough,
                    neighbourhood,
                    price,
                    start_date,
                    end_date,
                    room_type,
                    description,
                    image_path
                )
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;

        try (PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            for (Listing listing :
                    DataStore.listings) {

                statement.setInt(
                        1,
                        listing.getListingId()
                );

                statement.setInt(
                        2,
                        listing.getListerId()
                );

                statement.setString(
                        3,
                        listing.getTitle()
                );

                statement.setString(
                        4,
                        listing.getBorough()
                );

                statement.setString(
                        5,
                        listing.getNeighbourhood()
                );

                statement.setDouble(
                        6,
                        listing.getPrice()
                );

                statement.setString(
                        7,
                        listing.getStartDate()
                                .toString()
                );

                statement.setString(
                        8,
                        listing.getEndDate()
                                .toString()
                );

                statement.setString(
                        9,
                        listing.getRoomType()
                );

                statement.setString(
                        10,
                        listing.getDescription()
                );

                statement.setString(
                        11,
                        listing.getImagePath()
                );

                statement.addBatch();
            }

            statement.executeBatch();
        }
    }

    private static void saveApplications(
            Connection connection)
            throws SQLException {

        String sql =
                """
                INSERT INTO applications
                (
                    application_id,
                    student_id,
                    listing_id,
                    status
                )
                VALUES (?, ?, ?, ?)
                """;

        try (PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            for (Application application :
                    DataStore.applications) {

                statement.setInt(
                        1,
                        application
                                .getApplicationId()
                );

                statement.setInt(
                        2,
                        application
                                .getStudentId()
                );

                statement.setInt(
                        3,
                        application
                                .getListingId()
                );

                statement.setString(
                        4,
                        application
                                .getStatus()
                );

                statement.addBatch();
            }

            statement.executeBatch();
        }
    }

    private static void saveReports(
            Connection connection)
            throws SQLException {

        String sql =
                """
                INSERT INTO reports
                (
                    report_id,
                    student_id,
                    listing_id,
                    reason,
                    status
                )
                VALUES (?, ?, ?, ?, ?)
                """;

        try (PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            for (Report report :
                    DataStore.reports) {

                statement.setInt(
                        1,
                        report.getReportId()
                );

                statement.setInt(
                        2,
                        report.getStudentId()
                );

                statement.setInt(
                        3,
                        report.getListingId()
                );

                statement.setString(
                        4,
                        report.getReason()
                );

                statement.setString(
                        5,
                        report.getStatus()
                );

                statement.addBatch();
            }

            statement.executeBatch();
        }
    }
}