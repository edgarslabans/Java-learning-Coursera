/**
 * Print out total number of babies born, as well as for each gender, in a given CSV file of baby name data.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;
import org.apache.commons.csv.*;

public class BabyBirths {
    public void printNames () {
        FileResource fr = new FileResource();
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            if (numBorn <= 100) {
                System.out.println("Name " + rec.get(0) +
                           " Gender " + rec.get(1) +
                           " Num Born " + rec.get(2));
            }
        }
    }

    public int totalBirths (FileResource fr) {
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        int totalNamesGirls = 0;
        int totalNamesBoys = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            if (rec.get(1).equals("M")) {
                totalBoys += numBorn;
                totalNamesBoys++;
            }
            else {
                totalGirls += numBorn;
                totalNamesGirls++;
            }
        }
        //System.out.println("total births = " + totalBirths);
        //System.out.println("female girls = " + totalGirls+" _Number of names = "+ totalNamesGirls);
        //System.out.println("male boys = " + totalBoys+" _Number of names = "+ totalNamesBoys);
        
        return totalNamesGirls;
        
    }

    public int getRank (int year, String name, String gender) {
        FileResource fr = new FileResource("data/yob"+year+".csv");
        int rank = -1;
        int num = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            num++;
            if (rec.get(0).equals(name) && rec.get(1).equals(gender)) {
                rank = num;
                if (gender == "M"){
                    rank = rank - totalBirths(fr);
                }
                break;
            }
        }   
        return rank;
    }

    public String getName (int year, int rank, String gender) {
        FileResource fr = new FileResource("data/yob"+year+".csv");
        String name = "NO NAME";
        int num = 0;
       
        if (gender == "M"){
            rank = rank + totalBirths(fr);
        }
        for (CSVRecord rec : fr.getCSVParser(false)) {
            num++;
            if (num==rank && rec.get(1).equals(gender)) {                  //  rec.get(2).equals(rank)&& rec.get(1).equals(gender)
                name = rec.get(0);
                System.out.println("here: " + rec.get(0)+rec.get(1)+rec.get(2));
                
            }
        }
        return name;
    }    

    public void whatIsNameInYear (String name, int year, int newyear, String gender) {
        String newName = "NOT found";
        int rank = -1;
           
        rank = getRank(year,name,gender);
        newName = getName (newyear,rank,gender);
        System.out.println("The name of "+name+" would be " + newName+" in "+ newyear);
        
    }    
    public int yearOfHighestRank (String name,String gender,int year1, int year2) {
        int currHighest = -1;
        int rank = 0;
        int topYear = 0;
        int sumRank =0;
        int numYearsIntop = 0;                      // number of years the name stays in chart
        for (int i = year1; i < year2+1; i++) {
            //System.out.println(i);
            rank = getRank(i, name, gender);      
            //System.out.println("rank= "+rank);
            sumRank = sumRank+ rank;
            if(rank != -1 && currHighest == -1){
                currHighest = rank;
                topYear = i;
                
                
            } else if (rank != -1 && currHighest > rank){
                currHighest = rank;
                topYear = i; 

            }
        }
        
        double avg = sumRank/(year2+1-year1);
        System.out.println("The highest rank of "+name+" was position " + currHighest +" in "+ topYear+" average "+avg);
        return currHighest;
    }     
     
    public int getTotalBirthsRankedHigher (String name,String gender,int year) {
        FileResource fr = new FileResource("data/yob"+year+".csv");
        int rank = 0;
        int num = 0;
        int sumBirth =0;
        int upper_boundary = 0;
        
        rank = getRank(year, name, gender);
        
        if (gender == "M"){
            upper_boundary = totalBirths(fr);
            rank = rank + upper_boundary;
        }
        

        for (CSVRecord rec : fr.getCSVParser(false)) {
            num++;
            if (num<rank && num > upper_boundary) {                  //  rec.get(2).equals(rank)&& rec.get(1).equals(gender)
                sumBirth = sumBirth + Integer.parseInt(rec.get(2));
            }
        }

        System.out.println("Number of kids with names more popular than "+name+" is " + sumBirth);
        return sumBirth;
    }     
    
    
    

    public void testTotalBirths() {
        //FileResource fr = new FileResource();
        FileResource fr = new FileResource("data/yob1900.csv");
        totalBirths(fr);
    }
    
    public void testGetRank() {
        //FileResource fr = new FileResource();
        System.out.println("The rank 1 is = " + getRank(1960,"Emily","F"));
        System.out.println("The rank 2 is = " + getRank(1971,"Frank","M"));
    }    
    
    public void testGetName() {
        //FileResource fr = new FileResource();
        System.out.println("The name1 of given rank is = " + getName(1980,350,"F"));
        System.out.println("The name2 of given rank is = " + getName(1982,450,"M"));
    }    
    public void testWhatIsNameInYear() {
        //FileResource fr = new FileResource();
        whatIsNameInYear("Susan",1972,2014,"F");
        whatIsNameInYear("Owen",1974,2014,"M");
    }  
    public void testYearOfHighestRankr() {
        //FileResource fr = new FileResource();
        yearOfHighestRank("Genevieve","F",1880,2014);
        yearOfHighestRank("Mich","M",1880,2014);
        yearOfHighestRank("Susan","F",1880,2014);
        yearOfHighestRank("Robert","M",1880,2014);
    } 
    public void TestGetTotalBirthsRankedHigher() {
        //FileResource fr = new FileResource();
        getTotalBirthsRankedHigher("Emily","F",1990);
        getTotalBirthsRankedHigher("Drew","M",1990);
    } 
}

