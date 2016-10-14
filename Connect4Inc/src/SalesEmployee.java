/**
 * Created by benl on 13/10/2016.
 */
public class SalesEmployee extends Employees {

    private double commissionRate;
    private double salesTotal;


<<<<<<< HEAD
    public SalesEmployee(int employeeNo, String forename, String surname, String addressLine1, String addressLine2, String town, String county, String postcode, int departmentId, String bankNo, double startingSalary, String nino, double commissionRate, double salestotal) {
        super (forename, surname, addressLine1, addressLine2, town, county, postcode, departmentId, bankNo, startingSalary, nino);
=======
    public SalesEmployee(String forename, String surname, String addressLine1, String addressLine2, String town, String county, String postcode, int departmentId, String bankNo, double startingSalary, String nino, double commissionRate, double salestotal) {
        super(forename, surname, addressLine1, addressLine2, town, county, postcode, departmentId, bankNo, startingSalary, nino);
>>>>>>> 4ad2ad157eeb11c0e15924630db78f5f78b44f29
        this.commissionRate = commissionRate;
        this.salesTotal = salesTotal;

    }

    public double getCommissionRate() {
        return commissionRate;
    }

    public void setCommissionRate(double commissionRate) {
        this.commissionRate = commissionRate;
    }

    public double getSalesTotal() {
        return salesTotal;
    }

    public void setSalesTotal(double salesTotal) {
        this.salesTotal = salesTotal;
    }
}
