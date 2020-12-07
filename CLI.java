import java.util.Scanner;
public class CLI {
	private Scanner in;
	boolean exit, loggedIn = false;
	int command;
	Admin admin;
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
					in = new Scanner(System.in);
					//List off all the properties owned
				} else if(command == 2) {
					in = new Scanner(System.in);
					//Gets details of properties
				} else if(command == 3) {
					in = new Scanner(System.in);
					System.out.println("1) Register Property, 2) Remove Property");
					command = in.nextInt();
					if(command == 1) {
						in = new Scanner(System.in);
						//Ask for property details
						//Register the property
					} else if(command == 2) {
						in = new Scanner(System.in);
						//Ask for property details
						//Remove the property
					}
				} else if(command == 4) {
					in = new Scanner(System.in);
					//Make a tax payment
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
