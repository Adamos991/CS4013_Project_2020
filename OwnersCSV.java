import java.io.File;
import java.io.PrintWriter;
import java.io.*;
public class OwnersCSV
{
    /** A method which creates a registry of owners*/
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

    /** A mutator which adds an owner to a CSV file */
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
    
    /** A mutator which removes an owner from the CSV files */
    void removeAnOwner(String owner){
        File inputFile = new File("OwnersAccounts.csv");
        String inputFile1 = "Owners.Accounts.csv";
        File tempFile = new File("InProgress.csv");
        try{
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
            PrintWriter Print = new PrintWriter(writer);
            String line = "";
            while ((line = reader.readLine()) != null){
                String[] values = line.split(",");
                if (values[0].equals(owner)){
                    //do nothing
                }
                else{
                    Print.println(values[0]+ "," +values[1]);
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

}
