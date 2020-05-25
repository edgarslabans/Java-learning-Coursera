/**
 * Find the highest (hottest) temperature in any number of files of CSV weather data chosen by the user.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class CSVMax {
    public CSVRecord hottestHourInFile(CSVParser parser) {
        //start with largestSoFar as nothing
        CSVRecord largestSoFar = null;
        //For each row (currentRow) in the CSV File
        for (CSVRecord currentRow : parser) {
            // use method to compare two records
            largestSoFar = getLargestOfTwo(currentRow, largestSoFar);
        }
        //The largestSoFar is the answer
        return largestSoFar;
    }
    public CSVRecord coldestHourInFile(CSVParser parser) {
        //start with largestSoFar as nothing
        CSVRecord smallestSoFar = null;
        //For each row (currentRow) in the CSV File
        for (CSVRecord currentRow : parser) {
            // use method to compare two records
            smallestSoFar = getSmallestOfTwo(currentRow, smallestSoFar);
        }
        //The largestSoFar is the answer
        return smallestSoFar;
    }
    
    public CSVRecord lowestValueInColumn(CSVParser parser,String colName) {
        //start with largestSoFar as nothing
        CSVRecord smallestSoFar = null;

        for (CSVRecord currentRow : parser) {
            // use method to compare two records
            if (smallestSoFar == null) {
            smallestSoFar = currentRow;
            }
            else {
                double currentTemp = Double.parseDouble(currentRow.get(colName));
                double smallestTemp = Double.parseDouble(smallestSoFar.get(colName));
                //Check if currentRow’s temperature > largestSoFar’s
                if (currentTemp < smallestTemp) {
                    //If so update largestSoFar to currentRow
                        smallestSoFar = currentRow;
                }
            }
        }
        return smallestSoFar;
    }  

    
    public void testHottestInDay () {
        FileResource fr = new FileResource("data/2014/weather-2014-05-01.csv");         //YYYY-MM-DD
        CSVRecord largest = hottestHourInFile(fr.getCSVParser());
        System.out.println("hottest temperature was " + largest.get("TemperatureF") +
                   " at " + largest.get("TimeEDT"));
                   
        CSVRecord smallest = coldestHourInFile(fr.getCSVParser());
        System.out.println("coldest temperature was " + smallest.get("TemperatureF") +
                   " at " + smallest.get("TimeEDT"));           
                   
    }
    
    public void lowestHumidityInDay () {
        FileResource fr = new FileResource("data/2014/weather-2014-07-22.csv");
        CSVRecord smallest = lowestValueInColumn(fr.getCSVParser(),"Humidity");
        System.out.println("Lowest humidity was " + smallest.get("Humidity") +
                   " at " + smallest.get("DateUTC"));

    }
    
    public void avgValueInDay (String colVal) {
        FileResource fr = new FileResource("data/2013/weather-2013-08-10.csv");
        double avg = avgVal(fr.getCSVParser(),colVal);
        System.out.println("The average "+colVal+" is "+avg );

    }    
    
    public void avgValueInDayWithtreshold (double treshold) {
        FileResource fr = new FileResource("data/2013/weather-2013-09-02.csv");
        double avg = averageTemperatureWithHighHumidityInFile(fr.getCSVParser(), treshold);
        System.out.println("The average temp with treshold"+" is "+avg );

    }       
    
    
    
    public double avgVal(CSVParser parser,String colName) {
        double total_sum = 0;
        int num = 0;
        double avg = 0;
        for (CSVRecord currentRow : parser) {
            total_sum = total_sum + Double.parseDouble(currentRow.get(colName));
            num++;
            
        }
        avg = total_sum/num;
        return avg;
    } 
    

        public double averageTemperatureWithHighHumidityInFile (CSVParser parser,double treshold) {
        double total_sum = 0;
        int num = 0;
        double avg = 0;

        for (CSVRecord currentRow : parser) {
            double curr =   Double.parseDouble(currentRow.get("Humidity")); 
            if (curr >= treshold){
                total_sum = total_sum + Double.parseDouble(currentRow.get("TemperatureF"));
                num++;
            }
        }
        avg = total_sum/num;
        return avg;
    } 
    
    
    
    
    
    
    public CSVRecord lowestValueInManyDays(String colVal) {
        CSVRecord lowestSoFar2 = null;
        DirectoryResource dr = new DirectoryResource();
        // iterate over files
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            // use method to get largest in file.
            CSVRecord currentRow = lowestValueInColumn(fr.getCSVParser(),colVal);
            // use method to compare two records
            lowestSoFar2 = getSmallestOfTwoAny(currentRow, lowestSoFar2, colVal);
        }
        //The largestSoFar is the answer
        return lowestSoFar2;
    }
    
   
    public CSVRecord hottestInManyDays() {
        CSVRecord largestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        // iterate over files
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            // use method to get largest in file.
            CSVRecord currentRow = hottestHourInFile(fr.getCSVParser());
            // use method to compare two records
            largestSoFar = getLargestOfTwo(currentRow, largestSoFar);
        }
        //The largestSoFar is the answer
        return largestSoFar;
    }

     
    public CSVRecord fileWithColdestTemperature() {
        CSVRecord smallestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        // iterate over files
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            // use method to get largest in file.
            CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
            // use method to compare two records
            smallestSoFar = getSmallestOfTwo(currentRow, smallestSoFar);
        }
        //The largestSoFar is the answer
        return smallestSoFar;
    }    
    
    
    public CSVRecord getLargestOfTwo (CSVRecord currentRow, CSVRecord largestSoFar) {
        //If largestSoFar is nothing
        if (largestSoFar == null) {
            largestSoFar = currentRow;
        }
        //Otherwise
        else {
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            double largestTemp = Double.parseDouble(largestSoFar.get("TemperatureF"));
            //Check if currentRow’s temperature > largestSoFar’s
            if (currentTemp > largestTemp) {
                //If so update largestSoFar to currentRow
                    largestSoFar = currentRow;
            }
        }
        return largestSoFar;
    }
    public CSVRecord getSmallestOfTwo (CSVRecord currentRow, CSVRecord smallestSoFar) {
        //If largestSoFar is nothing
        if (smallestSoFar == null) {
            smallestSoFar = currentRow;
        }
        //Otherwise
        else {
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            double smallestTemp = Double.parseDouble(smallestSoFar.get("TemperatureF"));
            //Check if currentRow’s temperature > largestSoFar’s
            if ((currentTemp < smallestTemp) && (currentTemp > -1000)) {
                //If so update largestSoFar to currentRow
                    smallestSoFar = currentRow;
            }
        }
        return smallestSoFar;
    } 
    
        public CSVRecord getSmallestOfTwoAny (CSVRecord currentRow, CSVRecord smallestSoFar,String colVal) {
        //If largestSoFar is nothing
        if (smallestSoFar == null) {
            smallestSoFar = currentRow;
        }
        //Otherwise
        else {
            double currentTemp = Double.parseDouble(currentRow.get(colVal));
            double smallestTemp = Double.parseDouble(smallestSoFar.get(colVal));
            //Check if currentRow’s temperature > largestSoFar’s
            if (currentTemp < smallestTemp) {
                //If so update largestSoFar to currentRow
                    smallestSoFar = currentRow;
            }
        }
        return smallestSoFar;
    } 


    public void testHottestInManyDays () {
        CSVRecord largest = hottestInManyDays();
        System.out.println("hottest temperature was " + largest.get("TemperatureF") +
                   " at " + largest.get("DateUTC"));

    }
        public void testcoldestInManyDays () {
        CSVRecord smallest = fileWithColdestTemperature();
        System.out.println("coldest temperature was " + smallest.get("TemperatureF") +
                   " at " + smallest.get("DateUTC"));
                        
    }
    public void LowestHumidityInManyDays () {
        CSVRecord smallest = lowestValueInManyDays("Humidity");
        System.out.println("Lowest humidity was " + smallest.get("Humidity") +
                   " at " + smallest.get("DateUTC"));
                        
    }
    
    
}
