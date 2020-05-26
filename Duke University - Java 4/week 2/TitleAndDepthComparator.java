import java.util.*;

public class TitleAndDepthComparator implements Comparator<QuakeEntry> {
    public int compare(QuakeEntry qe1, QuakeEntry qe2) {
        int compared = qe1.getInfo().compareTo(qe2.getInfo());

        if (compared==0){
            return Double.compare(qe2.getDepth(), qe1.getDepth());
        }

        //int compared = qe1.getInfo().compareTo(qe2.getInfo());
        return qe1.getInfo().compareTo(qe2.getInfo());

        /*

        if (magnitude < loc.getMagnitude()){
            return -1;
        }
        if (magnitude > loc.getMagnitude()){
            return 1;
        }
        return Double.compare(depth, loc.getDepth());


        return Double.compare(qe1.getInfo(), qe2.getMagnitude());
    }

         */

    }
}
