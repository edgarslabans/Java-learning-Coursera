import edu.duke.*;
import java.util.*;
import java.util.ArrayList;
/**
 * Write a description of WordsInFiles here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordsInFiles {
    private HashMap<String,ArrayList<String> > wordMap = new HashMap<String,ArrayList<String>>();
    public void addWordsFromFile(){
        String[] fileNames = new String[] {"caesar.txt","hamlet.txt","likeit.txt","macbeth.txt","romeo.txt","confucius.txt","errors.txt"};
        //String[] fileNames = new String[] {"caesar.txt","hamlet.txt","likeit.txt","macbeth.txt","romeo.txt"};
        for(int i =0; i < fileNames.length; i++){
            FileResource resource = new FileResource("data/"+fileNames[i]);
            ArrayList<String> wordsInFile = new ArrayList<String>();
            for(String word : resource.words()){
                wordsInFile.add(word);
            }
            wordMap.put(fileNames[i],wordsInFile);
        }    
    }
    
    public void maxNumber (String word){   // finds how many time word appears between all the files
        int num = 0;   //number of times word appears in all files
        ArrayList<String> titles = new ArrayList<String>(); // filenames where the word appears
        for (String name: wordMap.keySet()){
            String key = name.toString();
            ArrayList<String> values = wordMap.get(name); 
            for(int i = 0; i < values.size(); i++) {   
                String listItem = values.get(i);
                if (listItem.equals(word)){
                num++;
                titles.add(key);
                }
            } 
        } 
        System.out.println("The word "+ word + " appears in all files " + num+" times");
        System.out.println("The filenames containing it are: ");
        for(int i = 0; i < titles.size(); i++) {   
                System.out.println(titles.get(i));
        } 
    }    
    
    public void wordsInNumFiles (int num){   //prints the words which appear in cetain number of files
        HashMap<String,Integer> allWordsFreq = new HashMap<String,Integer>();
        ArrayList<String> words = new ArrayList<String>(); // filenames where the word appears

        for (String name: wordMap.keySet()){
            String key = name.toString();
            ArrayList<String> values = wordMap.get(name); 
            values = removeDuplicates(values); 
            for(int i = 0; i < values.size(); i++) {   
                String listItem = values.get(i);
                if (allWordsFreq.keySet().contains(listItem)){
                    allWordsFreq.put(listItem,allWordsFreq.get(listItem)+1);
                }else{
                    allWordsFreq.put(listItem,1);
                }
            } 
        } 
        System.out.println("words appearing in "+num+" files:");
        int total = 0;
        for (String name: allWordsFreq.keySet()){
            String key = name.toString();
            int value = allWordsFreq.get(name);  
            if(value == num){
                System.out.println(key + " " + value);
                total++;
            }
        } 
        System.out.println("In total "+num+" files contain "+total+" unique words");
        
    }
    
    public ArrayList<String> removeDuplicates(ArrayList<String> arrList){
        ArrayList<String> withoutDuplicates = new ArrayList<String>();
        
        for(int i = 0; i < arrList.size(); i++) {  
            String listItem = arrList.get(i);
            if (withoutDuplicates.contains(listItem)==false){
                withoutDuplicates.add(listItem);
            }
        } 
        return withoutDuplicates;
    }
    
    
    public void findUniqueWords(){
        FileResource resource = new FileResource("data/errors.txt");
        int numUnique = 0;
        int maxValue = 0;
        String mKey = "";
        HashMap<String,Integer> map = new HashMap<String,Integer>();
        
        for(String word : resource.words()){
            word = word.toLowerCase();
            if (map.keySet().contains(word)){
                map.put(word,map.get(word)+1);
            }else{
                map.put(word,1);
            }
        }
        
        for (String name: map.keySet()){
            String key = name.toString();
            int value = map.get(name);  
            numUnique++;
            if(value > maxValue){
                maxValue = value;
                mKey = key;
            }
        }
        System.out.println("The file contain "+numUnique+" unique words");
        System.out.println("Most frequent word /" + mKey + "/ appears " + maxValue + " times");
    }
    
    public void printMap(){
        for (String name: wordMap.keySet()){
            String key = name.toString();
            ArrayList<String> values = wordMap.get(name); 

            System.out.println(key + " ______________"); 
            for(int i = 0; i < values.size(); i++) {   
                System.out.println(values.get(i));
            } 
       } 
    }
    public static void main(String[] args){

        WordsInFiles m = new WordsInFiles();
        m.addWordsFromFile();

        //m.printMap();
        m.maxNumber("tree");
        //m.wordsInNumFiles(1);
        //m.findUniqueWords();
        //m.wordsInNumFiles(4);
        
    }
    
    
}
        
    
    

