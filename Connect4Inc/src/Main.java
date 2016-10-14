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

        PreparedStatement statement = connection.prepareStatement("Select * from Departments;");
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

        while (loginResultSet.next()) {
            accessNo = loginResultSet.getInt("accessNo");
        }

        switch (accessNo) {
            case 1:
                System.out.println("HR");
                employeesPerDepartment();

                break;
            case 2:
                System.out.println("FINANCE");
                employeesGrossPay();
                break;
        }
    }


    //Method used by HR in User Story 2
    public void employeesPerBU() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/Connect4?useSSL=false",
                    "root", "password");

            String sql = "select Departments.name, count(*) as 'EmployeesPerDepartment' from (Employees left join Departments on Employees.departmentId = Departments.id) " +
                    "group by Employees.departmentId";

            PreparedStatement prepr = conn.prepareStatement(sql);

            ResultSet result = prepr.executeQuery();


            System.out.println("Employees Per BU");
            System.out.println("Department" + "\t\t" + "Number of Employees");


            while (result.next()) {
                String department = result.getString("name");
                int emps = result.getInt("EmployeesPerDepartment");

                System.out.printf("%-22s %s\n", department, emps);
            }
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }


    }


    //Method used in User Story 2 - Employees Per Department
    public static void employeesPerDepartment() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/Connect4?useSSL=false",
                    "root", "password");

            String sql = "select Departments.name as 'Department Name', ifnull(concat(Employees.forename, Employees.surname), 'No Employee') as 'Employee Name' " +
                    "from Departments left join Employees on Departments.id = Employees.departmentId";

            PreparedStatement prepr = conn.prepareStatement(sql);

            ResultSet result = prepr.executeQuery();


            System.out.println("Employee Names Per Department");
            System.out.println("Department" + "\t\t\t\t" + "Employee Name");


            while (result.next()) {
                String department = result.getString("Department Name");
                String empsName = result.getString("Employee Name");

                System.out.printf("%-23s %s\n", department, empsName);
            }
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }





    //Method used in User Story 4 - Employees and their Gross Pay
    public static void employeesGrossPay() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/Connect4?useSSL=false",
                    "root", "password");

            String sql = "Select Employees.employeeNo, ifnull(concat(Employees.forename, Employees.surname), 'No Employee') as 'Employee', ifnull((Employees.startingSalary + (Sales.totalSales / 100 * Sales.commissionRate)), Employees.StartingSalary) as 'GrossPay' from Employees LEFT JOIN Sales On Employees.employeeNo = Sales.employeeNo";

            PreparedStatement prepr = conn.prepareStatement(sql);

            ResultSet result = prepr.executeQuery();


            System.out.println("Employees and their Gross Pay");
            System.out.println("Employee" + "\t\t\t\t" + "Gross Pay");


            while (result.next()) {
                String employeeName = result.getString("Employee");
                String grossPay = result.getString("GrossPay");

                System.out.printf("%-23s %s\n", employeeName, grossPay);
            }
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }


    }


}

