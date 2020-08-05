
/**
 * Coursera Course - Java Programming: Arrays, Lists, and Structured Data 
 * Week 1: Assignment 3 - Breaking the Caeser Cipher
 * @author jrishabh99 
 * @version 05.08.2020
 */
import edu.duke.*;
public class WordLengths {
    private void countWordLengths(FileResource resource,int[] counts)
    {
        for(String word:resource.words())
        {
            int length=word.length();
            if(!Character.isLetter(word.charAt(0)))
                length--;
            if(!Character.isLetter(word.charAt(word.length()-1)))
                length--;
            if(length>=30)
                length=30;
            counts[length]++;
        }
    }
    
    public void testCountWordLengths()
    {
        FileResource fr=new FileResource();
        int counts[]=new int[31];
        countWordLengths(fr,counts);
        System.out.println(indexOfMax(counts));
    }
    
    private int indexOfMax(int values[])
    {
        int index=0,max=0;
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
}
