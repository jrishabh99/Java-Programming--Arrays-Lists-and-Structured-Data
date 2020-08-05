
/**
 * Week 2:CharactersInPlay
 * 
 * @author jrishabh99
 * @version 05.08.2020
 */
import java.util.*;
import edu.duke.*;
public class CharactersInPlay {
    
    private ArrayList<String> characters;
    private ArrayList<Integer> parts;
    
    CharactersInPlay()
    {
        characters = new ArrayList<>();
        parts = new ArrayList<>();
    }
    
    private void update(String person)
    {
        int index = characters.indexOf(person);
        if(index == -1)
        {
            characters.add(person);
            parts.add(1);
        }
        else
        {
            parts.set(index, parts.get(index) + 1);
        }
    }
    
    private void findAllCharacters()
    {
        characters.clear();
        parts.clear();
        FileResource fr = new FileResource();
        for(String line : fr.lines())
        {
            line = line.trim();
            int index = line.indexOf('.');
            if(index != -1) 
                update(line.substring(0, index));
        }
    }
    
    public void tester()
    {
        findAllCharacters();
        for(int i = 0; i < characters.size(); i++)
        {
            int count = parts.get(i);
            if(count>0)
                System.out.println(characters.get(i)+" "+count);
        }
        int num1 = 10, num2 = 15;
        System.out.println("Characters with minimum "+num1+" parts and maximum "+num2+" parts are:");
        charactersWithNumParts(num1,num2);
    }
    
    private void charactersWithNumParts(int num1, int num2)
    {
        for(int i = 0; i < characters.size(); i++)
        {
            int count = parts.get(i);
            if(count >= num1 && count <= num2)
                System.out.println(characters.get(i)+" "+count);
        }
    }
}
