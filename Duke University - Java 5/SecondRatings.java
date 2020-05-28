
/**
 * Write a description of SecondRatings here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;

    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull", "ratings"); //
    }
    public SecondRatings(String movieFile, String ratingfile) {
        FirstRatings fr= new FirstRatings();

        myMovies = fr.loadMovies(movieFile);
        myRaters =  fr.loadRaters(ratingfile);
    }

    public int getMovieSize(ArrayList<Movie> movies){
        return movies.size();
    }
    public int getRaterSize(ArrayList<Rater> raters){
        return raters.size();
    }
    public double getAverageByID(String id, int minRaters){
        double sum = 0;
        double numOfraters = 0;
        for (int k=0; k < myRaters.size(); k++) {
            double ratingValue = myRaters.get(k).getRating(id);            //"1798709"
            if ( ratingValue !=-1){
                sum = sum +ratingValue;
                numOfraters++;
            }
        }
        // return 0 if not enough raters
        if (numOfraters < minRaters){
            return 0;
        }
        System.out.println("average rating: " + sum/numOfraters );
        return sum/numOfraters;
    }

    public ArrayList<Rating> getAverageRatings (int minRaters){
        ArrayList<Rating> avgRatingList = new ArrayList<>();
        for (int k=0; k < myMovies.size(); k++) {
            double avrRating = getAverageByID(myMovies.get(k).getID(),minRaters);
            if (avrRating != 0) {
                Rating tempR = new Rating(myMovies.get(k).getID(), avrRating);
                avgRatingList.add(tempR);
            }
        }
        return avgRatingList;
    }

    public String getTitle(String id){
        for (Movie myMovie : myMovies)
            if (myMovie.getID().equals(id)) {
                return myMovie.getTitle();
            }
        return ("Could not find a movie with such name");
    }

    public void printAverageRatings(int minRaters) {
        ArrayList<Rating> inputList = getAverageRatings(minRaters);
        Collections.sort(inputList);
        System.out.println("=========== movies by rating =============");
        for (Rating avgRating : inputList){
            System.out.println(avgRating.getValue()+"  " + getTitle(avgRating.getItem()));
        }
    }
}
