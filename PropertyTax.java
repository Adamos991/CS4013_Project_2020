import java.time.*;
public class PropertyTax {
    Property property;
    String loCat;
    double totalTax, locationTax, rate, value, mvt; //mvt = market value tax
    boolean ppr, paid = false;
    String[] loCatNames = {"City", "Large town", "Small town", "Village", "Countryside"};
    double[] loCatTax = {100, 80, 60, 50, 25};
    double[] propVal = {150000, 400000, 650000};
    double[] propRate = {0, 0.0001, 0.0002, 0.0004};
    int yearOfTax = LocalDate.now().getYear();
    /** A PropertyTax Constructor */
    public PropertyTax(Property property) {
        this.property = property;
        value = property.getValue();
        loCat = property.getLocationCategory();
        ppr = property.getPPR();        
    }
    
    /** A mutator which sets the last year of payment*/
    void setYearOfLastPayment(int a){
        yearOfTax = a;
    }
    
    /** An accessor which shows the year the tax is being applied*/
    int getYearOfTax(){
        return yearOfTax;
    }
    
    /** A mutator that calculates the Market value tax*/
    void setMVT() {
        for(int i = 0; i < propVal.length; i++) {
            if(value < propVal[i]) {
                rate = propRate[i];
                break;
            } else if(value > 650000) {
                rate = propRate[3];
            }
        }
        mvt = value * rate;
    }
    
    /** An accessor for the Market Value Tax */
    double getMVT(){
        return mvt;
    }
    
    /** A mutator for calculating the location tax*/
    void setLocationTax() {
        for(int i = 0; i < loCatNames.length; i++) {
            if(loCat.equalsIgnoreCase(loCatNames[i])) {
                locationTax = loCatTax[i];
            }
        }
    }
    
    /** An accessor for the location tax*/
    double getLocationTax(){
        return locationTax;
    }
    
    /** A mutator for calculating the overall tax*/
    double calcTax() { 
        if(paid == false) {
            setMVT();
            setLocationTax();
            totalTax = 100 + mvt + locationTax;
            if(ppr == true) {
                totalTax += 100;
            }
            if (yearOfTax!=LocalDate.now().getYear()){
                for(int i = 0; i < LocalDate.now().getYear() - yearOfTax; i++) {
                    totalTax += totalTax * 0.07;
                }
            }
        }
            return totalTax;
    }
    
    /** An accessor for the tax */
    double getTax() {
        return totalTax;
    }
    
    /** A boolean mutator determining if the tax has been paid, and if it has, making the tax 0 */
    void payTax() {
        paid = true;
        totalTax = 0;
    }
    
    /** An overriden toString() method which returns the total tax*/
    @Override
    public String toString(){
        return String.valueOf(totalTax);
    }
}
