
/**
 * Write a description of WordsInFiles here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;
import java.io.*;
public class WordsInFiles {
    private HashMap<String,ArrayList<String>> myMap;
    WordsInFiles()
    {
        myMap = new HashMap<>();
    }
    
    private void addWordsFromFile(File f)
    {
        FileResource fr = new FileResource(f);
        String fileName = f.getName(); 
        for(String word : fr.words())
        {
            if(myMap.get(word)==null)
            {
                myMap.put(word,new ArrayList<>());
            }
            ArrayList<String> files = myMap.get(word);
            if(files.indexOf(fileName) == -1)
            {
                files.add(fileName);
            }
            myMap.put(word,files);
        }
    }
    
    private void buildWordFileMap()
    {
        myMap.clear();
        DirectoryResource dr = new DirectoryResource();
        for(File f:dr.selectedFiles())
        {
            addWordsFromFile(f);
        }
    }
    
    private int maxNumber()
    {
        int max = 0;
        String max_word;
        for(String word : myMap.keySet())
        {
            int count = myMap.get(word).size();
            if(max < count)
            {
                max = count;
                max_word = word;
            }
        }
        return max;
    }
    
    private ArrayList<String> wordsInNumFiles(int number)
    {
        ArrayList<String> ans = new ArrayList<>();
        for(String word : myMap.keySet())
        {
            int count = myMap.get(word).size();
            if(count == number)
                ans.add(word);
        }
        return ans;
    }
    
    private void printFilesIn(String word)
    {
        ArrayList<String> fileNames = new ArrayList<>();
        if(myMap.containsKey(word))
            fileNames = myMap.get(word);
        for(String file: fileNames)
        {
            System.out.println(file);
        }
    }
    
    public void tester()
    {
        buildWordFileMap();
        int max = maxNumber();
        System.out.println("Maximum number of files any word appears in :"+max);
        ArrayList<String> words = wordsInNumFiles(max);
        for(String word : words)
        {
            System.out.println(word+" is in files:");
            printFilesIn(word);
        }
    }
}
