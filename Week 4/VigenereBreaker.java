import java.util.*;
import edu.duke.*;
import java.io.*;
public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder sliced = new StringBuilder();
        for(int i = whichSlice; i < message.length(); i+=totalSlices)
            sliced.append(message.charAt(i));
        return sliced.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        CaesarCracker cc = new CaesarCracker();
        for(int i = 0; i < klength; i++)
        {
            key[i] = cc.getKey(sliceString(encrypted,i,klength));
        }
        return key;
    }

    public void breakVigenere () {
        FileResource fr = new FileResource();
        String message = fr.asString();
        HashMap<String, HashSet<String>> dictionaries = new HashMap<>();
        DirectoryResource dictionaries_fr = new DirectoryResource();
        for(File f : dictionaries_fr.selectedFiles())
        {
            FileResource dictionary_fr = new FileResource(f);
            HashSet<String> dictionary = readDictionary(dictionary_fr);
            dictionaries.put(f.getName(),dictionary);
        }
        breakForAllLangs(message, dictionaries);
    }
    
    public HashSet<String> readDictionary(FileResource fr)
    {
        HashSet<String> words = new HashSet<>();
        for(String line : fr.lines())
        {
            line = line.toLowerCase();
            words.add(line);
        }
        return words;
    }
    
    public int countWords(String message, HashSet<String> dictionary)
    {
        String words[] = message.split("\\W+");
        int count=0;
        for(String word : words)
        {
            if(dictionary.contains(word.toLowerCase()))
                count++;
        }
        return count;
    }
    
    public String breakForLanguage(String encrypted, HashSet<String> dictionary)
    {
        char mostCommon = mostCommonCharIn(dictionary);
        int max = 0; 
        String decrypted_message = encrypted;
        for(int i = 1; i <= 100; i++)
        {
            int keys[] = tryKeyLength(encrypted, i, mostCommon);
            VigenereCipher vc = new VigenereCipher(keys);
            String decrypted = vc.decrypt(encrypted);
            int count = countWords(decrypted, dictionary);
            if(count > max)
            {
                max = count;
                decrypted_message = decrypted;
            }
        }
        return decrypted_message;
    }
    
    public char mostCommonCharIn(HashSet<String> dictionary)
    {
        char most_occured = ' ';
        int max = 0;
        HashMap<Character,Integer> map = new HashMap<>();
        for(String word : dictionary)
        {
            for(int i = 0;i < word.length(); i++)
            {
                char c = word.charAt(i);
                int count = 1;
                if(map.containsKey(c))
                    count += map.get(c);
                map.put(c,count);
                if(count > max)
                {
                    max = count;
                    most_occured = c;
                }
            }
        }
        return most_occured;
    }
    
    public void breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages)
    {
        int max = 0;
        String decrypted_message = encrypted;
        String language = "";
        for(String key : languages.keySet())
        {
            String decrypted = breakForLanguage(encrypted, languages.get(key));
            int count = countWords(decrypted, languages.get(key));
            if(count > max)
            {
                max = count;
                decrypted_message = decrypted;
                language = key;
            }
        }
        System.out.println("Language: "+language);
        System.out.println("Decrypted Message: "+decrypted_message);
    }
}
