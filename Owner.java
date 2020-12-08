import java.util.*;
import java.io.File;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.io.*;
public class Owner {
    private String ownerid, password;
    private ArrayList<Property> properties = new ArrayList<>();
    private Property currentProperty;
    private ArrayList<Payment> payments = new ArrayList<>();
    private PropertyTax currentTax;
    public Owner(String ownerid, String password) {
        // TODO Auto-generated constructor stub
        this.ownerid = ownerid;
        this.password = password;
    }

    String getOwnerId() {
        return ownerid;
    }

    void setOwnerId(String newId) {
        this.ownerid = newId;
    }

    void resetPassword(String newPassword) {
        this.password = newPassword;
    }

    String getPassword() {
        return password;
    }

    void assignAProperty(Property propertyOwned){
        try{
            properties.add(propertyOwned);
            FileWriter File = new FileWriter(ownerid+".csv",true);
            BufferedWriter Buffer = new BufferedWriter(File);
            PrintWriter Print = new PrintWriter(Buffer);
            Print.println(propertyOwned.toString());
            Print.flush();
            Print.close();
        }
        catch (Exception E){
            System.out.println("Error");
        }
    }

    void registerNewProperty(String owner, String address, String eircode, double value, String loCat, boolean ppr, int lastPayment) {
        Property newProperty = new Property(owner, address, eircode, value, loCat, ppr, lastPayment);
        properties.add(newProperty);
    }

    void showAllProperties(){
        try{
            Scanner inputStream = new Scanner(new File(ownerid+".csv"));
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
    
    void printPropertyDetails() {
    	for(int i = 0; i < properties.size(); i++) {
    		System.out.println((i + 1) + ") " + properties.get(i).toStringDetailed());
    	}
    }
    
    void printPropertyList() {
    	for(int i = 0; i < properties.size(); i++) {
    		System.out.println((i + 1) + ") " + properties.get(i).toString());
    	}
    }
    
    void makePayment(int b) {
    	if(properties.get(b - 1).getYearOfLastPayment() != LocalDate.now().getYear()) {
	    	Payment payment = new Payment(properties.get(b - 1), currentProperty.getYearOfLastPayment());
	    	payment.makePayment();
	    	payments.add(payment);
	    	properties.get(b - 1).addToPropertyPayments(payment);
	    	properties.get(b - 1).setYearOfLastPayment(payment.getYear());
    	}
    }
    
    String getPaymentAmount(int b) {
		currentTax = new PropertyTax(properties.get(b - 1));
		currentTax.calcTax();
		return currentTax.toString();
	}
}
