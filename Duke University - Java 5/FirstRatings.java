import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class FirstRatings {

    public ArrayList<Movie> loadMovies(String filename) {
        // Loading movies and storing them in array list
        FileResource fr = new FileResource("data/" + filename + ".csv");
        CSVParser parser = fr.getCSVParser();

        ArrayList<Movie> movieList = new ArrayList<>();
        HashMap<String,Integer> moviesPerDirector = new HashMap<>();
        int comedyMovies = 0;
        int longMovies = 0;

        for (CSVRecord record : parser) {
            Movie m = new Movie(record.get("id"), record.get("title"), record.get("year"), record.get("genre"), record.get("director"),
                    record.get("country"), record.get("poster"), Integer.parseInt(record.get("minutes")));
            movieList.add(m);
            if (record.get("genre").contains("Comedy")) {
                comedyMovies++;
            }
            if (Integer.parseInt(record.get("minutes"))>150) {
                longMovies++;
            }
            if (moviesPerDirector.keySet().contains(record.get("director"))){
                moviesPerDirector.put(record.get("director"),moviesPerDirector.get(record.get("director"))+1);
            }else{
                moviesPerDirector.put(record.get("director"),1);
            }
            //System.out.println("single movie: " + m.toString());
        }
        System.out.println("Number of movies in the file: " + movieList.size() + " comedies: " + comedyMovies+ " >150: "+longMovies);
        System.out.println("Hasmap of directors: " + moviesPerDirector);
        maxValuesInHashmap(moviesPerDirector);
        return movieList;
    }
    public ArrayList<Rater>  loadRaters(String filename){
        FileResource fr = new FileResource("data/" + filename + ".csv");
        CSVParser parser = fr.getCSVParser();

        HashMap<String,ArrayList<Rating>> ratingsPerRater = new HashMap<>();
        HashMap<String,Integer> ratingsPerMovie = new HashMap<>();

        ArrayList<Rater> raterList = new ArrayList<>();
        int numMoviesForOneRater = 0;
        int ratingsForOneMovie = 0;


        for (CSVRecord record : parser) {
            ArrayList<Rating> ratingList = new ArrayList<>();
            Rating r = new Rating(record.get("movie_id"), Double.parseDouble(record.get("rating")));
            ratingList.add(r);


            // code to fill hashmap ratingsPerRater
            if (ratingsPerRater.keySet().contains(record.get("rater_id"))){
                ArrayList<Rating> existingRatingList = ratingsPerRater.get(record.get("rater_id"));
                existingRatingList.add(r);
                ratingsPerRater.put(record.get("rater_id"),existingRatingList);
            }else{
                ratingsPerRater.put(record.get("rater_id"),ratingList);
            }



            if (ratingsPerMovie.keySet().contains(record.get("movie_id"))){
                ratingsPerMovie.put(record.get("movie_id"),ratingsPerMovie.get(record.get("movie_id"))+1);
            }else{
                ratingsPerMovie.put(record.get("movie_id"),1);
            }

            // code to find number of rater who rated particular movie
            if (record.get("rater_id").equals("193")){
                numMoviesForOneRater++;
            }
            if (record.get("movie_id").equals("1798709")){
                ratingsForOneMovie++;
            }



        }

        // converting hashmap ratingsPerRater to the arrayList of Raters
        for (String id : ratingsPerRater.keySet()) {
            String key = id;
            ArrayList<Rating> ratings = ratingsPerRater.get(id);

            Rater rt = new Rater(id);
            for (Rating piece : ratings){
                rt.addRating(piece.getItem(),piece.getValue());
            }
            raterList.add(rt);
        }


        //System.out.println("Number of movies for particular rater: " + numMoviesForOneRater);
        System.out.println("Number of rating for particular movie: " + ratingsForOneMovie);

        //System.out.println("Hasmap of raters: " + ratingsPerRater);

        System.out.println("Unique movies: " +  ratingsPerMovie.size());
        System.out.println("Unique raters: " +  raterList.size());



        return raterList;
    }

public void maxValuesInHashmap(HashMap<String,Integer> map){

    int maxValue = 0;
    String mKey = "";
    for (String name: map.keySet()) {
        String key = name;
        int value = map.get(name);
        if (value > maxValue) {
            maxValue = value;
            mKey = key;
        }
    }
    System.out.println("Most frequent key: " + mKey + " appears " + maxValue + " times");
    }



    public static void main(String[] args){

        FirstRatings fr= new FirstRatings();
        //fr.loadMovies("ratedmoviesfull");  // ratedmoviesfull   //ratedmovies_short
        //fr.loadRaters("ratings");            //   ratings   ratings_short

        SecondRatings sr = new SecondRatings();
        //The Maze Runner
       sr.getAverageByID("0006414",3);
       // System.out.println("id->movie test " + sr.getTitle("1798709"));
        //sr.printAverageRatings(3);
    }

}


