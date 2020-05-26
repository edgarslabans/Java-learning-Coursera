
/**
 * Write a description of class QuakeSortInPlace here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class QuakeSortInPlace {
    public QuakeSortInPlace() {
        // TODO Auto-generated constructor stub
    }
   
    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }

    public int getLargestDepth(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getDepth() > quakes.get(minIdx).getDepth()) {
                minIdx = i;
            }
        }
        return minIdx;
    }

    public boolean checkInSortedOrder(ArrayList<QuakeEntry> quakes){
        for (int i=0; i< quakes.size()-1; i++) {
            if (quakes.get(i).getMagnitude() > quakes.get(i+1).getMagnitude()) {
                return false;
            }
        }
        return true;
    }


    public void onePassBubbleSort(ArrayList<QuakeEntry> quakes, int numSorted){

        for (int i=0; i< quakes.size()-1-numSorted; i++) {
            if (quakes.get(i).getMagnitude() > quakes.get(i+1).getMagnitude() ){
                QuakeEntry qi = quakes.get(i+1);
                QuakeEntry qiLow = quakes.get(i);
                quakes.set(i,qi);
                quakes.set(i+1,qiLow);
            }
        }
    }

    public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> in){
        for (int i=0; i< in.size(); i++) {
            onePassBubbleSort(in,i);
            if (checkInSortedOrder(in)){
                i++;
                System.out.println("Array sorted after "+i+"runs");
                break;
            }
        }
    }



    public void sortByMagnitude(ArrayList<QuakeEntry> in) {
       
       for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
           if (checkInSortedOrder(in)){
               i++;
               System.out.println("Array sorted after "+i+"runs");
               break;
           }
        }
        
    }
    public void sortByDepth(ArrayList<QuakeEntry> in) {

        for (int i=0; i< 50; i++) {                  // i< in.size()
            int minIdx = getLargestDepth(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
        }

    }





    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "nov20quakedatasmall.atom";
        //String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);



        System.out.println("read data for "+list.size()+" quakes");    
        sortByMagnitude(list);
        for (QuakeEntry qe: list) { 
            System.out.println(qe);
        } 
        
    }
    
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        //String source = "data/nov20quakedatasmall.atom";
        String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);

        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }
    
    public void dumpCSV(ArrayList<QuakeEntry> list) {
		System.out.println("Latitude,Longitude,Magnitude,Info");
		for(QuakeEntry qe : list){
			System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
			                  qe.getLocation().getLatitude(),
			                  qe.getLocation().getLongitude(),
			                  qe.getMagnitude(),
			                  qe.getInfo());
	    }
		
	}
    public static void main(String [] args)
    {
        QuakeSortInPlace qsp = new QuakeSortInPlace();
        EarthQuakeParser xp = new EarthQuakeParser();
        //String source = "data/testTitles.atom";
        String source = "data/earthQuakeDataDec6sample2.atom";

        ArrayList<QuakeEntry> list  = xp.read(source);



        qsp.sortByDepth(list);                                // sort by selection sort and SortTo comparable
        //qsp.sortByMagnitude(list);
        //qsp.sortByMagnitudeWithBubbleSort(list);              // sort by bubble sort

        //Collections.sort(list);                               // sorting by SortTo comparable


        //Collections.sort(list, new TitleAndDepthComparator());
        //Collections.sort(list, new TitleLastAndMagnitudeComparator());


        for(QuakeEntry loc : list){
            System.out.println(loc);
        }

        int quakeNumber = 600;
        System.out.println("Print quake entry in position " + quakeNumber);
        System.out.println(list.get(quakeNumber));


    }


}
