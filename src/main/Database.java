import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

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

    //ads a new listing to database
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
