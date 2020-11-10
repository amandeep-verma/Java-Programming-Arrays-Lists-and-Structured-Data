import java.util.*;
import edu.duke.*;
import java.io.File
;
/**
 * Write a description of WordsInFiles here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordsInFiles {

    private HashMap<String,ArrayList<String>>  maps;
    WordsInFiles()
    {
        maps = new HashMap<String,ArrayList<String>>();
    }
    private void addWordsFromFile(File f)
    {
        FileResource fr = new FileResource(f);
        for(String word:fr.words())
        {
            if(!maps.containsKey(word))
            {
                ArrayList<String> ab = new ArrayList<String>();
                ab.add(f.getName());
                maps.put(word,ab);
            }
            else
            {
                ArrayList<String> ab = maps.get(word);
                if(!ab.contains(f.getName()))
                {
                    ab.add(f.getName());
                    maps.put(word,ab);
                }
            }
        }
    }

    void buildWordFileMap() 
    {
        maps.clear();
        DirectoryResource dr = new DirectoryResource();
        for (File f: dr.selectedFiles())
        {
            addWordsFromFile(f);
            
        }
    }
    
    
    int maxNumber()
    {
        int max=0;
        for(String st: maps.keySet())
        {
            int size = maps.get(st).size();
            if(size>max)
            {
                max=size;
            }
        }
        return max;
    
    }
    
    ArrayList<String> wordsInNumFiles(int number)
    {
        ArrayList<String> ret = new ArrayList<String>();
        for(String st: maps.keySet())
        {
            int size = maps.get(st).size();
            if (size == number)
            {
                ret.add(st);
            }
        }
        return ret;
    }
    
    void printFilesIn(String word)
    {
        for(String st: maps.keySet())
        {
            if (st.equals(word))
            {
                for(Object a: maps.get(st))
                {
                    System.out.println(a);
                }
                break;
            }
        }
    }
    
   void tester() 
   {
       buildWordFileMap();
       int maxN = maxNumber();
       
       System.out.println("the maximum number of files any word is in " + maxN );
       
       ArrayList<String> ab = wordsInNumFiles(maxN);
       System.out.println("total such words are "+ab.size());
       System.out.println("total such words which occur in 4 of 7 files  => "+wordsInNumFiles(4).size());
       for(String a:ab)
       {
           System.out.println("For word "+ a +" the files are");
           printFilesIn(a);
       }
       System.out.println("sea");
       printFilesIn("sea");
       System.out.println("tree");
       printFilesIn("tree");
   }
}
