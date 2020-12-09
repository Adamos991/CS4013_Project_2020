import java.util.Scanner;
public class CLI {
	private Scanner in;
	boolean exit, loggedIn = false, ppr;
	String address, eircode, loCat, unnused, userId, password, routingKey;
	int command, lastPayment;
	Admin admin;
	double value;
	public CLI() {
		exit = false;
		in = new Scanner(System.in);
		admin = new Admin();
	}
	public void runCLI() {
		admin.createRegistryOfOwners();
		admin.populatelistOfOwners();
		admin.createRegistryOfproperties();
		while(!exit) {
			if(loggedIn == false) {
				System.out.println("1) Login, 2) Create Account, 3) Department of Environment Login 4) Exit");
				command = in.nextInt();
				if(command == 1) {
					in = new Scanner(System.in);
					//Login to existing user
					System.out.print("Enter user id: ");
					userId = in.nextLine();
					System.out.print("Enter password: ");
				    password = in.nextLine();
					if(admin.checkLogin(userId, password) == true) {
						loggedIn = true;
					} else {
						loggedIn = false;
						System.out.println("Invalid login details");
					}
				} else if(command == 2) {
					in = new Scanner(System.in);
					//Create new user
					System.out.print("Enter user id: ");
					userId = in.nextLine();
					System.out.print("Enter password: ");
				    password = in.nextLine();
					if(admin.createOwner(userId, password) == true) {
						loggedIn = true;
					} else {
						System.out.println("Username already taken");
					}
				} else if(command == 3) {
					depOfEnvironment();
				} else if(command == 4) {
					exit = true;
				}
			} else if(loggedIn == true) {
				in = new Scanner(System.in);
				System.out.println("1) Property List, 2) Property Details, 3) Manage Properties 4) Make Payment, 5) Payment History, 6) Logout");
				command = in.nextInt();
				if(command == 1) {
					//List off all the properties owned
					admin.printPropertyList();
				} else if(command == 2) {
					//Gets details of properties
					admin.printPropertyDetails();
				} else if(command == 3) {
					in = new Scanner(System.in);
					System.out.println("1) Register Property, 2) Remove Property, 3) Back");
					command = in.nextInt();
					if(command == 1) {
						in = new Scanner(System.in);
						//Ask for property details
						//Register the property
						System.out.println("You are registering a property.");
						System.out.print("Enter property address: ");
						address = in.nextLine();
						System.out.print("Enter property eircode: ");
						eircode = in.nextLine();
						eircode = removeSpace(eircode);
						System.out.println(eircode); //need to remove this line aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
						System.out.print("Enter property value: ");
						value = in.nextDouble();
						in = new Scanner(System.in);
						System.out.print("Enter property location category (City, Large town, Small town, Village, Countryside): ");
						loCat = in.nextLine();
						System.out.print("Is the property you Principle Private Residence? True/False: ");
						in = new Scanner(System.in);
						ppr = in.nextBoolean();
						System.out.print("Enter the year of the last tax payment made on the property: ");
						in = new Scanner(System.in);
						lastPayment = in.nextInt();
						admin.registerProperty(admin.getCurrentOwner().getOwnerId(), address, eircode, value, loCat, ppr, lastPayment);
						System.out.println("Success");
					} else if(command == 2) {
						in = new Scanner(System.in);
						//Ask for property details
						//Remove the property
						System.out.println("Select a property to remove!");
						admin.printPropertyList();
						command = in.nextInt();
						admin.removePropertyFromOwner(command);
					} else {
						//return to menu
					}
				} else if(command == 4) {
					in = new Scanner(System.in);
					//Make a tax payment
					System.out.println("Select property (by number): ");
					admin.printPropertyList();
					command = in.nextInt();
					System.out.println("Amount due: " + admin.getPaymentAmount(command));
					System.out.print("Please enter credit card number: ");
					in = new Scanner(System.in);
					unnused = in.nextLine();
					admin.makePayment(command);
				} else if(command == 5) {
					in = new Scanner(System.in);
					//Prints off payment history
					System.out.println("1) View payment history for a property, 2) View overall payment history, 3) Back");
					command = in.nextInt();
					if(command == 1) {
						admin.showHistoryOfPaymentsPerProperty(command);
					} else if(command == 2) {
						admin.showHistoryOfPayments();
					} else {
						//return to menu
					}
				} else if(command == 6) {
					in = new Scanner(System.in);
					loggedIn = false;
				} else {
					//return to menu
				}
			}
		}
	}
	void depOfEnvironment() {
		boolean exitDep = false, loggedInDep = false;
		while(!exitDep) {
			if(loggedInDep == false) {
				System.out.println("1) Login to Department of Environment portal, 2) Back");
				in = new Scanner(System.in);
				command = in.nextInt();
				if(command == 1) {
					System.out.print("Enter password: ");
					in = new Scanner(System.in);
					password = in.nextLine();
					if(admin.depOfEnvLogin(password)) {
						loggedInDep = true;
					} else {
						System.out.println("Incorrect password");
					}
				} else {
					exitDep = true;
				}
			} else if(loggedInDep == true) {
				System.out.println("Get property tax data for 1) a property, 2) a property owner,");
				System.out.println("Or 3) List all overdue tax this year, 4) Get property tax statistics for an area, 5) Logout");
				in = new Scanner(System.in);
				command = in.nextInt();
				if(command == 1) {
					in = new Scanner(System.in);
					System.out.print("Enter the eircode of the property: ");
					eircode = in.nextLine();
					eircode = removeSpace(eircode);
					admin.showAllPaymentsPerProperty(eircode);
				} else if(command == 2) {
					in = new Scanner(System.in);
					System.out.print("Enter an ownerid: ");
					userId = in.nextLine();
					admin.showHistoryOfPayments(userId);
				} else if(command == 3) {
					System.out.println("1) List overdue tax on ALL properties, 2) List overdue tax in an area based on a routing key, 3) Back");
					in = new Scanner(System.in);
					command = in.nextInt();
					if(command == 1) {
						admin.showAllOverDueTaxes();
					} else if(command == 2) {
						System.out.print("Please enter a 3 digit routing key: ");
						in = new Scanner(System.in);
						routingKey = in.nextLine();
						admin.showAllOverDueTaxesInAnArea(routingKey);
					}
				} else if(command == 4) {
					System.out.print("Please enter a 3 digit routing key: ");
					in = new Scanner(System.in);
					routingKey = in.nextLine();
					admin.showStatisticsOfPropertiesInArea(routingKey);
				} else {
					loggedInDep = false;
				}
			}
		}
	}
	
	String removeSpace(String eircode) {
		String[] values = eircode.split(" ");
		String newEircode = "";
		for(int i = 0; i < values.length; i++) {
			newEircode += values[i];
		}
		return newEircode;
	}
	
}
