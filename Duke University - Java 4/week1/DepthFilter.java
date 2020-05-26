public class DepthFilter implements Filter{
    private double lower;
    private double higher;

    public DepthFilter(double min, double max){
        lower = min;
        higher = max;
    }


    public boolean satisfies(QuakeEntry qe){
        return (qe.getDepth() > lower && qe.getDepth() < higher) ;
    }
}
