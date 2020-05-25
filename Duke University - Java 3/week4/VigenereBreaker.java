import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
    
    private HashMap<String,HashSet<String>> langMap = new HashMap<String,HashSet<String>>();
        
    private String[] allLanguages = {"English", "Danish", "Dutch", "French", "German", "Italian", "Portuguese", "Spanish"};
    private char[] commonCharsAll = {'e', 'e', 'e', 'e', 'e', 'a', 'a', 'a'};
    
    //private String[] allLanguages = {"English", "Danish", "Dutch"};
    //private char[] commonCharsAll = {'e', 'e', 'e'};
    
    public String sliceString(String message, int whichSlice, int totalSlices) {
        String output = "";
        for (int i = whichSlice; i < message.length(); i = i+totalSlices){
            char ch1 = message.charAt(i);
            output = output+ch1;
       }
        return output;
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        
        for (int i = 0; i < klength; i++){
            String slice  = sliceString(encrypted, i, klength);
            CaesarCracker cc = new CaesarCracker(mostCommon);
            int key1 = cc.getKey(slice);
            key[i] = key1;
        }
        return key;
    }
    
    public int[] tryKeyLength2(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        
        for (int i = 0; i < klength; i++){
            String slice  = sliceString(encrypted, i, klength);
            CaesarCracker cc = new CaesarCracker(mostCommon);
            int key1 = cc.getKey(slice);
            key[i] = key1;
        }
        return key;
    }
    
    
    
    
    public String breakVigenere (String file,int keylength,char commonChar) {
        FileResource resource = new FileResource("data/"+file);
        String res = resource.asString();//.substring(100000,105000);       /// <---- !!!!!!!!!!!! .substring(0,1000)
        
        int[] keyss = tryKeyLength(res, keylength, commonChar);
        
        VigenereCipher vc = new VigenereCipher(keyss);
        String decyptedMessage = vc.decrypt(res);
        //System.out.println("Decrypted message SUBSTRING 0-300: ");
        //System.out.println(decyptedMessage); // .substring(0,50)
        //System.out.println("keys used: " + Arrays.toString(keyss));
        
        return decyptedMessage;
        
    }
    
    public String breakForLanguage(String file,HashSet<String> dictionary,char commonChar){
        int maxMatch = 0;
        String decr = "";
        int keyLen = 0;

        for (int i = 5; i < 6; i++){
            String decrStr = breakVigenere (file,i,commonChar);
            int validWords = countWords(decrStr,dictionary);
            if (validWords > maxMatch){
                maxMatch = validWords;
                decr = decrStr;
                keyLen = i;
            }
        }
        
        //System.out.println("Decrypted message by guessing key length. (SUBSTRING 0-300): ");
        System.out.println(decr.substring(0,100));
        System.out.println("Valid key length is: "+keyLen+" Valid words found: "+maxMatch);
        
        return decr;
    }
    
    public void breakForAllLangs(String file){
        readAllDictionaries();
        
        int maxMatch = 0;
        String decr = "";
        int keyLen = 0;
        String trueLang = "--";
        char com = 'x';
        for (String lang: langMap.keySet()){
            String key = lang;
            HashSet<String> language1 = langMap.get(lang); 
            char commonCh = commonCharsAll[Arrays.asList(allLanguages).indexOf(lang)];
            String output = breakForLanguage(file,language1,commonCh);
            output = output.toLowerCase();
            int validWords = countWords(output,language1);
            if (validWords > maxMatch){
                maxMatch = validWords;
                decr = output;
                trueLang = key;
                com = commonCh;
            }            
        } 
        System.out.println("Decrypted message by guessing key length and language. (SUBSTRING 0-300): ");
        System.out.println(decr.substring(0,200));
        System.out.println("Most valid words found: "+maxMatch+" are in "+trueLang+" with common char: "+com);
    }
    
    
    
    public HashSet<String> readDictionary (String language){
        
        FileResource resource = new FileResource("dictionaries/"+language);
        
        HashSet<String> hs = new HashSet<String>();  
        
        for(String cont : resource.lines()){
            hs.add(cont); 
        }
        
        System.out.println("Hash set with "+language+" dictionary created. In total "+hs.size()+" words added"); 
        return hs;
        
        /*
        HashSet<String> dictionary = new HashSet<String>();
        for (String word : resource.lines()){
            if(!dictionary.contains(word.toLowerCase())){dictionary.add(word.toLowerCase());}}
        return dictionary;
    
        */
        
        
        
        
    }
    
    
    public int countWords (String message,HashSet<String> dictionary){
        int validWords =0;
        for(String word : message.split("\\W+")){
            if (dictionary.contains(word.toLowerCase())){
                validWords++;
            }
        }
        //System.out.println("In total "+validWords+" valid words found."); 
        return validWords;
    }
    
    public char mostCommonCharIn (HashSet<String> dictionary){
        HashMap<Character,Integer> map = new HashMap<Character,Integer>();
        
        int maxValue = 0;
        char mKey = ' ';
        
        for(String item : dictionary){
            for(char c : item.toCharArray()) {
                if (map.keySet().contains(c) ){
                    map.put(c,map.get(c)+1);
                }else {
                    map.put(c,1);
                }
            }
        }
        for (char letter: map.keySet()){
            char key = letter;
            int value = map.get(letter);  

             if(value > maxValue){
                maxValue = value;
                mKey = key;
            }
        } 
        
        System.out.println("Most common char in  "+"<lang>"+" is "+mKey+ ". Apperarances: "+maxValue); 
        return mKey;
    }
    
    public void readAllDictionaries(){
        for (String language : allLanguages){
            langMap.put(language,readDictionary(language));
        }    
    }
    
    
    public static void main(String[] args){
        VigenereBreaker vb = new VigenereBreaker();
        //System.out.println("Some values: " + vb.sliceString("abcdefghijklm", 3, 4));
        
        //FileResource resource = new FileResource("data/athens_keyflute.txt");
        //String res = resource.asString();
        
        //vb.breakVigenere("athens_keyflute.txt",5);
        //vb.breakVigenere("secretmessage1.txt",4);

        //vb.breakForLanguage("secretmessage2.txt",vb.readDictionary("English"));
        //vb.breakForLanguage("titus-small_key5.txt",vb.readDictionary("English"));
        
        
        
        /*
        char[] commonChars = new char[8];
        for (String language : allLanguages){
            char op = vb.mostCommonCharIn(vb.readDictionary(language));
            System.out.println("Most common char in  "+language + " is "+op);
            commonChars[Arrays.asList(allLanguages).indexOf(language)]= op;
        }
        System.out.println("All  "+ Arrays.toString(commonChars));
        */
       
       
       //{"English", "Danish", "Dutch", "French", "German", "Italian", "Portuguese", "Spanish"};

       
       //vb.breakVigenere("aida_short_ansi.txt",5,'a');
       vb.breakForLanguage("aida_keyverdi.txt",vb.readDictionary("Italian"),'e');
       //vb.breakForAllLangs("secretmessage2.txt");
    }
    
    
}
