import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
public class Admin {
	ArrayList<Owner> listOfOwners = new ArrayList<>();
	boolean found;
	int currentOwner;
	private double paid;
	private OwnersCSV ownersCSV = new OwnersCSV();
	private PropertiesCSV propertiesCSV = new PropertiesCSV();
	private String departmentPassword = "ILoveTheEnvironment69";
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
			ownersCSV.addOwnerToCSV(ownerid, password);
			createAPaymentsCSV();
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
					if(listOfOwners.get(i).getPropertiesLoaded() == false) {
						populatePropertiesOwned();
						listOfOwners.get(i).setPropertiesLoaded(true);
					}
					createAPaymentsCSV();
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
		propertiesCSV.addPropertyToCSV(owner, address, eircode, value, loCat, ppr, lastPayment);
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
	
	void populatePropertiesOwned(){
        try{
            BufferedReader br = new BufferedReader(new FileReader("RegisteredProperties.csv"));
            String line = "";
            while ((line = br.readLine()) != null){
                String[] values = line.split(",");
                if (listOfOwners.get(currentOwner).getOwnerId().equals(values[0])){
                    listOfOwners.get(currentOwner).registerNewProperty(values[0],values[1],values[2],Double.parseDouble(values[3]),values[4],Boolean.parseBoolean(values[5]),Integer.parseInt(values[6]));
                }
            }
            br.close();
        }
        catch (FileNotFoundException e){
            System.out.println("No properties registered");
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
		changeDate(listOfOwners.get(currentOwner).accessEircode(b));
		addPaymentToCSV(listOfOwners.get(currentOwner).getPaymentAmount(b));
	}
	
	void createRegistryOfOwners() {
		ownersCSV.createARegistryOfOwners();
	}
	
	void createRegistryOfproperties() {
		propertiesCSV.createARegistryOfProperties();
	}
	
	void removePropertyFromOwner(int r) {
		propertiesCSV.removeAProperty(listOfOwners.get(currentOwner).accessEircode(r));
		listOfOwners.get(currentOwner).removePropertyFromOwner(r);
	}
	
	void createAPaymentsCSV(){
        try{
            File f = new File(getCurrentOwner().getOwnerId()+ "'s payments"+".csv");
            if (f.exists()){
                //nothing happens
            }
            else{
                PrintWriter pw = new PrintWriter(new File(getCurrentOwner().getOwnerId() + "'s payments"+".csv"));
                StringBuilder sb = new StringBuilder();
                sb.append("eircode");
                sb.append(",");
                sb.append("Year of Payment");
                sb.append(",");
                sb.append("Amount");
                sb.append("\n");

                pw.write(sb.toString());
                pw.close();
            }
        }
        catch (Exception E){
            System.out.println("Error");
        }
    }
	
	void showHistoryOfPayments(){
        try{
            BufferedReader br = new BufferedReader(new FileReader(getCurrentOwner().getOwnerId()+ "'s payments"+".csv"));
            String line = "";
            br.readLine();
            while ((line = br.readLine()) != null){
            System.out.println(line);
            }
            br.close();
        }
        catch (FileNotFoundException e){
            System.out.println("Could not find payments, WTF!!!");
        }
        catch(IOException e){
            System.out.println("IO exception");
        }
    }
	
	void showHistoryOfPayments(String ownerid){
        try{
            BufferedReader br = new BufferedReader(new FileReader(ownerid+ "'s payments"+".csv"));
            String line = "";
            br.readLine();
            while ((line = br.readLine()) != null){
            System.out.println(line);
            }
            br.close();
        }
        catch (FileNotFoundException e){
            System.out.println("Could not find payments, WTF!!!");
        }
        catch(IOException e){
            System.out.println("IO exception");
        }
    }
	
	void showHistoryOfPaymentsPerProperty(int a){
        try{
            BufferedReader br = new BufferedReader(new FileReader(getCurrentOwner().getOwnerId()+ "'s payments"+".csv"));
            String line = "";
            br.readLine();
            while ((line = br.readLine()) != null){
                String[] values = line.split(",");
                if (values[0].equals(listOfOwners.get(currentOwner).accessEircode(a))){
                System.out.println(values[0] + "," + values[1] + "," + values[2]);
                }
            }
            br.close();
        }
        catch (FileNotFoundException e){
            System.out.println("Could not find payments, WTF!!!");
        }
        catch(IOException e){
            System.out.println("IO exception");
        }
    }
	
	void showHistoryOfPaymentsPerProperty(String ownerid, String eircode){
        try{
            BufferedReader br = new BufferedReader(new FileReader(ownerid+ "'s payments"+".csv"));
            String line = "";
            br.readLine();
            while ((line = br.readLine()) != null){
                String[] values = line.split(",");
                if (values[0].equals(eircode)){
                System.out.println(values[0] + "," + values[1] + "," + values[2]);
                }
            }
            br.close();
        }
        catch (FileNotFoundException e){
            System.out.println("Could not find payments, WTF!!!");
        }
        catch(IOException e){
            System.out.println("IO exception");
        }
    }
	
	void showAllPaymentsPerProperty(String eircode){
        try{
            BufferedReader br = new BufferedReader(new FileReader("RegisteredProperties.csv"));
            String line = "";
            br.readLine();
            while ((line = br.readLine()) != null){
                String[] values = line.split(",");
                if (values[2].equals(eircode)){
                	showHistoryOfPaymentsPerProperty(values[0], values[2]);
                }
            }
            br.close();
        }
        catch (FileNotFoundException e){
            System.out.println("Could not find payments, WTF!!!");
        }
        catch(IOException e){
            System.out.println("IO exception");
        }
    }
	
	void addPaymentToCSV(String Payment){
        try{
            FileWriter File = new FileWriter(getCurrentOwner().getOwnerId()+ "'s payments"+".csv",true);
            BufferedWriter Buffer = new BufferedWriter(File);
            PrintWriter Print = new PrintWriter(Buffer);
            Print.println(getCurrentOwner().getOwnerId() + "," +Payment);
            Print.close();
        }
        catch (Exception E){
            System.out.println("Error");
        }
    }
	
	void changeDate(String eircode){
		int overwrittenYear = LocalDate.now().getYear();
        File inputFile = new File("RegisteredProperties.csv");
        String inputFile1 = "RegisteredProperties.csv";
        File tempFile = new File("InProgress.csv");
        String newYear = String.valueOf(overwrittenYear);
        try{
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
            PrintWriter Print = new PrintWriter(writer);
            String line = "";
            while ((line = reader.readLine()) != null){
                String[] values = line.split(",");
                if (values[2].equals(eircode)){
                    Print.println(values[0]+ "," +values[1] + "," +values[2] + "," +String.valueOf(values[3]) + "," + values[4] + "," + String.valueOf(values[5]) + "," + newYear);
                }
                else{
                    Print.println(values[0]+ "," +values[1] + "," +values[2] + "," +String.valueOf(values[3]) + "," + values[4] + "," + String.valueOf(values[5]) + "," + String.valueOf(values[6]));
                }
            }
            writer.close();
            reader.close();
            inputFile.delete();
            tempFile.renameTo(new File(inputFile1));
        }
        catch (FileNotFoundException e){
            System.out.println("File not found");
        }
        catch (IOException e){
            System.out.println("IO Exception");
        }
    }
	
	void showAllOverDueTaxes(){
        try{
            BufferedReader br = new BufferedReader(new FileReader("RegisteredProperties.csv"));
            String line = "";
            while ((line = br.readLine()) != null){
                String[] values = line.split(",");
                Property readProperty = new Property(values[0],values[1],values[2],Double.parseDouble(values[3]),values[4],Boolean.parseBoolean(values[5]),Integer.parseInt(values[6]));
                PropertyTax readPropertyTax = new PropertyTax(readProperty);
                if (Integer.parseInt(values[6])!=LocalDate.now().getYear()){
                    System.out.println("Address: " +values[1] + " eircode:" + values[2] + " Last payment was made: " + values[6] + " Amount of tax due: " + readProperty.getTotalTaxDue(readPropertyTax));
                }
            }
            br.close();
        }
        catch (FileNotFoundException e){
            System.out.println("No properties registered");
        }
        catch(IOException e){
            System.out.println("IO exception");
        }
    }
	
	void showAllOverDueTaxesInAnArea(String firstThreeDigits){
        try{
            BufferedReader br = new BufferedReader(new FileReader("RegisteredProperties.csv"));
            String line = "";
            while ((line = br.readLine()) != null){
                String[] values = line.split(",");
                Property readProperty = new Property(values[0],values[1],values[2],Double.parseDouble(values[3]),values[4],Boolean.parseBoolean(values[5]),Integer.parseInt(values[6]));
                PropertyTax readPropertyTax = new PropertyTax(readProperty);
                if (Integer.parseInt(values[6])!=LocalDate.now().getYear() && values[2].startsWith(firstThreeDigits)){
                    System.out.println("Address: " +values[1] + " eircode:" + values[2] + " Last payment was made: " + values[6] + " Amount of tax due: " + readProperty.getTotalTaxDue(readPropertyTax));
                }
            }
            br.close();
        }
        catch (FileNotFoundException e){
            System.out.println("No properties registered");
        }
        catch(IOException e){
            System.out.println("IO exception");
        }
    }
	
	void showStatisticsOfPropertiesInArea(String firstThree){
        try{
            BufferedReader br = new BufferedReader(new FileReader("RegisteredProperties.csv"));
            String line = "";
            br.readLine();
            int amountOfProperties=0;
            int amountPaidThisYear=0;
            double totalTaxPaid=0;
            double averageTaxPaid=0;
            double percentageOfPaid=0;
            while ((line = br.readLine()) != null){
                String[] values = line.split(",");
                if (values[2].startsWith(firstThree)){
                    LivingCalculator(values[0], values[2]);
                    totalTaxPaid += paid;
                    amountOfProperties++;
                    if (Integer.parseInt(values[6]) == LocalDate.now().getYear()){
                        amountPaidThisYear++;
                    }
                }
            }
            averageTaxPaid= (totalTaxPaid/amountOfProperties);
            percentageOfPaid = (amountPaidThisYear/amountOfProperties)*100;
            System.out.println("Total tax paid: " +totalTaxPaid + " Average Tax Paid per Property: " +averageTaxPaid + " Percentage of people who paid this year: " +percentageOfPaid + "%");
            br.close();
        }
        catch (FileNotFoundException e){
            System.out.println("Could not find payments, WTF!!!");
        }
        catch(IOException e){
            System.out.println("IO exception");
        }
    }
	
	void LivingCalculator(String ownerid, String eircode){
        try{
            BufferedReader br = new BufferedReader(new FileReader(ownerid+ "'s payments"+".csv"));
            String line = "";
            while ((line = br.readLine()) != null){
                String[] values = line.split(",");
                if (values[0].equals(eircode)){
                    paid = Integer.parseInt(values[2]);;
                }
            }
            br.close();
        }
        catch (FileNotFoundException e){
            System.out.println("Could not find payments, WTF!!!");
        }
        catch(IOException e){
            System.out.println("IO exception");
        }
    }
	
	boolean depOfEnvLogin(String password) {
		if(password.equals(departmentPassword)) {
			return true;
		} else {
			return false;
		}
	}
}
