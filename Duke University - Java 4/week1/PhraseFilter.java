import java.util.*;
import java.text.*;
import edu.duke.*;

public class PhraseFilter implements Filter{

    ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();

     private String phrase;
     private String pos;


     public PhraseFilter(String inputPhrase, String  position){
            phrase = inputPhrase;
            pos = position;
    }

        public boolean satisfies(QuakeEntry qe) {
            String info = qe.getInfo();
            //info = info.toLowerCase();
            //phrase = phrase.toLowerCase();
            int indexPhr = info.indexOf(phrase, 0);
            boolean answer = false;
            if (indexPhr != -1 && indexPhr == 0 && pos == "start") {
                answer =  true;
            } else if (indexPhr != -1 && indexPhr == info.length() + 1 - phrase.length() && pos == "end") {
                answer =  true;
            } else if (indexPhr != -1) {
                answer =  true;
            }
            return answer;
        }



}
