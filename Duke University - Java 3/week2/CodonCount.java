import edu.duke.*;
import java.util.*;
import java.util.ArrayList;
/**
 * Write a description of CononCount here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CodonCount {
    private HashMap<String,Integer> map = new HashMap<String,Integer>();
    public void buildCodonMap (int start, String dna){
        for(int i = start; i < dna.length()-2; i=i+3){
            String codon = dna.substring(i,i+3);
            //System.out.println(codon);
            if (map.keySet().contains(codon)){
                map.put(codon,map.get(codon)+1);
            }else{
                map.put(codon,1);
            }
        }

    }
    public void printMap(){
        int maxValue = 0;
        String mKey = "";
        int unique = 0;
        for (String name: map.keySet()){
            String key = name.toString();
            int value = map.get(name);  
            System.out.println(key + " " + value);  
            unique++;
             if(value > maxValue){
                maxValue = value;
                mKey = key;
            }
        } 
        
        System.out.println("Max Value " + maxValue + " is associated with " + mKey + " key");
        System.out.println("Num of unique values: " + unique);
    }

    public static void main(String[] args){

        CodonCount m = new CodonCount();
        //MysteryDna2
        m.buildCodonMap(0,"CAACCTTTAAAAGGAAGAAATCGCAGCAGCCCAGAACCAACTGCATAACATACAACCTTTAAAAGGAAGAAATCGCAGCAGCCCAGAACCAACTGCATAACATACAACCTTTAAAAGGAAGAAATCGCACCAGCCCAGAATCAACTGCATAACATACAAACTTTAAAAGGAAGAAATCTAACATACAACCTTTAAAAGGAAGAAATCGCACCAGCCCAGAATCAACTGCATAACATACAAACTTTAAAAGGAAGAAATCCAACCTTTAAAAGGAAGAAATCGCAGCAGCCCAGAACCAACTGCATAACATACAACCTTTAAAAGGAAGAAATCGCAGCAGCCCAGAACCAACTGCATAACATACAACCTTTAAAAGGAAGAAATCGCACCAGCCCAGAATCAACTGCATAACATACAAACTTTAAAAGGAAGAAATC");
        //m.buildCodonMap(0,"CGTTCAAGTTCAA");
        //m.buildCodonMap(2,"CGTTCAAGTTCAA");
        m.printMap();
    }
}

    
    


