import java.time.LocalDate;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javafx.scene.control.*;
public class Owner {
    private String ownerid, password;
    private ArrayList<Property> properties = new ArrayList<>();
    private ArrayList<String> propertiesString = new ArrayList<>();
    private ArrayList<Payment> payments = new ArrayList<>();
    private PropertyTax currentTax;
    private boolean propertiesLoaded = false;
    /**A constructor for the owner */
    public Owner(String ownerid, String password) {
        this.ownerid = ownerid;
        this.password = password;
    }

    ////////////Start
    ListView getDisplayList(){
        ListView<String> displayList = new ListView<>();
        for (int i=0; i<properties.size(); i++){
            displayList.getItems().add(properties.get(i).toString());
        }
        return displayList;
    }

    ListView getDiplayListOfPayments(){
        ListView<String> PaymentsList = new ListView<>();
        for (int i=0; i<properties.size(); i++){
            PaymentsList.getItems().add(payments.get(i).toString());
        }
        return PaymentsList;
    }

    ArrayList getOwnerProperties(){
        return properties;
    }

    ListView getDetailedDisplayList(){
        ListView<String> displayList = new ListView<>();
        for (int i=0; i<properties.size(); i++){
            displayList.getItems().add(properties.get(i).toStringDetailed());
        }
        return displayList;
    }

    ArrayList getProperties(){
        return properties;
    }
    ///////////End
    /**An accessor method for ownerid */
    String getOwnerId() {
        return ownerid;
    }

    /** A mutator method for ownerid*/
    void setOwnerId(String newId) {
        this.ownerid = newId;
    }

    /** A mutator for password */
    void resetPassword(String newPassword) {
        this.password = newPassword;
    }

    /** An accessor for password*/
    String getPassword() {
        return password;
    }

    /**An accessor for the eircode for a particular property owned by the owner */
    String accessEircode(int r) {
        return properties.get(r - 1).getEircode();
    }
    //////Start
    String accessEircodeReal(int r) {
        return properties.get(r).getEircode();
    }
    ////////End
    /** A mutator for the properties owned by the owner, allows the owner to input a new property */
    void registerNewProperty(String owner, String address, String eircode, double value, String loCat, boolean ppr, int lastPayment) {
        Property newProperty = new Property(owner, address, eircode, value, loCat, ppr, lastPayment);
        properties.add(newProperty);
    }    

    /** A method that prints details of all properties owned by the owner */
    void printPropertyDetails() {
        for(int i = 0; i < properties.size(); i++) {
            System.out.println((i + 1) + ") " + properties.get(i).toStringDetailed());
        }
    }

    /** A method that prints all properties owned by the owner */
    void printPropertyList() {
        for(int i = 0; i < properties.size(); i++) {
            System.out.println((i + 1) + ") " + properties.get(i).toString());
        }
    }

    /** A method that makes a payment for the owner */
    boolean makePayment(int b) {
        if(properties.get(b - 1).getYearOfLastPayment() != LocalDate.now().getYear()) {
            Payment payment = new Payment(properties.get(b - 1), properties.get(b - 1).getYearOfLastPayment());
            payment.makePayment();
            payments.add(payment);
            properties.get(b - 1).addToPropertyPayments(payment);
            properties.get(b - 1).setYearOfLastPayment(payment.getYear());
            return true;
        }
        return false;
    }
    //////////Start
    boolean makePaymentReal(int b) {
        if(properties.get(b).getYearOfLastPayment() != LocalDate.now().getYear()) {
            Payment payment = new Payment(properties.get(b), properties.get(b).getYearOfLastPayment());
            payment.makePayment();
            payments.add(payment);
            properties.get(b).addToPropertyPayments(payment);
            properties.get(b).setYearOfLastPayment(payment.getYear());
            return true;
        }
        return false;
    }
    //////////End
    
    /** A method that shows how much is to be paid */
    String getPaymentAmount(int b) {
        currentTax = new PropertyTax(properties.get(b - 1));
        currentTax.calcTax();
        return currentTax.toString();
    }
    
    ////////Start
    String getPaymentAmountReal(int b) {
        currentTax = new PropertyTax(properties.get(b));
        currentTax.calcTax();
        return currentTax.toString();
    }
    ////////End
    
    /** A mutator that removes a property from an owner */
    void removePropertyFromOwner(int r) {
        properties.remove(r - 1);
    }
    
    /////////Start
    void removePropertyFromOwnerReal(int r) {
        properties.remove(r);
    }
    ///////////////End
    
    
    /** A mutator method which changes the value of whether properties are loaded*/
    void setPropertiesLoaded(boolean a) {
        propertiesLoaded = a;
    }

    /** An accessor of boolean propertiesLoaded */
    boolean getPropertiesLoaded() {
        return propertiesLoaded;
    }
}
