import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Database {

    //SQlite database file used by application
    private static final String URL = "jdbc:sqlite:sublet.db";

    //Creates and returns a connection to the SQlite database
    public static Connection connect() throws SQLException{
        
        return DriverManager.getConnection(URL);
    }

    //Creates the listings table if it doesn't already exist
    public static void createListingsTable(){

        String sql = """
                CREATE TABLE IF NOT EXISTS listings(
                listing_id INTEGER PRIMARY KEY AUTOINCREMENT,
                title TEXT NOT NULL,
                location TEXT NOT NULL,
                price REAL NOT NULL,
                description TEXT,
                start_date TEXT NOT NULL,
                end_date TEXT NOT NULL,
                status TEXT NOT NULL,
                created_at TEXT NOT NULL
            )
            """;

        try(Connection connection = connect();
            Statement statement = connection.createStatement()){

            statement.execute(sql);
            System.out.println("Listings table ready.");

        } catch(SQLException e){
            System.out.println("Could not create listing table." + e.getMessage());
        }
    }

    //Creates the reports table if it doesn't already exists
    public static void createReportsTable(){
        String sql = """
                CREATE TABLE IF NOT EXISTS reports(
                report_id INTEGER PRIMARY KEY AUTOINCREMENT,
                reason TEXT NOT NULL,
                status TEXT NOT NULL,
                date TEXT NOT NULL
            )
            """;

        try(Connection connection = connect();
            Statement statement = connection.createStatement()){

            statement.execute(sql);
            System.out.println("Reports table ready.");

        }catch(SQLException e){
            System.out.println(
                "Could not create reports table." + e.getMessage()
            );
        }
    }

    //Adds a new listing to database
    public static void addListing(Listing listing){

        String sql = """
                INSERT INTO listings
                (title, location, price, description, start_date, end_date, status, created_at)
                VALUES(?, ?, ?, ?, ?, ?, ?, ?)
                """;

        try (Connection connection = connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {

                statement.setString(1, listing.getTitle());
                statement.setString(2, listing.getLocation());
                statement.setBigDecimal(3, listing.getPrice());
                statement.setString(4, listing.getDescription());
                statement.setString(5, listing.getStartDate().toString());
                statement.setString(6, listing.getEndDate().toString());
                statement.setString(7, listing.getStatus());
                statement.setString(8, listing.getCreatedAt().toString());

                statement.executeUpdate();

                System.out.println("Listing added successfully.");

             }catch (SQLException e){
                System.out.println("Could not add listing" + e.getMessage());
             }
    }

    //Returns all listings storted in the database
    public static List<Listing> getAllListings(){
        List<Listing> listings = new ArrayList<>();

        String sql = "SELECT* FROM listings";

        try (Connection connection = connect();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)){

            while (resultSet.next()){

                Listing listing = new Listing(
                    resultSet.getInt("listing_id"),
                    resultSet.getString("title"),
                    resultSet.getString("location"),
                    resultSet.getBigDecimal("price"),
                    resultSet.getString("description"),
                    java.time.LocalDate.parse(resultSet.getString("start_date")),
                    java.time.LocalDate.parse(resultSet.getString("end_date")),
                    resultSet.getString("status"),
                    java.time.LocalDateTime.parse(resultSet.getString("created_at"))
        
                );

                listings.add(listing);
            }

        }catch (SQLException e) {
            System.out.println("Could not retireve listings:" + e.getMessage());
        }

        return listings;
        
    }

    // Updates an existing listing in the database
    public static void updateListing(Listing listing) {

        String sql = """
                UPDATE listings
                SET title = ?,
                    location = ?,
                    price = ?,
                    description = ?,
                    start_date = ?,
                    end_date = ?,
                    status = ?,
                    created_at = ?
                WHERE listing_id = ?
                """;

        try (Connection connection = connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, listing.getTitle());
            statement.setString(2, listing.getLocation());
            statement.setBigDecimal(3, listing.getPrice());
            statement.setString(4, listing.getDescription());
            statement.setString(5, listing.getStartDate().toString());
            statement.setString(6, listing.getEndDate().toString());
            statement.setString(7, listing.getStatus());
            statement.setString(8, listing.getCreatedAt().toString());
            statement.setInt(9, listing.getlistingId());

            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Listing updated successfully.");
            } else {
                System.out.println("Listing not found.");
            }

        } catch (SQLException e) {
            System.out.println("Could not update listing: " + e.getMessage());
        }
    }

    //Deletes a listing from the database
    public static void deleteListing(int listingId) {

        String sql = "DELETE FROM listings WHERE listing_id = ?";

        try(Connection connection = connect();
             PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setInt(1, listingId);

            int rowsDeleted = statement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Listing deleted successfully.");
            } else {
                System.out.println("Listing not found.");
            }

        }catch (SQLException e) {
            System.out.println("Could not delete listing:" + e.getMessage());
        }
    }

    //Adds a new report for the database
    public static void addReport(Report report) {

        String sql = """
                INSERT INTO reports
                (reason, status, date)
                VALUES(?, ?, ?)
                """;

        try(Connection connection = connect();
             PreparedStatement statement = connection.prepareStatement(sql)){
            
            statement.setString(1, report.getReason());
            statement.setString(2, report.getStatus());
            statement.setString(3, report.getDate().toString());

            statement.executeUpdate();

            System.out.println("Report added successfully.");

        } catch (SQLException e) {
        System.out.println("Could not add report: " + e.getMessage());
        }
        
    }

    //Returns all reports stored in the database
    public static List<Report> getAllReports() {
        
        List<Report> reports = new ArrayList<>();
        String sql = "SELECT * FROM reports";

        try (Connection connection = connect();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
       
            while (resultSet.next()) {

                Report report = new Report(
                    resultSet.getInt("report_id"),
                    resultSet.getString("reason"),
                    resultSet.getString("status"),
                    java.time.LocalDateTime.parse(
                        resultSet.getString("date")
                    )

                );

                reports.add(report);

            }
        
        
        }catch (SQLException e) {
        System.out.println("Could not retrieve reports: " + e.getMessage());
        }

        return reports;
        
    }

    // Updates an existing report in the database
    public static void updateReport(Report report) {

        String sql = """
                UPDATE reports
                SET reason = ?,
                    status = ?,
                    date = ?
                WHERE report_id = ?
                """;

        try(Connection connection = connect();
             PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setString(1, report.getReason());
            statement.setString(2, report.getStatus());
            statement.setString(3, report.getDate().toString());
            statement.setInt(4, report.getReportId());
             
            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
            System.out.println("Report updated successfully.");
            } else {
            System.out.println("Report not found.");
            }

        }catch(SQLException e) {
        System.out.println("Could not update report: " + e.getMessage());
        }
    }

    //Deletes a report from the database
    public static void deleteReport(int reportId) {
        String sql = "DELETE FROM reports WHERE report_id = ?";

        try(Connection connection = connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, reportId);

            int rowsDeleted = statement.executeUpdate();

            if (rowsDeleted > 0){
                System.out.println("Report deleted successfully.");
            }else {
                System.out.println("Report not found");
            }
        
        }catch(SQLException e) {
            System.out.println("Could not delete report: " + e.getMessage());
        }
    }

    // Tests if the application can connect to the database
    public static void main(String[] args) {
        try (Connection connection = connect()) {
            System.out.println("Database connected successfully.");
        } catch (SQLException e) {
            System.out.println("Database connection failed: " + e.getMessage());
        }

        createListingsTable();
        createReportsTable();  
        
    }

}
