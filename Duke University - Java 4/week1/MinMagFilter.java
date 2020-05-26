import java.util.*;
import java.text.*;
import edu.duke.*;


public class MinMagFilter implements Filter{
    private double lower;
    private double higher;

    public MinMagFilter(double min, double max){
        lower = min;
        higher = max;
    }


    public boolean satisfies(QuakeEntry qe){
        return (qe.getMagnitude() > lower && qe.getMagnitude() < higher) ;
    }
}
