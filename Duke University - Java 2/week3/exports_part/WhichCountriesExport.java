/**
 * Reads a chosen CSV file of country exports and prints each country that exports coffee.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;
import org.apache.commons.csv.*;

public class WhichCountriesExport {
    public void listExporters(CSVParser parser, String exportOfInterest) {
        //for each row in the CSV File
        int num = 0;
        for (CSVRecord record : parser) {
            //Look at the "Exports" column
            String export = record.get("Exports");
            //Check if it contains exportOfInterest
            if (export.contains(exportOfInterest)) {
                //If so, write down the "Country" from that row
                String country = record.get("Country");
                System.out.println(country);
                num++;
            }
        }
        System.out.println("Number of entries: "+num);
    }

    public void largestExporters(CSVParser parser) {
        //for each row in the CSV File
        int num = 0;
        for (CSVRecord record : parser) {
            //Look at the "Exports" column
            String export = record.get("Value (dollars)");
            //Check if it contains exportOfInterest
            if (export.length()>17) {
                //If so, write down the "Country" from that row
                String country = record.get("Country");
                System.out.println(country);
                num++;
            }
        }
        System.out.println("Number of entries: "+num);
    }   
    
    
    
    public void whoExportsCoffee() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        listExporters(parser, "cocoa");
    }
    /*  This method should be restored
     *  public void exportingTwoItems() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        listExportersTwoItems(parser, "cotton","flowers");
    }
    */
    
    public void FindLargestExporters() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        largestExporters(parser);
    }
   
   
}
