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
                hrAccess();
                System.out.println("HR");
                employeesPerDepartment();
                break;
            case 2 :
                System.out.println("FINANCE");
                break;
        }
    }


    public static void hrAccess(){
        System.out.println("HR Access Level Approved.");
        System.out.println();
        Scanner inputScanner = new Scanner(System.in);
        while (true) {
            System.out.println("Please select what you would like to do.");
            System.out.println("1. Enter New Employee");
            System.out.println("2. Enter New Sales Employee");
            System.out.println("3. Generate Employees per Project report");
            int inputChoice = inputScanner.nextInt();
            inputScanner.next();
            try{
            switch(inputChoice) {
                case 1:
                    System.out.println("Decided to create employee.");
                    break;
                case 2:
                    System.out.println("Decided to create new sales employee");
                    break;
                case 3:
                    System.out.println("Decided to generate report.");
                    employeesPerBU();
                    break;
                default:
                    System.out.println("Invalid option. Please re-emter.");
            }
            }catch(Exception e){
                    System.out.println("Invalid entry. Please re-enter.");
                }
            }
        }



    //Method used by HR in User Story 2
    public static void employeesPerBU (){
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
        }
        catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }



    }

    public static void addNewEmployee(){
        Scanner employeeInputScanner = new Scanner(System.in);
        System.out.print("Please enter forename: ");
        String forename = employeeInputScanner.next();
        System.out.print("Please enter surname: ");
        String surname = employeeInputScanner.next();
        System.out.print("Please enter address line 1: ");
        String addressLine1 = employeeInputScanner.next();
        System.out.print("Please enter address line 2: ");
        String addressLine2 = employeeInputScanner.next();
        System.out.println("Please enter town: ");
        String town = employeeInputScanner.next();
        System.out.println("Please enter county: ");
        String county = employeeInputScanner.next();
        System.out.println("Please enter postcode: ");
        String postcode = employeeInputScanner.next();
        System.out.println("Please enter department id: ");
        int departmentID = employeeInputScanner.nextInt();
        employeeInputScanner.next();
        System.out.println("Please enter bank no: ");
        String bankNo = employeeInputScanner.next();
        System.out.println("Please enter starting salary: ");
        Double startingSalary = employeeInputScanner.nextDouble();
        employeeInputScanner.next();
        System.out.println("Please enter national insurance number:");
        String nationalInsuranceNo = employeeInputScanner.next();

        Employees newEmployee = new Employees(forename,surname,addressLine1,addressLine2,town,county,postcode,departmentID,bankNo,startingSalary,nationalInsuranceNo);
    }


    //Method used in User Story 2 - Employees Per Department
    public static void employeesPerDepartment(){
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
        }
        catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }



    }

}

