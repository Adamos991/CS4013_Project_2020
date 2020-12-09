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
    public PropertyTax(Property property) {
        this.property = property;
        value = property.getValue();
        loCat = property.getLocationCategory();
        ppr = property.getPPR();        
    }
    
    void setYearOfLastPayment(int a){
    	yearOfTax = a;
    }
    
    int getYearOfTax(){
    	return yearOfTax;
    }
    
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

    double getMVT(){
        return mvt;
    }

    void setLocationTax() {
        for(int i = 0; i < loCatNames.length; i++) {
            if(loCat.equalsIgnoreCase(loCatNames[i])) {
                locationTax = loCatTax[i];
            }
        }
    }

    double getLocationTax(){
        return locationTax;
    }

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

    double getTax() {
        return totalTax;
    }
    
    void payTax() {
    	paid = true;
    	totalTax = 0;
    }
    
    @Override
    public String toString(){
    	return "€" + String.valueOf(totalTax);
    }
}
