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
    private ArrayList<PropertyTax> taxes = new ArrayList<>();
    File file;
    private ArrayList<Payment> payments = new ArrayList<>();
    private int yearOfLastPayment = LocalDate.now().getYear();
    public Property(String owner, String address, String eircode, double value, String loCat, boolean ppr, int lastPayment) {
        this.owner = owner;
        this.address = address;
        this.eircode = eircode;
        this.value = value;
        this.loCat = loCat;
        this.ppr = ppr;
        this.yearOfLastPayment = lastPayment;
    }

    void assignATax(PropertyTax yearlyTax){
        try{
            taxes.add(yearlyTax);
            FileWriter File = new FileWriter(owner+".csv",true);
            BufferedWriter Buffer = new BufferedWriter(File);
            PrintWriter Print = new PrintWriter(Buffer);
            Print.println(yearlyTax.toString());
            Print.flush();
            Print.close();
        }
        catch (Exception E){
            System.out.println("Error");
        }
    }

    double getTotalTaxDue(PropertyTax a){
        return a.calcTax();
    }
    
    String getOwner() {
        return owner;
    }

    void setOwner(String owner) {
        this.owner = owner;
    }

    String getAddress() {
        return address;
    }

    String getEircode() {
        return eircode;
    }

    double getValue() {
        return value;
    }

    void setValue(double value) {
        this.value = value;
    }

    String getLocationCategory() {
        return loCat;
    }

    void setLocationCategory(String loCat) {
        this.loCat = loCat;
    }

    boolean getPPR() {
        return ppr;
    }

    void setPPR(boolean ppr) {
        this.ppr = ppr;
    }
    
    int getYearOfLastPayment() {
    	return yearOfLastPayment;
    }
    
    void setYearOfLastPayment(int year) {
    	yearOfLastPayment = year;
    }
    
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
    
    void addToPropertyPayments(Payment payment) {
    	payments.add(payment);
    }
    
    @Override
    public String toString(){
    	return "Address: " +address + ", eircode: " +eircode;
    }
    
    public String toStringDetailed() {
    	return "Owner: " + owner + ", address: " +address + ", eircode: " +eircode + ", market value: " + value + ", Principle Private Residence: " + ppr;
    }
    
    
}
