import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class library {
    ////////////////////////////////////////////////////////////////////////////
    PreparedStatement preparedStatement = null;
    ResultSet result = null;
    ArrayList<book> Book_list = new ArrayList<>();
    ArrayList<user>user_list = new ArrayList<>();
    Scanner input = new Scanner(System.in);
    library(){

    }
    ///////////////////////////////////////////////////////////////////////////




    ///////////////////////////////////////////////////////////////////////////

    ///////////////////////////////////////////////////////////////////////////
    void load_book(){
        try {
            String query = "SELECT * FROM book";
            preparedStatement = db.connection.prepareStatement(query);
            result = preparedStatement.executeQuery();

            // Create an ArrayList to store the data


            // Iterate over the ResultSet and store the values in the ArrayList
            while (result.next()) {
                String title = result.getString("title");
                String genre = result.getString("genre");
                String author = result.getString("author");
                boolean status = result.getBoolean("status");
                int id = result.getInt("book_id");
                Book_list.add(new book(id, title, genre, author, status));
            }

        } catch (SQLException e) {
            System.err.println("Error executing SQL query: " + e.getMessage());
        }

    }
    void load_user(){
        try {
            String query = "SELECT * FROM user";
            preparedStatement = db.connection.prepareStatement(query);
            result = preparedStatement.executeQuery();

            // Create an ArrayList to store the data


            // Iterate over the ResultSet and store the values in the ArrayList
            while (result.next()) {
                String name = result.getString("name");
                String contact = result.getString("contact");
                int borrowed = result.getInt("borrowed");
                int user_id = result.getInt("user_id");

                user_list.add(new user(user_id, name, contact,borrowed));
            }

        } catch (SQLException e) {
            System.err.println("Error executing SQL query: " + e.getMessage());
        }
    }


    void insert_book(String title1,String author1,String genre1,Boolean status1) {

        ////////////////////////////decleration///////////////////////////////////////////////

        Scanner input = new Scanner(System.in);
        PreparedStatement preparedStatement = null;
        /////////////////////////////////////////////////////////////////////////////////////


        ////////////////////////////////getting input ///////////////////////////////////////
        while (true) {
            try {

                try {
                    String insertSql = "INSERT INTO book(book_id, title, author, genre, status) VALUES (?, ?, ?, ?, ?)";
                    preparedStatement = db.connection.prepareStatement(insertSql);
//                        preparedStatement.setInt(1, all_book_id); // book_id
                    preparedStatement.setString(2, title1); // title
                    preparedStatement.setString(3, author1); // author
                    preparedStatement.setString(4, genre1);
                    preparedStatement.setBoolean(5, status1);

                    // Executing the insert query
                    int rowsInserted = preparedStatement.executeUpdate();
                    ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                    if (generatedKeys.next()) {

                        //The getGeneratedKeys() method is a JDBC method used to retrieve
                        // auto-generated keys after executing an SQL INSERT statement.
                        // When you insert a row into a table that has an auto-increment
                        // primary key column (or any column with a default value generated
                        // by the database), the database automatically generates a value for
                        // that column. The getGeneratedKeys() method allows you to retrieve
                        // these auto-generated key values from the database.

                        int lastInsertedId = generatedKeys.getInt(1);
                        System.out.println("Book inserted with ID: " + lastInsertedId);
                        load_book();


                    }
                    System.out.println("Rows inserted: " + rowsInserted);


                } catch (SQLException f) {
                    System.err.println("Error executing SQL query: " + f.getMessage());


                }
            } catch (Exception e) {
                System.out.println("re enter the value ");
            }
            try {
                break;

            } catch (Exception h) {
                System.out.println("Exiting on wrong Input");
            }


        }

    }

    void insert_user(String contact,String name) {

        int borrowed = 0;

        Scanner input = new Scanner(System.in);
        PreparedStatement preparedStatement = null;
        /////////////////////////////////////////////////////////////////////////////////////


        ////////////////////////////////getting input ///////////////////////////////////////
        while (true) {
            try {
                try {
                    String insertSql = "INSERT INTO user(name, contact, borrowed) VALUES (?, ?, ?)";
                    preparedStatement = db.connection.prepareStatement(insertSql);
                    preparedStatement.setString(1, name); // title
                    preparedStatement.setString(2, contact); // author
                    preparedStatement.setInt(3, borrowed);


                    // Executing the insert query
                    int rowsInserted = preparedStatement.executeUpdate();
                    ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                    if (generatedKeys.next()) {

                        //The getGeneratedKeys() method is a JDBC method used to retrieve
                        // auto-generated keys after executing an SQL INSERT statement.
                        // When you insert a row into a table that has an auto-increment
                        // primary key column (or any column with a default value generated
                        // by the database), the database automatically generates a value for
                        // that column. The getGeneratedKeys() method allows you to retrieve
                        // these auto-generated key values from the database.

                        int lastInsertedId = generatedKeys.getInt(1);
                        System.out.println("user inserted with ID: " + lastInsertedId);
                        load_book();//////////////////////////////////////////////////////////////////////////
                    }
                    System.out.println("Rows inserted: " + rowsInserted);

                } catch (SQLException f) {
                    System.err.println("Error executing SQL query: " + f.getMessage());


                }
            } catch (Exception e) {
                System.out.println("re enter the value ");
                input.next();
            }
            try {
                break;


            } catch (Exception h) {
                System.out.println("Exiting on wrong Input");
            }


        }

    }

    void view_book(int Id) {
        ///////////////////////////////////////////////////////////////////////////////


        while (true) {
            try {

                // Fetch the latest data from the database
                String query = "SELECT * FROM book WHERE book_id = ?";
                preparedStatement = db.connection.prepareStatement(query);
                preparedStatement.setInt(1, Id);
                result = preparedStatement.executeQuery();

                // Check if a record with the given book_id exists
                if (result.next()) {
                    System.out.println("Book Id :" + result.getInt("book_id"));
                    System.out.println("Title : " + result.getString("title"));
                    System.out.println("Author : " + result.getString("author"));
                    System.out.println("Genre : " + result.getString("genre"));
                    System.out.println("Availability status : " + result.getBoolean("status"));
                } else {
                    System.out.println("Book with id " + Id + " not found!");
                }
                break;
            } catch (Exception e) {
                System.out.println("Wrong input !");
            }
        }
    }
    void view_user (int Id) {

        while (true) {
            try {

                // Fetch the latest data from the database
                String query = "SELECT * FROM user WHERE user_id = ?";
                preparedStatement = db.connection.prepareStatement(query);
                preparedStatement.setInt(1, Id);
                result = preparedStatement.executeQuery();

                // Check if a record with the given book_id exists
                if (result.next()) {
                    System.out.println("user Id :" + result.getInt("user_id"));
                    System.out.println("Name : " + result.getString("name"));
                    System.out.println("Contact : " + result.getString("contact"));
                    System.out.println("Books borrowed : " + result.getString("borrowed"));

                } else {
                    System.out.println("user with id " + Id + " not found!");
                }
                break;
            } catch (Exception e) {
                System.out.println("Wrong input !");
            }
        }
    }
    void borrow_book(int user_id,int b_id){

        try {
            String query = "SELECT status FROM book WHERE book_id = ?";
            preparedStatement = db.connection.prepareStatement(query);
            preparedStatement.setInt(1, b_id);
            result = preparedStatement.executeQuery();

            if (result.next()) {
                boolean status = result.getBoolean("status");
//                    System.out.println("Status of book with ID " + b_id + ": " + status);
                if(status){
                    String updateQuery = "UPDATE book SET status = FALSE WHERE book_id = ?";
                    preparedStatement = db.connection.prepareStatement(updateQuery);
                    preparedStatement.setInt(1, b_id);
                    int rowsUpdated = preparedStatement.executeUpdate();
                    if (rowsUpdated > 0) {
                        System.out.println("Status of book with ID " + b_id + " updated successfully.");

                        try {
                            // Check if the user with the specified ID exists
                            String selectQuery = "SELECT * FROM user WHERE user_id = ?";
                            preparedStatement = db.connection.prepareStatement(selectQuery);
                            preparedStatement.setInt(1, user_id);
                            result = preparedStatement.executeQuery();

                            if (result.next()) {
                                // If the user exists, update the borrow count by incrementing it by one
                                int currentBorrowCount = result.getInt("borrowed");
                                int newBorrowCount = currentBorrowCount + 1;

                                String updateQuery_1 = "UPDATE user SET borrowed = ? WHERE user_id = ?";
                                preparedStatement = db.connection.prepareStatement(updateQuery_1);
                                preparedStatement.setInt(1, newBorrowCount);
                                preparedStatement.setInt(2, user_id);

                                int rowsUpdated_1 = preparedStatement.executeUpdate();
                                if (rowsUpdated_1 > 0) {
                                    System.out.println("Borrow count of user with ID " + user_id + " updated successfully.");
                                } else {
                                    System.out.println("Failed to update borrow count of user with ID " + user_id);
                                }
                            } else {
                                System.out.println("User with ID " + user_id + " not found.");
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }

                        load_book();
                        load_book();
                    } else {
                        System.out.println("Failed to update status of book with ID " + b_id);
                    }

                }else{
                    System.out.println("Book is not available !!!");
                }
            } else {
                System.out.println("Book with ID " + b_id + " not found!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    void return_book(int user_id,int b_id){

        try {
            String query = "SELECT status FROM book WHERE book_id = ?";
            preparedStatement = db.connection.prepareStatement(query);
            preparedStatement.setInt(1, b_id);
            result = preparedStatement.executeQuery();

            if (result.next()) {
                boolean status = result.getBoolean("status");
//                    System.out.println("Status of book with ID " + b_id + ": " + status);
                if(!status){
                    String updateQuery = "UPDATE book SET status = TRUE WHERE book_id = ?";
                    preparedStatement = db.connection.prepareStatement(updateQuery);
                    preparedStatement.setInt(1, b_id);
                    int rowsUpdated = preparedStatement.executeUpdate();
                    if (rowsUpdated > 0) {
                        System.out.println("Status of book with ID " + b_id + " updated successfully.");

                        try {
                            // Check if the user with the specified ID exists
                            String selectQuery = "SELECT * FROM user WHERE user_id = ?";
                            preparedStatement = db.connection.prepareStatement(selectQuery);
                            preparedStatement.setInt(1, user_id);
                            result = preparedStatement.executeQuery();

                            if (result.next()) {
                                // If the user exists, update the borrow count by incrementing it by one
                                int currentBorrowCount = result.getInt("borrowed");
                                int newreturnCount = currentBorrowCount - 1;

                                String updateQuery_1 = "UPDATE user SET borrowed = ? WHERE user_id = ?";
                                preparedStatement = db.connection.prepareStatement(updateQuery_1);
                                preparedStatement.setInt(1, newreturnCount);
                                preparedStatement.setInt(2, user_id);

                                int rowsUpdated_1 = preparedStatement.executeUpdate();
                                if (rowsUpdated_1 > 0) {
                                    System.out.println("Borrow count of user with ID " + user_id + " updated successfully.");
                                } else {
                                    System.out.println("Failed to update borrow count of user with ID " + user_id);
                                }
                            } else {
                                System.out.println("User with ID " + user_id + " not found.");
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }

                        load_book();
                        load_book();
                    } else {
                        System.out.println("Failed to update status of book with ID " + b_id);
                    }

                }else{
                    System.out.println("Book is already RETURNED !!!");
                }
            } else {
                System.out.println("Book with ID " + b_id + " not found!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}