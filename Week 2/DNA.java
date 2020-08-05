
/**
 * Week 2: DNA
 * 
 * @author jrishabh99
 * @version 05.08.2020
 */
import java.util.*;
import edu.duke.*;
public class DNA {

    private HashMap<String,Integer> codonMap;
    DNA()
    {
        codonMap = new HashMap<>();
    }
    private void buildCodonMap(int start, String dna)
    {
        codonMap.clear();
        for(int i = start; i< dna.length() - 2; i += 3)
        {
            String codon = ""+dna.charAt(i) + dna.charAt(i + 1) + dna.charAt(i + 2);
            codonMap.put(codon, codonMap.getOrDefault(codon, 0) + 1);
        }
    }
    private String getMostCommonCodon()
    {
        int max = -1;
        String ans = "";
        for(String codon : codonMap.keySet())
        {
            int count = codonMap.get(codon);
            if(count > max)
            {
                max = count;
                ans = codon;
            }
        }
        return ans;
    }
    private void printCodonCounts(int start, int end)
    {
        for(String codon : codonMap.keySet())
        {
            int count = codonMap.get(codon);
            if(count >= start && count <= end)
            {
                System.out.println(codon + ": " + count);
            }
        }
    }
    public void tester()
    {
        FileResource fr=new FileResource();
        String dna = fr.asString();
        dna = dna.trim();
        dna = dna.toUpperCase();
        for(int i = 0; i <= 2; i++)
        {
            System.out.println("Reading Frame: " + i);
            buildCodonMap(i,dna);
            System.out.println("Total number of unique codons: "+codonMap.size());
            String common = getMostCommonCodon();
            int count = codonMap.getOrDefault(common,0);
            System.out.println("Most Common Codon- "+ common+": "+count);
            printCodonCounts(1,5);
        }
    }
}
