
/**
 * Write a description of TestObjectOrientedCaeserCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class TestObjectOrientedCaeserCipher {
    
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
    
    private String breakCaeserCipher(String input)
    {
        int[] freqs=countLetters(input);
        int index=maxIndex(freqs);
        int dKey=index-4;
        if(index<4)
            dKey=26-(4-index);
        System.out.println("The key is:"+dKey);
        ObjectOrientedCaeserCipher cc=new ObjectOrientedCaeserCipher(26-dKey);
        return cc.getEncryptedMessage(input);
    }
    public void simpleTests()
    {
        FileResource fr=new FileResource();
        ObjectOrientedCaeserCipher cc=new ObjectOrientedCaeserCipher(18);
        String encrypted=cc.getEncryptedMessage(fr.asString());
        System.out.println("Encrypted Message: "+encrypted);
        String decrypted=cc.getDecryptedMessage(encrypted);
        System.out.println("Decrypted Message: "+decrypted);
        String automaticDecryptedMessage = breakCaeserCipher(encrypted);
        System.out.println("Automatic Decrypted Message: "+automaticDecryptedMessage);
    }
}
