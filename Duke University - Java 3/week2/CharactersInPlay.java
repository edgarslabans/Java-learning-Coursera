import edu.duke.*;
import java.util.*;
import java.util.ArrayList;
/**
 * Write a description of WordsInFiles here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CharactersInPlay {
    
    public void findCharactesrInPlays(int minAppearances, int maxAppearances){
        FileResource resource = new FileResource("data/errors.txt");

        HashMap<String,Integer> map = new HashMap<String,Integer>();
        
        for(String cont : resource.lines()){
            int start = 0;
            int end = 0;
            boolean startFound = false;
            boolean endFound = false;
            String playChar = "";
            if (cont.length() > 0 && cont.indexOf(".") != -1){
                //System.out.println("cont: " + cont.length());
                for (int i = 0; i < cont.length(); i++){
                    char c = cont.charAt(i);
                    if (c != ' ' && startFound == false){
                        start = cont.indexOf(c);
                        startFound = true;
                    }
                    if (c =='.'&& endFound == false){
                        end = cont.indexOf(c);
                        endFound = true;
                        break;
                    }
                    
                }
                playChar = cont.substring(start,end);
                //System.out.println("Playchar " + playChar + "_ start " + start + " end: "+end);
            }
            if (map.keySet().contains(playChar) && playChar !=""){
                map.put(playChar,map.get(playChar)+1);
            }else if (playChar !=""){
                map.put(playChar,1);
            }

        }
        
        for (String name: map.keySet()){
            String key = name.toString();
            int value = map.get(name);  
            
            if(value > minAppearances-1 && value < maxAppearances+1){
                System.out.println("Most frequent words:" + key + " appears " + value + " times");
            }
        }
    }
   
    public static void main(String[] args){

        CharactersInPlay m = new CharactersInPlay();

        m.findCharactesrInPlays(9,16);
        
    }
    
    
}
        
    
    

