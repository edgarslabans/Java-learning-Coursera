import java.util.ArrayList;
import java.util.HashMap;

public class EarthQuakeClientWithFilter {
    
    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();

        for (QuakeEntry qe : quakeData) {
            if (f.satisfies(qe)) {
                answer.add(qe);
                System.out.println(qe);
            }
        }
        System.out.println("In total "+answer.size()+" earthquakes who satisfy the filter found");
        return answer;              
    }

    public ArrayList<QuakeEntry> prepareQuakeList(String sourceFileName) {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/"+sourceFileName;
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("# quakes read: " + list.size()+"Source: "+sourceFileName);

        return list;
    }
    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData, double min, double max) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();

        for (QuakeEntry qe : quakeData) {
            if (qe.getDepth() > min  && qe.getDepth() <max) {
                answer.add(qe);
                System.out.println(qe);
            }
        }
        System.out.println("In total "+answer.size()+" earthquakes in the range of " +min+"-"+max +" found");
        return answer;
    }


    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData, String phrase, String pos) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();

        for (QuakeEntry qe : quakeData) {
            String info = qe.getInfo();
            int indexPhr = info.indexOf(phrase, 0);
            if (indexPhr != -1 && indexPhr ==0 && pos == "start" ) {
                answer.add(qe);
                System.out.println(qe+". Start index: "+indexPhr);
            } else if (indexPhr != -1 && indexPhr == info.length()+1-phrase.length() && pos == "end" ){
                answer.add(qe);
                System.out.println(qe+". Start index: "+indexPhr);
            } else if (indexPhr != -1){
                answer.add(qe);
                System.out.println(qe+". Start index: "+indexPhr);
            }
        }
        System.out.println("In total "+answer.size()+" earthquakes containing the phrase" +phrase+" in the "+pos +" position found");
        return answer;
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData, double distMax, Location from) {      
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();

        for (QuakeEntry qe : quakeData) {
            double dist = qe.getLocation().distanceTo(from);
            if (dist < distMax) {
                answer.add(qe);
                System.out.println(qe+". Distance "+dist/1000+" km.");
            }
        }
        System.out.println("In total "+answer.size()+" earthquakes are in the distance of  " +distMax/1000 +"km from "+from.toString());
         return answer;
    }


    public ArrayList<QuakeEntry> closestQuakes(ArrayList<QuakeEntry> quakeData, Location current, int howMany) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        HashMap<QuakeEntry, Integer> hm = new HashMap<QuakeEntry, Integer>();

        for (QuakeEntry qe : quakeData) {
            double dist = qe.getLocation().distanceTo(current);
            int distInt = (int) dist;
            hm.put(qe, distInt);
        }

        HashMap<QuakeEntry, Integer> hm1 = GFG.sortByValue(hm);

        ArrayList<QuakeEntry> quakes = new ArrayList<QuakeEntry>(hm1.keySet());
        ArrayList<Integer> distances = new ArrayList<Integer>(hm1.values());

        System.out.println(howMany+" earth quakes closest to "+current.toString());

        for (int i = 0; i<howMany; i++) {
            System.out.println(quakes.get(i)+ ". Distance " + distances.get(i)/1000 + " km." );
        }
/*
        Location city1 = new Location( 56.946285, 24.105078);   //riga
        Location city2 = new Location( 59.911491, 10.757933);   //oslo
        double testDist = city1.distanceTo(city2)/1000;

        System.out.println("Test Distance " + testDist+ " km. To Int:" );
*/
        return quakes;
    }

    public ArrayList<QuakeEntry> getLargest (ArrayList<QuakeEntry> quakeData, int howMany) {
        HashMap<QuakeEntry, Integer> hm = new HashMap<>();

        for (QuakeEntry qe : quakeData) {
            int distInt = (int) (qe.getMagnitude()*100) ;
            System.out.println(" Magnitude: "+distInt);
            hm.put(qe, distInt);
        }
        HashMap<QuakeEntry, Integer> hm1 = GFG.sortByValue(hm);
        ArrayList<QuakeEntry> quakes = new ArrayList<>(hm1.keySet());

        System.out.println(howMany+" earth quakes sorted by magnitude ");
        for (int i = quakes.size()-1; i > quakes.size()-howMany-1; i--) {
            System.out.println(quakes.get(i));
        }
        return quakes;
    }


    public void dumpCSV(ArrayList<QuakeEntry> list){
		System.out.println("Latitude,Longitude,Magnitude,Info");
		for(QuakeEntry qe : list){
			System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
			                  qe.getLocation().getLatitude(),
			                  qe.getLocation().getLongitude(),
			                  qe.getMagnitude(),
			                  qe.getInfo());
	    }
		
	}
	

	
    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }
    
    public void closeToMe() {
        Location durham = new Location(35.988, -78.907);
        Location bridgeport = new Location(38.17, -118.82);

        filterByDistanceFrom(prepareQuakeList("nov20quakedata.atom"), 1000000, durham);
    }




    public ArrayList<QuakeEntry> testMatchAllFilter(ArrayList<QuakeEntry> quakeData, ArrayList<Filter> flist) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();

        for (QuakeEntry qe : quakeData) {
            for (Filter f : flist) {
                if (f.satisfies(qe)) {
                    answer.add(qe);
                    System.out.println(qe);
                }
            }
            System.out.println("In total " + answer.size() + " earthquakes who satisfy the filter list");

        }
        return answer;
    }


    public static void main(String [ ] args) {
        EarthQuakeClientWithFilter ec = new EarthQuakeClientWithFilter();

        //Location city = new Location(52.37, 4.895168);
        //Location city = new Location( -6.211,106.845);  //Jakarta
        //Location city = new Location( -6.211,106.845);  //San francisco
        //Location city = new Location( 35.42, 139.43);  //Tokyo
        Location city = new Location( 36.1314, -95.9372);  //Tulsa Oklahoma
        //Location city = new Location( 39.7392, -104.9903);  //Denver
        //Location city = new Location( 55.7308, 9.1153);  //Billund


        //ec.filterByMagnitude(ec.prepareQuakeList("nov20quakedata.atom"),6);
        //ec.filterByDistanceFrom(ec.prepareQuakeList("nov20quakedatasmall.atom"), 500000, city);
        //ec.closeToMe();
        //ec.filterByDepth(ec.prepareQuakeList("nov20quakedata.atom"), -10000, -8000);
        //ec.filterByPhrase(ec.prepareQuakeList("nov20quakedata.atom"), "Creek","any"); // start, end, any
        //ec.closestQuakes(ec.prepareQuakeList("nov20quakedatasmall.atom"), city, 10);
        //ec.getLargest(ec.prepareQuakeList("nov20quakedata.atom"), 51);

        Filter f1 = new MinMagFilter(0,2);
        Filter f2 = new DepthFilter(-100000,-10000);
        Filter f3 = new DistanceFilter(city,10000000);
        Filter f4 = new PhraseFilter("a","any");
        //ArrayList<QuakeEntry> largeQuakes = ec.filter(ec.prepareQuakeList("nov20quakedatasmall.atom"),f3);
        //ArrayList<QuakeEntry> largeQuakesCombi = ec.filter(largeQuakes,f4);

        Filter maf = new MatchAllFilter();

        ((MatchAllFilter) maf).addFilter(f1);
        ((MatchAllFilter) maf).addFilter(f2);
        ((MatchAllFilter) maf).addFilter(f4);




        //((MatchAllFilter) maf).addFilter(f4);


        ArrayList<QuakeEntry> combi = ec.filter(ec.prepareQuakeList("nov20quakedata.atom") ,maf);

    }

}