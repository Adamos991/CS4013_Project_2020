import java.io.File;
import java.io.PrintWriter;
import java.io.*;
public class PropertiesCSV {
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
        String[] values = address.split(",");
        String addressy = "";
        for(int i = 0; i < values.length; i++) {
        	addressy += values[i];
        }
        try{
            FileWriter File = new FileWriter("RegisteredProperties.csv",true);
            BufferedWriter Buffer = new BufferedWriter(File);
            PrintWriter Print = new PrintWriter(Buffer);
            Print.println(Owner + "," +addressy + "," +eircode + "," +valuey +"," + loCat +"," +ppry + "," +lastPaymenty);
            Print.close();
        }
        catch (Exception E){
            System.out.println("Error");
        }
    }
    
    void removeAProperty(String eircode){
        File inputFile = new File("RegisteredProperties.csv");
        String inputFile1 = "RegisteredProperties.csv";
        File tempFile = new File("InProgress.csv");
        try{
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
            PrintWriter Print = new PrintWriter(writer);
            String line = "";
            while ((line = reader.readLine()) != null){
                String[] values = line.split(",");
                if (values[2].equals(eircode)){
                    //do nothing
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
}

