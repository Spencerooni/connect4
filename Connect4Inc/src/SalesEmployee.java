/**
 * Created by benl on 13/10/2016.
 */
public class SalesEmployee extends Employees {

    private double commissionRate;
    private double salesTotal;


    public SalesEmployee(int employeeNo, String forename, String surname, String addressLine1, String addressLine2, String town, String county, String postcode, int departmentId, String bankNo, double startingSalary, String nino, double commissionRate, double salestotal) {
        super(employeeNo, forename, surname, addressLine1, addressLine2, town, county, postcode, departmentId, bankNo, startingSalary, nino);
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
