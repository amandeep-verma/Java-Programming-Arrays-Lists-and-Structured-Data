import edu.duke.*;
import java.io.*;
/**
 * Write a description of TestCaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TestCaesarCipherTwo {

    public static int[] countLetters(String message)
    {
        String alph = "abcdefghijklmnopqrstuvwxyz";
        int[] count = new int[26];
        for (int k=0; k<message.length(); k++)
        {
            char ch = Character.toLowerCase(message.charAt(k));
            int pos = alph.indexOf(ch);
            if (pos != -1)
            {
                count[pos]++;
            }
        }
        return count;
    }
    
    public static int maxIndex(int[] freq)
    {
        int max=0;
        for (int k=0; k<freq.length; k++)
        {
            if(freq[max]<freq[k])
                max= k;
        }
        return max;
    }
    
    public static String halfOfString(String message,int start)
    {
        StringBuilder st = new StringBuilder();
        for(int i = start; i < message.length(); i=i+2)
        {
            st = st.append(message.charAt(i));
        }
        return st.toString();
    }
    
    public static void simpleTests()
    {
        FileResource fr = new FileResource();
        String original = fr.asString();
        CaesarCipherTwo a = new CaesarCipherTwo(17,3);
        String encrypted = a.encrypt(original);
        
        String decrypted = a.decrypt(encrypted);
        
        String decrypted2 = breakCaesarCipher(original);
        System.out.println("original "+ original);
        System.out.println("encrypted "+ encrypted);
        System.out.println("decrypted "+ decrypted);
        System.out.println("decrypted2 "+ decrypted2);
    }
    
    private static int getKey(String s)
    {
        int[] freq= countLetters(s);
        int maxI = maxIndex(freq);
        return maxI;
    }
    
    public static String breakCaesarCipher(String input)
    {
        String even = halfOfString(input,0);
        String odd = halfOfString(input,1);
        int key1 = getKey(even);
        int key2 = getKey(odd);
        
        if (key1 >4)
            key1 = key1-4;
        else
            key1 = 26 - (4-key1);
            
        if (key2> 4)
            key2 = key2-4;
        else
            key2 = 26 - (4-key2);
            
        CaesarCipherTwo a = new CaesarCipherTwo(26-key1,26-key2);
        String decrypted = a.decrypt(input);
        return decrypted;
    }
    
}
