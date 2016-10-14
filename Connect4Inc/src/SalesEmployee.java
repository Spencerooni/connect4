/**
 * Created by benl on 13/10/2016.
 */
public class SalesEmployee extends Employees {

    private double commissionRate;
    private double salestotal;


    public SalesEmployee(String forename, String surname, String addressLine1, String addressLine2, String town, String county, String postcode, int departmentId, String bankNo, double startingSalary, String nino, double commissionRate, double salestotal) {
        super(forename, surname, addressLine1, addressLine2, town, county, postcode, departmentId, bankNo, startingSalary, nino);
        this.commissionRate = commissionRate;
        this.salestotal = salestotal;

    }
}
