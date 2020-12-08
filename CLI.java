import java.util.Scanner;
public class CLI {
	private Scanner in;
	boolean exit, loggedIn = false, ppr;
	String address, eircode, loCat;
	int command, lastPayment;
	Admin admin;
	double value;
	public CLI() {
		exit = false;
		in = new Scanner(System.in);
		admin = new Admin();
	}
	public void runCLI() {
		while(!exit) {
			if(loggedIn == false) {
				System.out.println("1) Login, 2) Create Account, 3) Exit");
				command = in.nextInt();
				if(command == 1) {
					in = new Scanner(System.in);
					//Login to existing user
					System.out.print("Enter user id: ");
					String userId = in.nextLine();
					System.out.print("Enter password: ");
				    String password = in.nextLine();
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
					String userId = in.nextLine();
					System.out.print("Enter password: ");
				    String password = in.nextLine();
					if(admin.createOwner(userId, password) == true) {
						System.out.println(admin.getCurrentOwner().getOwnerId());
						System.out.println(admin.getCurrentOwner().getPassword());
						loggedIn = true;
					} else {
						System.out.println("Username already taken");
					}
				} else if(command == 3) {
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
					System.out.println("1) Register Property, 2) Remove Property");
					command = in.nextInt();
					if(command == 1) {
						in = new Scanner(System.in);
						//Ask for property details
						//Register the property
						System.out.print("You are registering a property.");
						System.out.print("Enter property address: ");
						address = in.nextLine();
						System.out.print("Enter property eircode: ");
						eircode = in.nextLine();
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
					}
				} else if(command == 4) {
					in = new Scanner(System.in);
					//Make a tax payment
					System.out.println("Select property (by number): ");
					admin.printPropertyList();
					command = in.nextInt();
					System.out.println("Amount due: " + admin.getPaymentAmount(command));
					System.out.print("Please enter credit card number: ");
					in.nextLine();
					admin.makePayment(command);
				} else if(command == 5) {
					in = new Scanner(System.in);
					//Prints off payment history
				} else if(command == 6) {
					in = new Scanner(System.in);
					loggedIn = false;
				}
			}
		}
	}
	
	String getInput(String prompt) {
		System.out.println(prompt);
		String input = in.nextLine();
		return input;
	}
}
