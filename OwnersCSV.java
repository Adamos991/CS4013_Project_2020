import java.util.*;
import java.io.File;
import java.io.PrintWriter;
import java.io.*;
public class OwnersCSV
{
	void createARegistryOfOwners(){
        try{
            File f = new File("OwnerAccounts.csv");
            if (f.exists()){
                //nothing happens
            }
            else{
                PrintWriter pw = new PrintWriter(new File("OwnerAccounts.csv"));
                StringBuilder sb = new StringBuilder();
                sb.append("OwnerId");
                sb.append(",");
                sb.append("Password");
                sb.append("\n");

                pw.write(sb.toString());
                pw.close();
            }
        }
        catch (Exception E){
            System.out.println("Error");
        }
    }
    
    void addOwnerToCSV(String ownerid, String password){       
        try{
            FileWriter File = new FileWriter("OwnerAccounts.csv",true);
            BufferedWriter Buffer = new BufferedWriter(File);
            PrintWriter Print = new PrintWriter(Buffer);
            Print.println(ownerid+ "," +password);
            Print.close();
        }
        catch (Exception E){
            System.out.println("Error");
        }
    }
}
