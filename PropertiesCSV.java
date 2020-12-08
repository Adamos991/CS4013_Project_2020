import java.util.*;
import java.io.File;
import java.io.PrintWriter;
import java.io.*;
public class PropertiesCSV
{
    private String CSVFile = "RegisteredProperties.csv";
    void createARegistryOfProperties(){
        try{
            File f = new File("RegisteredProperties.csv");
            if (f.exists()){
                //nothing happens
            }
            else{
                PrintWriter pw = new PrintWriter(new File("RegisteredProperties.csv"));
                StringBuilder sb = new StringBuilder();
                sb.append("Owner");
                sb.append(",");
                sb.append("address");
                sb.append(",");
                sb.append("eircode");
                sb.append(",");
                sb.append("Market Value");
                sb.append(",");
                sb.append("Location Tax");
                sb.append(",");
                sb.append("PPR");
                sb.append(",");
                sb.append("Last Payment");
                sb.append(",");
                sb.append("\n");

                pw.write(sb.toString());
                pw.close();
            }
        }
        catch (Exception E){
            System.out.println("Error");
        }
    }

    void addPropertyToCSV(String Owner, String address, String eircode, double value, String loCat, boolean ppr, int lastPayment){
        String ppry= String.valueOf(ppr);
        String lastPaymenty= String.valueOf(lastPayment);
        String valuey= String.valueOf(value);        
        try{
            FileWriter File = new FileWriter("RegisteredProperties.csv",true);
            BufferedWriter Buffer = new BufferedWriter(File);
            PrintWriter Print = new PrintWriter(Buffer);
            Print.println(Owner + "," +address + "," +eircode + "," +valuey +"," + loCat +"," +ppry + "," +lastPaymenty);
            Print.close();
        }
        catch (Exception E){
            System.out.println("Error");
        }
    }
}

