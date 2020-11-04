import edu.duke.*;
import java.util.ArrayList;

/**
 * Write a description of CharactersInPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CharactersInPlay {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    
    CharactersInPlay()
    {
        myWords = new ArrayList<String>();
        myFreqs =  new ArrayList<Integer>();
    }
    
    public void update(String person)
    {
        int index = myWords.indexOf(person);
        if (index == -1)
        {
            myWords.add(person);
            myFreqs.add(1);
        }
        else
        {
            myFreqs.set(index,1+ myFreqs.get(index));
        }
    }
    
    public void findAllCharacters()
    {
        FileResource fr = new FileResource();
        myWords.clear();
        myFreqs.clear();
        for(String line: fr.lines())
        {
            int index = line.indexOf('.');
            if(index != -1)
            {
                String name = line.substring(0,index).trim();
                update(name);
            }
        }
    }
    
    public void charactersWithNumParts(int num1,int num2)
    {
        for (int i =0; i< myWords.size(); i++)
        {
            if (num1 <= myFreqs.get(i) && num2 >= myFreqs.get(i))
                System.out.println(myWords.get(i) +"  "+myFreqs.get(i));
        }
    }
    
    public void tester()
    {
        findAllCharacters();
        for (int i =0; i< myWords.size(); i++)
        {
            System.out.println(myWords.get(i) +"  "+myFreqs.get(i));
        }
        
        System.out.println("-----------------------------------");
        charactersWithNumParts(10,15);
        
        myFreqs.sort(null);
	System.out.println(myFreqs.get(myFreqs.size()-1));
    }
}
