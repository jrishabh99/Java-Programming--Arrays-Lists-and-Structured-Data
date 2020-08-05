
/**
 * Week 2: WordFrequencies
 * 
 * @author jrishabh99 
 * @version 05.08.2020
 */
import java.util.ArrayList;
import edu.duke.*;
public class WordFrequencies {
    
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    
    public WordFrequencies()
    {
        myWords = new ArrayList<>();
        myFreqs = new ArrayList<>();
    }
    
    private void findUnique()
    {
        myWords.clear();
        myFreqs.clear();
        FileResource fr = new FileResource();
        for(String word : fr.words())
        {
            word=word.toLowerCase();
            int index = myWords.indexOf(word);
            if(index == -1)
            {
                myWords.add(word);
                myFreqs.add(1);
            }
            else
            {
                myFreqs.set(index, myFreqs.get(index) + 1);
            }
        }
    }
    
    private int findIndexOfMax()
    {
        int index = -1, max = 0;
        for(int i = 0; i < myFreqs.size(); i++)
        {
            if(max < myFreqs.get(i))
            {
                index = i;
                max = myFreqs.get(i);
            }
        }
        return index;
    }
    
    public void tester()
    {
        findUnique();
        System.out.println("Number of unique Words: " + myWords.size());
        for(int i = 0; i < myWords.size(); i++)
        {
            System.out.println(myFreqs.get(i) + " " + myWords.get(i));
        }
        int index = findIndexOfMax();
        System.out.println("The word that occurs most often and its count are: "+myWords.get(index)+" "+myFreqs.get(index));
    }
    
}
