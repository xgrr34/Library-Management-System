import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class assignment {
    public static void main(String[] args) {
        // Establishing the connection using the getConnection() method from the db class
        PreparedStatement preparedStatement = null;
        Scanner input = new Scanner(System.in);
        db.getConnection();
        library l1 = new library();



        //////////////////////////////////////////////////////////////////////////////////////

        while(true){

            System.out.println("\t\t\t╔════════════════════════════ OPERATIONS ═══════════════════════════╗");
            System.out.println("\t\t\t║                                                                   ║");
            System.out.println("\t\t\t║  [1] INSERT BOOK                                                  ║");
            System.out.println("\t\t\t║  [2] INSERT USER                                                  ║");
            System.out.println("\t\t\t║  [3] VIEW BOOK                                                    ║");
            System.out.println("\t\t\t║  [4] VIEW USER                                                    ║");
            System.out.println("\t\t\t║  [5] BORROW BOOK                                                  ║");
            System.out.println("\t\t\t║  [6] RETURN BOOK                                                  ║");
            System.out.println("\t\t\t║  [7] EXIT                                                         ║");
            System.out.println("\t\t\t║                                                                   ║");
            System.out.println("\t\t\t╚═══════════════════════════════════════════════════════════════════╝");
            try {
                System.out.println("Insert operation ");
                System.out.print("> ");
                int x = input.nextInt();
                if(x>7 || x<1){
                    throw new IOException("Input out of Range !!");
                }
                else{
                    if(x==1){
                        input.nextLine();
                        System.out.println("Give the name of book");
                        String title1 = input.nextLine();
                        System.out.println("Give the author of the book");
                        String author1 = input.nextLine();
                        System.out.println("Give the genre of the book");
                        String genre1 = input.nextLine();
                        System.out.println("Is this book available ?");
                        Boolean status1 = input.nextBoolean();
                        l1.insert_book(title1,author1,genre1,status1);
                    }
                    else if(x==2){
                        input.nextLine();
                        System.out.println("Give the name of user : ");
                        String name = input.nextLine();
                        System.out.println("Give the contact of the user : ");
                        String contact = input.nextLine();
                        l1.insert_user(contact,name);
                    }
                    else if(x==3){
                        System.out.print("Enter id :");
                        int Id = input.nextInt();
                        l1.view_book(Id);
                    }
                    else if(x==4){
                        System.out.print("Enter id of the user :");
                        int Id = input.nextInt();
                        l1.view_user(Id);
                    }
                    else if(x==5){
                        System.out.println("Enter user ID : ");
                        int user_id=input.nextInt();
                        System.out.println("Enter ID of book you want to borrow ");
                        int b_id = input.nextInt();
                        l1.borrow_book(user_id,b_id);
                    }
                    else if(x==6){
                        System.out.println("Enter user ID : ");
                        int user_id=input.nextInt();
                        System.out.println("Enter ID of book you want to borrow ");
                        int b_id = input.nextInt();
                        l1.return_book(user_id,b_id);
                    }
                    else{
                        break;
                    }
                }
            } catch (IOException e) {
                System.out.println("wrong input 123");
            }
            catch (Exception g){
                System.out.println("wrong input 456");
                input.nextLine();
            }

        }
        db.close();
    }

}
