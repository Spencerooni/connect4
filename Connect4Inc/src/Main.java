import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by joshuak on 13/10/2016.
 */
public class Main {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws SQLException {

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
                System.out.println("\n");
                employeesPerDepartment();
                break;
            case 2 :
                System.out.println("FINANCE");
                break;
            case 3 :
                System.out.println("");
                break;
            case 4 :
                System.out.println("");
        }
    }


    public static void hrAccess(){
        System.out.println("HR Access Level Approved.");
        System.out.println();
        Scanner inputScanner = new Scanner(System.in);
        while (true) {
            System.out.println();
            System.out.println("HR MENU:");
            System.out.println("Please select what you would like to do.");
            System.out.println("1. Enter New Employee");
            System.out.println("2. Enter New Sales Employee");
            System.out.println("3. Generate Employees per Project report");
            System.out.println("4. Quit the program");
            int inputChoice = inputScanner.nextInt();
            inputScanner.nextLine();
            try{
            switch(inputChoice) {
                case 1:
                    System.out.println("Decided to create employee.");
                    addNewEmployee(inputChoice);
                    break;
                case 2:
                    System.out.println("Decided to create new sales employee");
                    addNewEmployee(inputChoice);
                    break;
                case 3:
                    System.out.println("Decided to generate report.");
                    employeesPerBU();
                    break;
                case 4:
                    System.out.println("Quitting program...");
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please re-enter.");
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

    public static void addNewEmployee(int userChoice) {

        switch (userChoice) {
            case 1:
                Scanner employeeInputScanner = new Scanner(System.in);
                System.out.print("Please enter forename: ");
                String forename = employeeInputScanner.next();
                System.out.print("Please enter surname: ");
                String surname = employeeInputScanner.next();
                System.out.print("Please enter address line 1: ");
                String addressLine1 = employeeInputScanner.next();
                System.out.print("Please enter address line 2: ");
                String addressLine2 = employeeInputScanner.next();
                System.out.print("Please enter town: ");
                String town = employeeInputScanner.next();
                System.out.print("Please enter county: ");
                String county = employeeInputScanner.next();
                System.out.print("Please enter postcode: ");
                String postcode = employeeInputScanner.next();
                System.out.print("Please enter department id: ");
                int departmentID = employeeInputScanner.nextInt();
                employeeInputScanner.nextLine();
                System.out.print("Please enter bank no: ");
                String bankNo = employeeInputScanner.next();
                System.out.print("Please enter starting salary: ");
                Double startingSalary = employeeInputScanner.nextDouble();
                employeeInputScanner.nextLine();
                System.out.print("Please enter national insurance number:");
                String nationalInsuranceNo = employeeInputScanner.next();

                Employees newEmployee = new Employees(forename, surname, addressLine1, addressLine2, town, county, postcode, departmentID, bankNo, startingSalary, nationalInsuranceNo);
                break;

            case 2:
                Scanner salesEmployeeScanner = new Scanner(System.in);

                SalesEmployee salesEmployee = new SalesEmployee();

                System.out.println("Please enter forename: ");
                salesEmployee.setForename(salesEmployeeScanner.nextLine());

                System.out.println("Please enter surname: ");
                salesEmployee.setSurname(salesEmployeeScanner.nextLine());

                System.out.println("Please enter address line 1:");
                salesEmployee.setAddressLine1(salesEmployeeScanner.nextLine());

                System.out.println("Please enter address Line 2: ");
                salesEmployee.setAddressLine2(salesEmployeeScanner.nextLine());

                System.out.println("Please enter town: ");
                salesEmployee.setTown(salesEmployeeScanner.nextLine());

                System.out.println("Please enter county: ");
                salesEmployee.setCounty(salesEmployeeScanner.nextLine());

                System.out.println("Please enter postcode: ");
                salesEmployee.setPostcode(salesEmployeeScanner.nextLine());

                System.out.println("Please enter department id: ");
                salesEmployee.setDepartmentId(salesEmployeeScanner.nextInt());
                salesEmployeeScanner.nextLine();

                System.out.println("Please enter bank no: ");
                salesEmployee.setBankNo(salesEmployeeScanner.nextLine());

                System.out.println("Please enter starting salary: ");
                salesEmployee.setStartingSalary(salesEmployeeScanner.nextDouble());
                salesEmployeeScanner.nextLine();

                System.out.println("Please enter national insurance number: ");
                salesEmployee.setNino(salesEmployeeScanner.nextLine());

                System.out.println("Please enter commission rate: ");
                salesEmployee.setCommissionRate(salesEmployeeScanner.nextDouble());
                salesEmployeeScanner.nextLine();

                System.out.println("Please enter sales total: ");
                salesEmployee.setSalesTotal(salesEmployeeScanner.nextDouble());
                salesEmployeeScanner.nextLine();
                break;
        }
    }

    public static void addNewEmployee(){
        try {
        Scanner employeeInputScanner = new Scanner(System.in);
        System.out.print("Please enter forename: ");
        String forename = employeeInputScanner.next();
        System.out.print("Please enter surname: ");
        String surname = employeeInputScanner.next();
        System.out.print("Please enter address line 1: ");
        String addressLine1 = employeeInputScanner.next();
        System.out.print("Please enter address line 2: ");
        String addressLine2 = employeeInputScanner.next();
        System.out.print("Please enter town: ");
        String town = employeeInputScanner.next();
        System.out.print("Please enter county: ");
        String county = employeeInputScanner.next();
        System.out.print("Please enter postcode: ");
        String postcode = employeeInputScanner.next();
        System.out.print("Please enter department id: ");
        int departmentID = employeeInputScanner.nextInt();
        employeeInputScanner.nextLine();
        System.out.print("Please enter bank no: ");
        String bankNo = employeeInputScanner.next();
        System.out.print("Please enter starting salary: ");
        Double startingSalary = employeeInputScanner.nextDouble();
        employeeInputScanner.nextLine();
        System.out.print("Please enter national insurance number:");
        String nationalInsuranceNo = employeeInputScanner.next();

        Employees newEmp = new Employees(forename,surname,addressLine1,addressLine2,town,county,postcode,departmentID,bankNo,startingSalary,nationalInsuranceNo);

            System.out.println(newEmp.getBankNo());
        Connection conn = null;
        conn = DriverManager.getConnection("jdbc:mysql://localhost/Connect4?useSSL=false",
                    "root", "password");

            /*"insert into Employees" +
                    "(" +
                    "forename," +
                    "surname," +
                    "addressLine1, " +
                    "addressLine2," +
                    "town, " +
                    "county, " +
                    "postcode, " +
                    "nino, " +
                    "bankNo, " +
                    "startingSalary, " +
                    "departmentId) " +
                    "values (%s," +
                    "%s," +
                    "%s," +
                    "%s," +
                    "%s," +
                    "%s," +
                    "%s," +
                    "%s," +
                    "%s," +
                    "%d," +
                    "%d)"
                    ,newEmp.getForename(),
                    newEmp.getSurname(),
                    newEmp.getAddressLine1(),
                    newEmp.getAddressLine2(),
                    newEmp.getTown(),
                    newEmp.getCounty(),
                    newEmp.getPostcode(),
                    newEmp.getNino(),
                    newEmp.getBankNo(),
                    newEmp.getStartingSalary(),
                    newEmp.getDepartmentId());*/

            String sql = "insert into Employees" +
                            "(" +
                            "forename," +
                            "surname," +
                            "addressLine1, " +
                            "addressLine2," +
                            "town, " +
                            "county, " +
                            "postcode, " +
                            "nino, " +
                            "bankNo, " +
                            "startingSalary, " +
                            "departmentId) " +
                    "values ("
                    +"\""+newEmp.getForename()+"\""+","
                    +"\""+newEmp.getSurname()+"\""+","
                    +"\""+newEmp.getAddressLine1()+"\""+","
                    +"\""+newEmp.getAddressLine2()+"\""+","
                    +"\""+newEmp.getTown()+"\""+","
                    +"\""+newEmp.getCounty()+"\""+","
                    +"\""+newEmp.getPostcode()+"\""+","
                    +"\""+newEmp.getNino()+"\""+","
                    +"\""+newEmp.getBankNo()+"\""+","
                    +newEmp.getStartingSalary()+","
                    +newEmp.getDepartmentId()+")";

            System.out.println(sql);

            PreparedStatement prep1 =
                    conn.prepareStatement(sql);

            prep1.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
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

    public static void AddSalesEmployee(SalesEmployee emp) {
        Connection conn = null;

        try {
            conn =
                    DriverManager.getConnection("jdbc:mysql://localhost/Connect4?useSSL=false",
                            "root", "password");

            String sql = String.format("insert into Sales (employeeNo, commissionRate, totalSales) values ('%d', '%d', %d)", emp.getEmployeeNo(), emp.getCommissionRate(), emp.getSalesTotal());

            System.out.println(sql);

            PreparedStatement prep1 =
                    conn.prepareStatement(sql);

            prep1.executeUpdate();


        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

    }
    //Method used in User Story 2 - Employees Per Department

    public static void highestSalesEmployeeTotal() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/Connect4?useSSL=false",
                    "root", "password");

            String sql = "select concat(Employees.forename, Employees.surname) as 'Name' , Sales.totalSales as 'Total Sales' from Sales join Employees on Sales.employeeNo = Employees.employeeNo order by Sales.totalSales desc limit 1";

            PreparedStatement prepr = conn.prepareStatement(sql);

            ResultSet result = prepr.executeQuery();


            System.out.println("\nEmployee with the Highest Sales");
            System.out.println("Name" + "\t\t\t\t" + "Total Sales");


            while (result.next()) {
                String name = result.getString("Name");
                double salesTotal = result.getDouble("Total Sales");

                System.out.printf("%-19s %f\n", name, salesTotal);
            }
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }


    public static void manager() {

        char choice = 'a';


        do {
            System.out.println("\n**** Manager Menu ****");
            System.out.println("1 - Top Sales Employee for this period");
            System.out.println("0 - Exit\n");

            System.out.print("Enter your choice: ");
            choice = scanner.nextLine().charAt(0);

            switch (choice) {
                case '1':
                    highestSalesEmployeeTotal();

                    break;
                case '0':
                    System.out.println("GoodBye");
                    break;
                default:
                    System.out.println("Invalid choice\n");
            }


        } while (choice != '0');


    }


    public static void talentManager()

    {

        char choice;
        String projectName;
        char departmentNum;
        String projectName2 = "";


        do {

            // keyboard.nextLine();
            System.out.println("\n**** Talent Manager Menu ****");
            System.out.println("1 - Add a Project");
            System.out.println("2 - Assign a Project");
            System.out.println("3 - View Employee Project Assignments\n");
            System.out.println("0 - Exit\n");


            System.out.println("Enter your choice: ");
            choice = scanner.nextLine().charAt(0);


            switch (choice) {
                case '1':

                    try {
                        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/Connect4?useSSL=false",
                                "root", "password");

                        System.out.print("Please enter a project name to be added: ");
                        projectName = scanner.nextLine();

                        String sql = "select * from Departments";

                        PreparedStatement prep6 = conn.prepareStatement(sql);

                        ResultSet result6 = prep6.executeQuery();


                        System.out.println("\nDepartments");
                        System.out.println("Department Number" + "\t\t" + "Department Name");

                        ArrayList<Integer> depart = new ArrayList<>();

                        int projectNo;


                        while (result6.next()) {


                            projectNo = result6.getInt("id");
                            projectName2 = result6.getString("name");
                            depart.add(projectNo);

                            System.out.printf("%-23d %s\n", projectNo, projectName2);

                        }


                        int departmentNum2;
                        do {

                            System.out.print("\nPlease select a department number for the project: ");
                            departmentNum = scanner.nextLine().charAt(0);
                            departmentNum2 = Character.getNumericValue(departmentNum);
                            depart.add(departmentNum2);


                        } while (!(depart.contains(departmentNum2)));

                        sql = "insert into Projects (projectName, departmentId) values ('" + projectName + "', " + departmentNum2 + ")";
                        System.out.println(sql);

                        PreparedStatement prep9 =
                                conn.prepareStatement(sql);

                        prep9.executeUpdate();

                        System.out.println("\nProject added!");


                    } catch (SQLException ex) {
                        // handle any errors
                        System.out.println("SQLException: " + ex.getMessage());
                        System.out.println("SQLState: " + ex.getSQLState());
                        System.out.println("VendorError: " + ex.getErrorCode());
                    }


                    break;

                case '2':

                    try {
                        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/Connect4?useSSL=false",
                                "root", "password");

                        String sql = "select * from projects";

                        PreparedStatement prep1 = conn.prepareStatement(sql);

                        ResultSet result4 = prep1.executeQuery();


                        System.out.println("Projects");
                        System.out.println("Project Number" + "\t\t" + "Project Name");

                        ArrayList<Integer> projects = new ArrayList<>();
                        int projectNo;


                        while (result4.next()) {


                            projectNo = result4.getInt("id");
                            projectName = result4.getString("projectName");
                            projects.add(projectNo);

                            System.out.printf("%-22d %s\n", projectNo, projectName);


                        }

                        do {

                            System.out.print("Please enter a project number to assign: ");

                            projectNo = scanner.nextInt();
                            scanner.nextLine();

                        } while (!(projects.contains(projectNo)));

                        //Assign Employee

                        sql = "select * from employees";

                        PreparedStatement prep2 =
                                conn.prepareStatement(sql);

                        ResultSet result5 = prep2.executeQuery();


                        System.out.println("Employees");
                        System.out.println("Employee Number" + "\t\t" + "Employee Name");

                        ArrayList<Integer> employees = new ArrayList<>();
                        int employeeNo;

                        while (result5.next()) {
                            employeeNo = result5.getInt("employeeNo");
                            String name = result5.getString("forename").concat(" " + result5.getString("surname"));
                            employees.add(employeeNo);

                            System.out.printf("%-22d %s\n", employeeNo, name);
                        }

                        int employeeNo2;


                        do {
                            System.out.print("Please enter an employee number to assign to project " + projectNo + ": ");
                            employeeNo2 = scanner.nextInt();
                            scanner.nextLine();
                        } while (!(employees.contains(employeeNo2)));


                        conn =
                                DriverManager.getConnection("jdbc:mysql://localhost/Connect4?useSSL=false",
                                        "root", "password");

                        sql = "insert into Assignments (employeeNo, projectId, startDate) values " + "(" + employeeNo2 + ", " + projectNo + ", " + "NOW())";

                        System.out.println(sql);

                        PreparedStatement prep4 =
                                conn.prepareStatement(sql);


                        prep4.executeUpdate();


                        System.out.println("Employee number " + employeeNo2 + " is now assigned to project number " + projectNo);

                    } catch (SQLException ex) {
                        // handle any errors
                        System.out.println("SQLException: " + ex.getMessage());
                        System.out.println("SQLState: " + ex.getSQLState());
                        System.out.println("VendorError: " + ex.getErrorCode());
                    }

                    break;
                case '3':

                    try {


                        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/Connect4?useSSL=false",
                                "root", "password");

                        String sql = "select Projects.projectName, concat(Employees.forename, ' ', Employees.surname) as 'Employee Name' from Projects left join Assignments on Projects.id = Assignments.projectId left join Employees on Assignments.employeeNo = Employees.employeeNo";


                        PreparedStatement prep4 =
                                conn.prepareStatement(sql);

                        ResultSet result6 = prep4.executeQuery();

                        System.out.println("Projects and Employees");
                        System.out.println("Project Name" + "\t\t" + "Employee Name");

                        while (result6.next()) {

                            projectName = result6.getString("projectName");
                            String name = result6.getString("Employee Name");

                            System.out.printf("%-22s %s\n", projectName, name);
                        }


                    } catch (SQLException ex) {
                        // handle any errors
                        System.out.println("SQLException: " + ex.getMessage());
                        System.out.println("SQLState: " + ex.getSQLState());
                        System.out.println("VendorError: " + ex.getErrorCode());
                    }

                    break;


                case '0':
                    System.out.println("GoodBye");
                    break;
                default:
                    System.out.println("Invalid choice\n");
            }


        } while (!(choice == '0'));
    }
}








