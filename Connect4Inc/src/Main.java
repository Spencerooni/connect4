import java.sql.*;
import java.util.Scanner;

/**
 * Created by joshuak on 13/10/2016.
 */
public class Main {

    public static void main(String[] args) throws SQLException {

        Scanner scanner = new Scanner(System.in);

        //Creating connection with Connect4

        Connection connection;

        connection = DriverManager.getConnection("jdbc:mysql://localhost/Connect4?useSSL=false",
                "root",
                "password");

       PreparedStatement statement =  connection.prepareStatement("Select * from Departments;");
       ResultSet resultResult = statement.executeQuery();

        //Printing Menu

        System.out.println("\n******* *******\n Connect4 Inc\n******* *******");

        System.out.println("\n** Login **");
        System.out.println("\nUsername:");
        String login = scanner.next();
        System.out.println("Password: ");
        String password = scanner.next();



        String loginString = String.format("Select accessNo from Logins where username = \"%s\" and password = \"%s\"", login, password);

        PreparedStatement loginStatement = connection.prepareStatement(loginString);
        ResultSet loginResultSet = loginStatement.executeQuery();

        int accessNo = 0;

        while (loginResultSet.next()){
             accessNo = loginResultSet.getInt("accessNo");
        }

        switch (accessNo){
            case 1 :
                System.out.println("HR");
                break;
            case 2 :
                System.out.println("FINANCE");
                break;
        }
    }
}

