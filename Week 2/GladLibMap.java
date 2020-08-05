
/**
 * Week 2: GladLibMap
 * 
 * @author jrishabh99 
 * @version 05.08.2020
 */
import edu.duke.*;
import java.util.*;

public class GladLibMap {
    
    private HashMap<String,ArrayList<String>> myMap;
    
    private ArrayList<String> used;
    private ArrayList<String> categoriesUsed;
    private Random myRandom;
    
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";
    
    public GladLibMap(){
        
        categoriesUsed = new ArrayList<>();
        myMap = new HashMap<>();
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
        used = new ArrayList<>();
    }
    
    public GladLibMap(String source){
        
        myMap = new HashMap<>();
        initializeFromSource(source);
        myRandom = new Random();
        used = new ArrayList<>();
    }
    
    private void initializeFromSource(String source) {
        String[] categories = {"adjective", "noun", "color", "country", "name","animal", "timeframe", "verb", "fruit"};
        for(String category : categories)
        {
            ArrayList<String> words = readIt(source+"/"+category+".txt");
            myMap.put(category,words);
        }
    }
    
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }
    
    private String getSubstitute(String label) {
        if (label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        }
        else if(myMap.get(label)!=null)
        {
            if(categoriesUsed.indexOf(label)==-1)
                categoriesUsed.add(label);
            return randomFrom(myMap.get(label));
        }
        return "**UNKNOWN**";
    }
    
    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub;
        while(true)
        {
            sub =getSubstitute(w.substring(first+1,last));
            if(used.indexOf(sub)==-1)
            {
                used.add(sub);
                break;
            }
        }
        return prefix+sub+suffix;
    }
    
    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }
    
    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }
    
    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }
    private int totalWordsConsidered()
    {
        int count=0;
        for(String category : categoriesUsed)
        {
            count += myMap.get(category).size();
        }
        return count;
    }
    public void makeStory(){
        System.out.println("\n");
        used.clear();
        String story = fromTemplate("data/madtemplate2.txt");
        printOut(story, 60);
        System.out.println("\nCount of replaced words: " + used.size());
        System.out.println("Total words considered: "+totalWordsConsidered());
    }


}

