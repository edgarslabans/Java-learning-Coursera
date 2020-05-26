import java.util.*;

public class TitleLastAndMagnitudeComparator  implements Comparator<QuakeEntry> {
    public int compare(QuakeEntry qe1, QuakeEntry qe2) {

        String qe1LastWord = findLastWord(qe1);
        String qe2LastWord = findLastWord(qe2);


        int compared = qe1LastWord.compareTo(qe2LastWord);

        if (compared==0){
            return Double.compare(qe1.getMagnitude(), qe2.getMagnitude());
        }


        return qe1LastWord.compareTo(qe2LastWord);

    }

    public String findLastWord(QuakeEntry qe1) {
        String info = qe1.getInfo();
        int wordStartInd = 0;
        for (int i=info.length()-1; i>0; i--){
            char c = info.charAt(i);
            if (c == ' '){
                wordStartInd = i+1;
                break;
            }
        }
        String last = info.substring(wordStartInd,info.length());
        //System.out.println("Last word" + last);
        return last;
    }


}
