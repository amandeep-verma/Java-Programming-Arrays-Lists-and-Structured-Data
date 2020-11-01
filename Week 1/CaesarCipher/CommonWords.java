import edu.duke.*;
import java.util.Arrays;
/**
 * Write a description of CommonWords here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CommonWords {
    public static String[] getCommon()
    {
        FileResource res = new FileResource("data/common.txt");
        String[] common = new String[20];
        int index = 0;
        for(String s: res.words())
        {
            common[index] =s;
            index += 1;
        }
        
        return common;
        
    }
    
    public static int indexOf(String[] common,String s)
    {
        for( int k =0; k< common.length;k++)
        {
            if ( s.equals(common[k]))
                return k;
        }
        return -1;
    }
    
    public static void countWords(FileResource res,String[] common,int[] count)
    {
        for(String s: res.words())
        {
            s = s.toLowerCase();
            int index = indexOf(common,s);
            if(index != -1)
            {
                count[index]++;
            }
        }
    }
    
    
    public static void countShakespeare(){
        String[] plays = {"caesar.txt","hamlet.txt","macbeth.txt","romeo.txt","errors.txt","likeit.txt"};
        
        String common[] = getCommon();
        int[] count = new int[20];
        
        for( int k =0; k< plays.length;k++)
        {
            FileResource res = new FileResource("/Users/amandeepverma/Downloads/CaesarCipher/data/"+ plays[k]);
            countWords(res,common,count);
            System.out.println("counted for "+plays[k]);
        }
        
        for( int k =0; k< common.length;k++)
        {
            System.out.println("Occurance of \""+common[k]+"\" \t is " + count[k]+" times.");
        }
        
        
    }
}
