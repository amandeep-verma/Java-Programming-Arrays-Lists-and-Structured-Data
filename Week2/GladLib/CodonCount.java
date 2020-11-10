import java.util.*;
import edu.duke.*;
/**
 * Write a description of CodonCount here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CodonCount {
    
    private HashMap<String,Integer>  maps;
    
    CodonCount()
    {
        maps = new HashMap<String,Integer>();
    }
    
    public void buildCodonMap(int start,String dna) 
    {
        maps.clear();
        for(int i = start; i < dna.length()-3; i=i+3)
        {
            String codon = dna.substring(i,i+3);
            if(!maps.containsKey(codon))
            {
                maps.put(codon,1);
            }
            else
            {
                maps.put(codon,1+maps.get(codon));
            }
        }
        
    }
    
    
    String getMostCommonCodon()
    {
           int max =0;
           String maxCodon="" ;
           for(String st: maps.keySet())
           {
               if(maps.get(st)>max )
               {
                   max = maps.get(st);
                   maxCodon = st;
               }
           }
           return maxCodon;
    }
    
    void printCodonCounts(int start,int end)
    {
        for(String st: maps.keySet())
        {
            int count = maps.get(st);
            if ( count >= start && count <= end)
            {
                System.out.println(st+" "+ count);
            }
        }
    }
    
    public void tester()
    {
        FileResource file = new FileResource();
        String dna = file.asString().toUpperCase();
        for(int i =0; i<3;i++)
        {
            buildCodonMap(i,dna);
            System.out.println("Reading frame starting with  "+i+ " results in "+maps.size()+" unique codons");
            System.out.println("most common codon is "+ getMostCommonCodon() + " with count = "+ maps.get(getMostCommonCodon()));
            printCodonCounts(7,7);
        }
        
    }

}
