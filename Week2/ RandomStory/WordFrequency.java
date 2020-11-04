import edu.duke.*;
import java.util.ArrayList;

/**
 * Write a description of WordFrequency here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordFrequency {
    
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    
    WordFrequency()
    {
        myWords = new ArrayList<String>();
        myFreqs =  new ArrayList<Integer>();
    }
    
    public void findUnique()
    {
        FileResource fr = new FileResource();
        myWords.clear();
        myFreqs.clear();
        for(String word:fr.words())
        {
            word = word.toLowerCase();
            int index = myWords.indexOf(word);
            if(index == -1)
            {
                myWords.add(word);
                myFreqs.add(1);
            }
            else
            {
                myFreqs.set(index,(myFreqs.get(index))+1);
            }
        }
    }
    
    public int findIndexOfMax()
    {
        int maxIndex =0;
        int maxValue =0;
        for( int i =0; i < myFreqs.size(); i++)
        {   
            if( maxValue < myFreqs.get(i))
            {
                maxIndex = i;
                maxValue = myFreqs.get(i);
            }
        }
        return maxIndex;
    }
    
    public void tester()
    {
        findUnique();
        System.out.println("Total unique value "+ myWords.size());
        
        for(int i =0; i< myWords.size(); i++)
        {
            System.out.println(myFreqs.get(i)+"\t"+myWords.get(i));
        }
        int maxIndex = findIndexOfMax();
        System.out.println("The word that occurs most often and its count are: "+
        myWords.get(maxIndex) +" "+ myFreqs.get(maxIndex));
        
    }
}
