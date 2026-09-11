import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

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
