import java.time.LocalDate;
public class Payment {
	PropertyTax currentTax;
	double amount;
	int yearOfPayment;
	String eircode;
	/** A constructor for a payment*/
	public Payment(Property property, int year) {
		currentTax = new PropertyTax(property);
		currentTax.setYearOfLastPayment(year);
		currentTax.calcTax();
		amount = currentTax.getTax();
		eircode = property.getEircode();
	}
	
	/** A method which allows you to amke a payment on a tax */
	void makePayment() {
		if(currentTax.getTax() != 0) {
			currentTax.payTax();
			yearOfPayment = LocalDate.now().getYear();
		}
	}
	
	/** An accessor method which return the amount of tax due to pay*/
	double getAmountDue() {
		return amount;
	}
	
	/** An accessor that returns the year of payment*/
	int getYear() {
		return yearOfPayment;
	}
	
	/** An overriden toString() method that returns the eircode, the year of payment and the amount paid*/
	@Override
	public String toString() {
		return eircode + "," + yearOfPayment + "," + amount;
	}
}

