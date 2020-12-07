import java.util.ArrayList;
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
	
	
}
