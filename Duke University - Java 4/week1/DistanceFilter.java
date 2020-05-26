public class DistanceFilter implements Filter{
    private Location city;
    private double dist;

    public DistanceFilter(Location place, double meters){
        city = place;
        dist = meters;
    }

    public boolean satisfies(QuakeEntry qe){

        return (qe.getLocation().distanceTo(city) <= dist) ;
    }
}
