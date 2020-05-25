import edu.duke.*;
import java.util.*;

public class GladLibMap {
    
    private String[] categories = {"adjective","noun","color","country","name","animal","timelist"};
    
    private HashMap<String,ArrayList<String> > myMap = new HashMap<String,ArrayList<String>>();
    
    private Random myRandom;
    
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";
    
    public GladLibMap(){
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
    }
    

    
    private void initializeFromSource(String source) {
        for (String name: myMap.keySet()){
            ArrayList<String> arrList = readIt(source+"/"+name+".txt");
            for(int i=0;i<arrList.size();i++){
                System.out.println(arrList.get(i));
            }
            myMap.put(name,arrList);
        }
    }
    

    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
	return source.get(index);
    }
    private String getSubstitute(String label) {
        if (label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        }
        
        return randomFrom (myMap.get(label));
    }
    
    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub = getSubstitute(w.substring(first+1,last));
        return prefix+sub+suffix;
    }
    
    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }
    
    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }
    
    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }
    
    public void makeStory(){
        printMap();
        System.out.println("\n");
        String story = fromTemplate("data/madtemplate.txt");
        printOut(story, 60);
    }
    
    public void printMap(){
        for (String name: myMap.keySet()){
            String key = name.toString();
            ArrayList<String> values = myMap.get(name); 

            System.out.println(key + " ______________"); 
            for(int i = 0; i < values.size(); i++) {   
                System.out.println(values.get(i));
            } 
        } 
    }
    
    
    


}
