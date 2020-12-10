import java.util.*;
import java.time.*;
import java.io.File;
import java.io.PrintWriter;
import java.io.*;
public class Property {
    String owner, address, eircode, loCat;
    double value;
    boolean ppr; 
    PrintWriter CSVOfProperty;
    File file;
    private ArrayList<Payment> payments = new ArrayList<>();
    private int yearOfLastPayment = LocalDate.now().getYear();
    /**A constructor of the Property object */
    public Property(String owner, String address, String eircode, double value, String loCat, boolean ppr, int lastPayment) {
        this.owner = owner;
        this.address = address;
        this.eircode = eircode;
        this.value = value;
        this.loCat = loCat;
        this.ppr = ppr;
        this.yearOfLastPayment = lastPayment;
    }

    /** An accessor method which shows the tax due to pay for the property */
    double getTotalTaxDue(PropertyTax a){
        return a.calcTax();
    }

    /** An accessor method for the owner of the property */
    String getOwner() {
        return owner;
    }

    /** A mutator method for the owner of the property */
    void setOwner(String owner) {
        this.owner = owner;
    }

    /** An accessor method for the address of the property */
    String getAddress() {
        return address;
    }

    /** An accessor method for the eircode of the property */
    String getEircode() {
        return eircode;
    }

    /** An accessor method for the estimated market value of the property */
    double getValue() {
        return value;
    }

    /** A mutator method for the estimated market value of the property */
    void setValue(double value) {
        this.value = value;
    }

    /** An accessor method for the location category */
    String getLocationCategory() {
        return loCat;
    }

    /** A mutator method for the location category */
    void setLocationCategory(String loCat) {
        this.loCat = loCat;
    }

    /** An accessor method for whether the property is a principal private residance*/
    boolean getPPR() {
        return ppr;
    }

    /** A mutator method for whether the property is a principal private residance */
    void setPPR(boolean ppr) {
        this.ppr = ppr;
    }

    /** An accessor method for the last year of payment on property */
    int getYearOfLastPayment() {
        return yearOfLastPayment;
    }

    /** A mutator method for last payment */
    void setYearOfLastPayment(int year) {
        yearOfLastPayment = year;
    }

    /** An accessor which shows all taxes due for the property */
    void showAllTaxesDue(){
        try{
            Scanner inputStream = new Scanner(new File(owner+".csv"));
            while (inputStream.hasNext()){
                String data = inputStream.next();
                System.out.println(data);
            }
            inputStream.close();
        }
        catch (FileNotFoundException e){
            System.out.println("error");
        }
    }

    /** A mutator which adds a payment for a property to the Arraylist*/
    void addToPropertyPayments(Payment payment) {
        payments.add(payment);
    }

    /** An Overriden toString() method returning the address and eircode of our property */
    @Override
    public String toString(){
        return "Address: " +address + ", eircode: " +eircode;
    }

    /** A toString() method showing a lot more details for our property */
    public String toStringDetailed() {
        return "Owner: " + owner + ", address: " +address + ", eircode: " +eircode + ", market value: " + value + ", Principle Private Residence: " + ppr;
    }

}
