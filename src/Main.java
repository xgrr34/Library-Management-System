import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        // Establishing the connection using the getConnection() method from the db class
        PreparedStatement preparedStatement = null;
        db.getConnection();

        try {
            // Creating a PreparedStatement object for insert query
            String insertSql = "INSERT INTO book(book_id, title, author, genre, status) VALUES (?, ?, ?, ?, ?)";
            preparedStatement = db.connection.prepareStatement(insertSql);
            preparedStatement.setInt(1, 1); // book_id
            preparedStatement.setString(2, "weathering with you"); // title
            preparedStatement.setString(3, "Example Author2"); // author
            preparedStatement.setString(4, "romance");
            preparedStatement.setBoolean(5, true);
            preparedStatement.executeUpdate();//this is necessary if you want to implement the insertion

            // Executing the insert query
            int rowsInserted = preparedStatement.executeUpdate();
            System.out.println("Rows inserted: " + rowsInserted);

            // Executing a select query
            String selectSql = "SELECT * FROM book";
            ResultSet result = db.statement.executeQuery(selectSql);
            while (result.next()) {
                System.out.println("book id : " + result.getInt("book_id"));
                System.out.println("title : " + result.getString("title"));
                System.out.println("genre : " + result.getString("genre"));
                System.out.println("availability : " + result.getBoolean("status"));
                System.out.println("author : " + result.getString("author"));
            }
        } catch (SQLException e) {
            System.err.println("Error executing SQL query: " + e.getMessage());
        } finally {
            // Closing the PreparedStatement and connection
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                System.err.println("Error closing PreparedStatement: " + e.getMessage());
            }
            db.close();
        }
    }
}