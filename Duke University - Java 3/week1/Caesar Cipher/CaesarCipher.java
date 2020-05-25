import edu.duke.*;

public class CaesarCipher {
    public String encrypt(String input, int key) {
        //Make a StringBuilder with message (encrypted)
        StringBuilder encrypted = new StringBuilder(input);
        //Write down the alphabet
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        //Compute the shifted alphabet
        String shiftedAlphabet = alphabet.substring(key)+ alphabet.substring(0,key);
        String shiftedAlphabet_small = shiftedAlphabet.toLowerCase(); 
        //Count from 0 to < length of encrypted, (call it i)
        for(int i = 0; i < encrypted.length(); i++) {
            //Look at the ith character of encrypted (call it currChar)
            char currChar = encrypted.charAt(i);
            //Find the index of currChar in the alphabet (call it idx)
            int idx = alphabet.indexOf(currChar);
            
            if(idx == -1 ){
                idx = alphabet.toLowerCase().indexOf( Character.toLowerCase(currChar));
            }
            //If currChar is in the alphabet
            if(idx != -1 && Character.isUpperCase(currChar)){
                //Get the idxth character of shiftedAlphabet (newChar)
                char newChar = shiftedAlphabet.charAt(idx);
                //Replace the ith character of encrypted with newChar
                encrypted.setCharAt(i, newChar);
            } else if(idx != -1 && Character.isLowerCase(currChar)){
                //Get the idxth character of shiftedAlphabet (newChar)
                char newChar = shiftedAlphabet_small.charAt(idx);
                //Replace the ith character of encrypted with newChar
                encrypted.setCharAt(i, newChar);
                //System.out.println("index + curr char " +idx+currChar+Character.isLowerCase(currChar);
            }
            //Otherwise: do nothing
        }
        return encrypted.toString();
    }
    

    public String encryptTwoKeys (String input, int key, int key2) {
        //Make a StringBuilder with message (encrypted)
        StringBuilder encrypted = new StringBuilder(input);
        //Write down the alphabet
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        //Compute the shifted alphabet
        String shiftedAlphabet = alphabet.substring(key)+ alphabet.substring(0,key);
        String shiftedAlphabet_small = shiftedAlphabet.toLowerCase(); 
        
        String shiftedAlphabet2 = alphabet.substring(key2)+ alphabet.substring(0,key2);
        String shiftedAlphabet_small2 = shiftedAlphabet2.toLowerCase(); 
        
        
        //Count from 0 to < length of encrypted, (call it i)
        for(int i = 0; i < encrypted.length(); i++) {
            //Look at the ith character of encrypted (call it currChar)
            char currChar = encrypted.charAt(i);
            //Find the index of currChar in the alphabet (call it idx)
            int idx = alphabet.indexOf(currChar);
            
            if(idx == -1 ){
                idx = alphabet.toLowerCase().indexOf( Character.toLowerCase(currChar));
            }
            //If currChar is in the alphabet
            if(idx != -1 && Character.isUpperCase(currChar) && i%2==0 ){
                //Get the idxth character of shiftedAlphabet (newChar)
                char newChar = shiftedAlphabet.charAt(idx);
                //Replace the ith character of encrypted with newChar
                encrypted.setCharAt(i, newChar);
            } else if(idx != -1 && Character.isLowerCase(currChar) && i%2==0 ){
                //Get the idxth character of shiftedAlphabet (newChar)
                char newChar = shiftedAlphabet_small.charAt(idx);
                //Replace the ith character of encrypted with newChar
                encrypted.setCharAt(i, newChar);
                //System.out.println("index + curr char " +idx+currChar+Character.isLowerCase(currChar);
            }
            else if(idx != -1 && Character.isUpperCase(currChar) && i%2 !=0 ){
                //Get the idxth character of shiftedAlphabet (newChar)
                char newChar = shiftedAlphabet2.charAt(idx);
                //Replace the ith character of encrypted with newChar
                encrypted.setCharAt(i, newChar);
            } else if(idx != -1 && Character.isLowerCase(currChar) && i%2 !=0 ){
                //Get the idxth character of shiftedAlphabet (newChar)
                char newChar = shiftedAlphabet_small2.charAt(idx);
                //Replace the ith character of encrypted with newChar
                encrypted.setCharAt(i, newChar);
                //System.out.println("index + curr char " +idx+currChar+Character.isLowerCase(currChar);
            }

            //Otherwise: do nothing
        }
        return encrypted.toString();
    }
    
    
    public boolean isVowel(char ch){
        ch = Character.toLowerCase(ch);
        String vowels = "aeiou";
        int idx = vowels.indexOf(ch);
        if (idx != -1){
            return true;
        }else{
            return false;
        }
    }
    
    public String replaceVowels (String phrase, char ch){
        StringBuilder changed = new StringBuilder(phrase);
        
        for(int i = 0; i < phrase.length(); i++) {   
            char currChar = changed.charAt(i);
            if (isVowel(currChar)){
                changed.setCharAt(i, ch);
            }
        }
        return changed.toString();
    }
    public String emphasize (String phrase, char ch){
        StringBuilder changed = new StringBuilder(phrase);
        
        for(int i = 0; i < phrase.length(); i++) {   
            char currChar = changed.charAt(i);
            if (isVowel(currChar) && i%2==0 && currChar == ch){
                changed.setCharAt(i, '*');
            }else if (isVowel(currChar) && i%2!=0 && currChar == ch){
                changed.setCharAt(i, '+');
            }
        }
        return changed.toString();
    }
    public void testCaesar() {
        int key = 17;
        FileResource fr = new FileResource();
        String message = fr.asString();
        String encrypted = encrypt(message, key);
        System.out.println(encrypted);
        String decrypted = encrypt(encrypted, 26-key);
        System.out.println(decrypted);
    }
    public static void main(String[] args){

        CaesarCipher m = new CaesarCipher();
        //System.out.println("Replace vowels " + m.replaceVowels("Hello",'^'));
        //System.out.println("Emphasize " + m.emphasize("Mary Bella Abracadabra",'a'));
        System.out.println("Cypher " + m.encrypt("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 15));
        System.out.println("Cypher " + m.encryptTwoKeys("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 8,21));
        
        
    }
    
    
}

