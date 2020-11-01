import edu.duke.*;
import java.io.*;
/**
 * Write a description of WordLengths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordLengths {

    public static void countWordLengths(FileResource resource, int counts[])
    {
        
        for( String words : resource.words())
        {
            
            int size = words.length();
            if (!Character.isLetter(words.charAt(size-1))) 
                size--;
            // might get error if size of word was already 1
            if (!Character.isLetter(words.charAt(0))) 
                size--;
            
            if (size<30 && size >0)
                counts[size]++;
            else if(size >= 30)
                counts[30]++;
        }
    }
    
     public static int indexOfMax(int []values)
     {
        int max =0;
        int index =0;
        for(int i =0; i<values.length;i++)
        {
            if(max<values[i])
            {
                max=values[i];
                index = i;
                
            }
        }
        return index;
     }
    
     public static void testCountWordLengths()
     {
        FileResource fr = new FileResource();
        int[] count = new int[31];
        countWordLengths(fr,count);
        for(int i =0; i<count.length;i++)
        {
            if(count[i]!=0)
            System.out.println(i+" "+count[i]);
        }
        
        System.out.println("max index is  --> "+indexOfMax(count));
     }
}
