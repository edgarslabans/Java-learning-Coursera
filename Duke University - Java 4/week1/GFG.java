
import java.util.*;
import java.lang.*;


public class GFG {

    // function to sort hashmap by values
    public static HashMap<QuakeEntry, Integer> sortByValue(HashMap<QuakeEntry, Integer> hm)
    {
        // Create a list from elements of HashMap
        List<Map.Entry<QuakeEntry, Integer> > list =
                new LinkedList<Map.Entry<QuakeEntry, Integer> >(hm.entrySet());

        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<QuakeEntry, Integer> >() {
            public int compare(Map.Entry<QuakeEntry, Integer> o1,
                               Map.Entry<QuakeEntry, Integer> o2)
            {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        // put data from sorted list to hashmap
        HashMap<QuakeEntry, Integer> temp = new LinkedHashMap<QuakeEntry, Integer>();
        for (Map.Entry<QuakeEntry, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }

    // Driver Code


}
