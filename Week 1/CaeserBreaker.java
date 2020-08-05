
/**
 * Week 1: Assignment 4: CaeserBreaker.
 * 
 * @author jrishabh99
 * @version 18.08.2020
 */
import edu.duke.*;
public class CaeserBreaker {

    private int[] countLetters(String message)
    {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int counts[]=new int[26];
        for(int i=0;i<message.length();i++)
        {
            char ch= Character.toLowerCase(message.charAt(i));
            int index=alphabet.indexOf(ch);
            if(index!=-1)
                counts[index]++;
        }
        return counts;
    }
    private int maxIndex(int values[])
    {
        int max=0,index=-1;
        for(int i=0;i<values.length;i++)
        {
            if(max<values[i])
            {
                max=values[i];
                index=i;
            }
        }
        return index;
    }
    private String decrypt(String encrypted)
    {   
        CaeserCipher cc= new CaeserCipher();
        int[] freqs=countLetters(encrypted);
        int index=maxIndex(freqs);
        int dKey=index-4;
        if(index<4)
            dKey=26-(4-index);
        return cc.getEncryptedMessage(encrypted,26-dKey);
    }
    public void testDecrpyt()
    {
        FileResource fr=new FileResource();
        CaeserCipher cc=new CaeserCipher();
        String encrypted = cc.getEncryptedMessage(fr.asString(),23);
        System.out.println("Encrypted Message: "+encrypted);
        System.out.println("Decrypted Message:"+decrypt(encrypted));
    }
    private String halfOfString(String message, int start)
    {
        StringBuilder sb=new StringBuilder();
        int count=0;
        for(int i=start;i<message.length();i++)
        {
            if(count%2==0)
                sb.append(message.charAt(i));
            count++;
        }
        return sb.toString();
    }
    
    private int getKey(String s)
    {
        int counts[]=countLetters(s);
        int index=maxIndex(counts);
        int dKey=index-4;
        if(index<4)
            dKey=26-(4-index);
        return dKey;
    }
    private String decryptTwoKeys(String encrypted)
    {
        int key1,key2;
        String firstHalf=halfOfString(encrypted,0);
        String secondHalf=halfOfString(encrypted,1);
        key1=getKey(firstHalf);
        key2=getKey(secondHalf);
        System.out.println("The keys are:"+key1+" and "+key2);
        CaeserCipher cc=new CaeserCipher();
        return cc.getTwoKeyEncryptedMessage(encrypted,26-key1,26-key2);
    }
    public void testDecryptTwoKey()
    {
        int key1=23,key2=2;
        FileResource fr=new FileResource();
        CaeserCipher cc=new CaeserCipher();
        System.out.println("Encrypted Message: "+fr.asString());
        System.out.println("Decrypted Message:"+decryptTwoKeys(fr.asString()));
    }
}
