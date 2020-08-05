
/**
 * Week 1:ObjectOrientedCaeserCipher.
 * 
 * @author jrishabh99
 * @version 05.08.2020
 */
public class ObjectOrientedCaeserCipher {
    private String alphabets;
    private String shiftedAlphabets;
    private int mainKey;
    ObjectOrientedCaeserCipher(int key)
    {
        mainKey=key;
        alphabets = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabets = alphabets.substring(key) +
                        alphabets.substring(0,key);
    }
    
    private char encryptCharacter(char ch)
    {
        int index = alphabets.indexOf(Character.toUpperCase(ch));
        if(index != -1)
        {
            char newChar = shiftedAlphabets.charAt(index);
            if(Character.isLowerCase(ch))
                newChar = Character.toLowerCase(newChar);
            return newChar;
        }
        return ch;
    }
    
    private String encrypt(String input)
    {
        StringBuilder sb = new StringBuilder(input);
        for(int i = 0;i < sb.length(); i++)
        {
            char newChar = encryptCharacter(sb.charAt(i));
            sb.setCharAt(i,newChar);
        }
        return sb.toString();
    }
    
    private String decrypt(String input)
    {
        ObjectOrientedCaeserCipher cc = new ObjectOrientedCaeserCipher(26-mainKey);
        return cc.encrypt(input);
    }
    public String getEncryptedMessage(String input)
    {
        return encrypt(input);
    }
    public String getDecryptedMessage(String input)
    {
        return decrypt(input);
    }
}

