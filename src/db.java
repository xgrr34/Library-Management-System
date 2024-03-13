import java.sql.*;

public class db {

        public static Connection connection = null;
        public static Statement statement;
        public static void getConnection() {
            try {
                // Establishing a connection to the database
                String url = "jdbc:sqlite:/C:\\Users\\Kiran\\Downloads\\oop assignment/database1.db"; // Replace with the path to your SQLite database file
                connection = DriverManager.getConnection(url);
                statement = connection.createStatement();
                System.out.println("the connection was successful");
            } catch (SQLException e) {
                System.err.println("Error connecting to SQLite database: " + e.getMessage());
            }
        }
        public static void close() {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("Error connecting to SQLite database: " + e.getMessage());
            }
        }
}

