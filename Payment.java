import java.time.LocalDate;
public class Payment {
	PropertyTax currentTax;
	double amount;
	int yearOfPayment = LocalDate.now().getYear();
	public Payment(Property property, int year) {
		currentTax = new PropertyTax(property);
		currentTax.setYearOfLastPayment(year);
		currentTax.calcTax();
		amount = currentTax.getTax();
	}
	
	void makePayment() {
		if(currentTax.getTax() != 0) {
			currentTax.payTax();
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
		return yearOfPayment + ", Paid: " + amount;
	}
}
