
/**
 * Coursera Course - Java Programming: Arrays, Lists, and Structured Data 
 * Week 1: Assignment 2
 * @author jrishabh99 
 * @version 05.08.2020
 */
import edu.duke.*;
public class CaeserCipher {
    
    // Function to return the character ch
    // encrypted by key 
    private char encryptCharacter(char ch, int key)
    {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedAlphabet = alphabet.substring(key) + 
                                alphabet.substring(0,key);
        int index = alphabet.indexOf(Character.toUpperCase(ch));
        if(index != -1)
        {
            char newChar = shiftedAlphabet.charAt(index);
            if(Character.isLowerCase(ch))
                newChar = Character.toLowerCase(newChar);
            return newChar;
        }
        return ch;
    }
    
    private String encrypt(String input, int key)
    {
        StringBuilder sb = new StringBuilder(input);
        for(int i = 0;i < sb.length(); i++)
        {
            char newChar = encryptCharacter(sb.charAt(i), key);
            sb.setCharAt(i,newChar);
        }
        return sb.toString();
    }
    public String getEncryptedMessage(String input,int key)
    {
        return encrypt(input,key);
    }
    private String encryptTwoKeys(String input, int key1, int key2)
    {   
        StringBuilder sb = new StringBuilder(input);
        for(int i = 0;i < sb.length(); i++)
        {
            char newChar = encryptCharacter(sb.charAt(i), i % 2 == 0 ? key1 : key2);
            sb.setCharAt(i,newChar);
        }
        return sb.toString();
    }
    public String getTwoKeyEncryptedMessage(String message,int key1,int key2)
    {
        return encryptTwoKeys(message,key1,key2);
    }
    public void testCaeser()
    {
        int key1 = 23, key2 = 17;
        FileResource fr = new FileResource();
        String message = fr.asString();
        String encrypted = encrypt(message, key1);
        System.out.println("key is "+ key1 + "\n" + encrypted);
        String doubleEncrypt = encryptTwoKeys(message,key1,key2);
        System.out.println("keys are "+key1+" and "+key2 +"\n"+doubleEncrypt);
    }
}
