import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
public class Admin {
	ArrayList<Owner> listOfOwners = new ArrayList<>();
	boolean found;
	int currentOwner;
	boolean createOwner(String ownerid, String password) {
		found = false;
		for(int i = 0; i < listOfOwners.size(); i++) {
			if(ownerid == listOfOwners.get(i).getOwnerId()) {
				found = true;
			}
		}
		if(found == false) {
			Owner newOwner = new Owner(ownerid, password);
			listOfOwners.add(newOwner);
			setCurrentOwner(listOfOwners.size() - 1);
			return true;
		} else { 
			return false;
		}
	}
	
	boolean checkLogin(String ownerid, String password) {
		for(int i = 0; i < listOfOwners.size(); i++) {
			if(ownerid.equals(listOfOwners.get(i).getOwnerId())) {
				//correct ownerid is entered
				if(password.equals(listOfOwners.get(i).getPassword())) {
					setCurrentOwner(i);
					return true;
				} else {
					System.out.println("Incorrect password"); //need a better way of doing this
					return false;
				}
			}
		}
		return false;
	}
	
	void setCurrentOwner(int a) {
		currentOwner = a;
	}
	
	Owner getCurrentOwner() {
		return listOfOwners.get(currentOwner);
	}
	
	void printPropertyDetails() {
		listOfOwners.get(currentOwner).printPropertyDetails();
	}
	
	void printPropertyList() {
		listOfOwners.get(currentOwner).printPropertyList();
	}
	void registerProperty(String owner, String address, String eircode, double value, String loCat, boolean ppr, int lastPayment) {
		listOfOwners.get(currentOwner).registerNewProperty(owner, address, eircode, value, loCat, ppr, lastPayment);
	}
	
	void populatelistOfOwners(){
        try{
            BufferedReader br = new BufferedReader(new FileReader("OwnerAccounts.CSV"));
            String line = "";
            line = br.readLine();
            while((line = br.readLine()) != null){
                String[] values = line.split(",");
                Owner readOwner = new Owner(values[0], values[1]);
                listOfOwners.add(readOwner);
            }
            br.close();
        }
        catch (FileNotFoundException e){
            System.out.println("No OwnerAccounts Registered");
        }
        catch(IOException e){
            System.out.println("IO exception");
        }
    }
	
	String getPaymentAmount(int b) {
		return listOfOwners.get(currentOwner).getPaymentAmount(b);
	}
	void makePayment(int b) {
		listOfOwners.get(currentOwner).makePayment(b);
	}
}
