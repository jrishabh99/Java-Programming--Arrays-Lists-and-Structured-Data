
/**
 * Coursera Course - Java Programming: Arrays, Lists, and Structured Data 
 * Week 1: Assignment 1
 * @author jrishabh99 
 * @version 05.08.2020
 */
public class WordPlay {
    
    private boolean isVowel(char ch)
    {
        ch = Character.toLowerCase(ch);
        if(ch =='a' || ch == 'e' || ch == 'i' 
        || ch == 'o' || ch == 'u')
            return true;
        else
            return false;
    }
    
    public void testIsVowel()
    {
        char ch = 'a';
        boolean vowel = isVowel(ch);
        if(vowel)
            System.out.println(ch + " is a vowel");
        else
            System.out.println(ch + " is not a vowel"); 
    }
    
    private String replaceVowel(String phrase, char ch)
    {
        StringBuilder sb = new StringBuilder(phrase);
        for(int i = 0;i < sb.length(); i++)
        {
            if(isVowel(sb.charAt(i)))
                sb.setCharAt(i,ch);
        }
        return sb.toString();
    }
    
    public void testReplaceVowel()
    {
        String phrase = "Hello World";
        char ch='*';
        String newPhrase = replaceVowel(phrase,ch);
        System.out.println("Originalphrase:" + phrase);
        System.out.println("New Phrase:" + newPhrase);
    }
    
    private String emphasize(String phrase,char ch)
    {
        StringBuilder sb=new StringBuilder(phrase);
        for(int i = 0;i < sb.length(); i++)
        {
            if(sb.charAt(i) == ch)
            {
                if(i % 2 == 0)
                    sb.setCharAt(i, '*');
                else
                    sb.setCharAt(i, '+');
            }
        }
        return sb.toString();
    }
    
    public void testEmphasize()
    {
        String phrase = "dna ctgaaactga";
        char ch = 'a';
        String emphasizedPhrase = emphasize(phrase, ch);
        System.out.println("Original Phrase: " + phrase);
        System.out.println("Emphasized Phrase: "+ emphasizedPhrase);
    }
}
