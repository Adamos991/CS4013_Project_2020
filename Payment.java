import java.time.LocalDate;
public class Payment {
	PropertyTax currentTax;
	double amount;
	int yearOfPayment;
	String eircode;
	public Payment(Property property, int year) {
		currentTax = new PropertyTax(property);
		currentTax.setYearOfLastPayment(year);
		currentTax.calcTax();
		amount = currentTax.getTax();
		eircode = property.getEircode();
	}
	
	void makePayment() {
		if(currentTax.getTax() != 0) {
			currentTax.payTax();
			yearOfPayment = LocalDate.now().getYear();
		}
	}
	
	double getAmountDue() {
		return amount;
	}
	
	int getYear() {
		return yearOfPayment;
	}
	
	@Override
	public String toString() {
		return eircode + "," + yearOfPayment + "," + amount;
	}
}
