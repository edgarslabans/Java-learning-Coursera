import edu.duke.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        // returns the number of the points in the file
        int num = 0;
        for (Point currPt : s.getPoints()) {
            num++;
        }
        return num;
    }

    public double getAverageLength(Shape s) {
        // Put code here
        double averageLength = 0;
        double[] arrOfSides = new double [getNumPoints(s)];
        Point prevPt = s.getLastPoint();
        int t = 0;
        double totalPerlength=0;
        for (Point currPt : s.getPoints()) {
            double currDist = prevPt.distance(currPt);
            arrOfSides[t]=currDist;
            totalPerlength = totalPerlength+currDist;
            t++;
            prevPt = currPt;
        }
        averageLength = totalPerlength/t;
        System.out.println("All sides = " + Arrays.toString(arrOfSides));
        return averageLength;
    }

    public double getLargestSide(Shape s) {
        // Put code here
        double currentLongSide = 0;
        Point prevPt = s.getLastPoint();
        for (Point currPt : s.getPoints()) {
            double currDist = prevPt.distance(currPt);
            if(currDist > currentLongSide){
                currentLongSide = currDist;
            }
            prevPt = currPt;
        }
        return currentLongSide;
    }

    public double getLargestX(Shape s) {
        double currentLongX = 0;
        Point prevPt = s.getLastPoint();
        for (Point currPt : s.getPoints()) {
            double currX = Math.abs(currPt.getX());
            if(currX > Math.abs(prevPt.getX())){
                currentLongX = currX;
            }
            prevPt = currPt;
        }
        return currentLongX;
    }

    public double getLargestPerimeterMultipleFiles() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
             FileResource fr = new FileResource(f);
             Shape s = new Shape(fr);
             getPerimeter(s);
             
            }
            
            
        return 0.0;
    }

    public String getFileWithLargestPerimeter() {
        DirectoryResource dr = new DirectoryResource();
        File temp = null;    // replace this code
        double largestPerim = 0;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            testPerimeter(s);
            if(getPerimeter(s) > largestPerim){
                temp = f;
            }
            largestPerim = getPerimeter(s);
        }

        return temp.getName();
    }

    public void testPerimeter (Shape s) {
  
        double length = getPerimeter(s);
        System.out.println("====================================");
        System.out.println("Total perimeter length: " + length);
        System.out.println("Number of points: " + getNumPoints(s));
        System.out.println("Average side length: "+getAverageLength(s));
        System.out.println("Largest length: "+getLargestSide(s));
        System.out.println("Largest X: "+getLargestX(s));
        
      
    }
    
    public void testPerimeterMultipleFiles() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
             FileResource fr = new FileResource(f);
             Shape s = new Shape(fr);
             testPerimeter(s);
             
            }

    }

    public void testFileWithLargestPerimeter() {
        // Put code here
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();

        System.out.println("File with longest perimeter = "+ pr.getFileWithLargestPerimeter());
        
    }
}
