
/**
 * Write a description of CaesarBreaker here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;

public class CaesarBreaker {

    public void countLetters(String input){
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int maxCountA =0;
        int maxCountE =0;
        int maxCountO =0;
        
        int maxIndCountA =0;
        int maxIndCountE =0;
        int maxIndCountO =0;        
        

        for(int i = 0; i < 26; i++) {
            String encrypted = CaesarCipher.encrypt(input,i).toUpperCase();
            int [] frequencies =  new int[26];
            int index =0;
            for(char c : encrypted.toCharArray()) {
                if (Character.isLetter(c) != false){
                    int idx = alphabet.indexOf(c);
                    frequencies[idx]++;
                }
            }
            
            if (maxCountA < frequencies[0]){
                maxCountA = frequencies[0];
                maxIndCountA = i;
            } 
            if (maxCountE < frequencies[4]){
                maxCountE = frequencies[4];
                maxIndCountE = i;
            } 
            if (maxCountO < frequencies[14]){
                maxCountO = frequencies[14];
                maxIndCountO = i;
            }             
            //System.out.println("Frequency at : "+ i +"  "+Arrays.toString(frequencies));
        }
        System.out.println("MaxA : "+ maxIndCountA +" MaxE : "+ maxIndCountE +" MaxO : "+ maxIndCountO);
    }

    public void maxIndex(int cur, int fresh){

    }
    public void decrypt(String input, int key){
        //encrypt(input,key);
    }
    public void halfOfString(String input){
        String encrypted = input.toUpperCase();
        String encr1 = "";
        String encr2 = "";
        for(int i = 0; i < encrypted.length(); i++) {
        
            if (i%2 == 0){
                encr1 = encr1+encrypted.substring(i,i+1);
            } else {
                encr2 = encr2+encrypted.substring(i,i+1);
            }
        }    
        System.out.println("encr1 : "+ encr1 );
        System.out.println("encr2 : "+ encr2 );
        countLetters(encr1);
        countLetters(encr2);
        
    }
    
    public static void main(String[] args){

        CaesarBreaker m = new CaesarBreaker();
        //System.out.println("Cypher " + m.encrypt("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 15));
        
        //System.out.println("Replace vowels " + m.replaceVowels("Hello",'^'));
        //System.out.println("Emphasize " + m.emphasize("Mary Bella Abracadabra",'a'));
        //m.countLetters("Pi cddc qt xc iwt rdcutgtcrt gddb lxiw ndjg wpi dc udg p hjgegxht epgin. NTAA ADJS!");
        //m.halfOfString("Io iwjv jz dv bcm kjvammmikz mwju edbc twpz pvb wi awm v ncmxmqnm xvzog. TMGT TJCY!");
        
        

        
        FileResource resource = new FileResource("data/mysteryTwoKeysQuiz.txt");
        String res = resource.asString();
                //FileResource fr = new FileResource("brca1line.fa");      //brca1.fa
        //String strand = fr.asString();
        m.halfOfString(res);
        
        //m.halfOfString("Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!");
        
        //m.countLetters("aaa eee ooo");

    }
    
    
}
